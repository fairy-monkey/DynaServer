package com.geeboo.dyna.server.service.impl.topic;

import com.geeboo.common.constant.StatusEnum;
import com.geeboo.common.exception.runtime.ServiceException;
import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.PageRestRequest;
import com.geeboo.dyna.server.client.dto.topic.*;
import com.geeboo.dyna.server.config.DynaServerConfig;
import com.geeboo.dyna.server.constant.OperateEnum;
import com.geeboo.dyna.server.constant.TopicCacheConstant;
import com.geeboo.dyna.server.mapper.topic.IDynaTopicCommentMapper;
import com.geeboo.dyna.server.service.topic.*;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Title: <br>
 * Description: Copyright: Copyright (c) 2018
 *
 * @author 郭明毅 guomy 创建时间:2018/9/13 22:31
 */
@Slf4j
@Service
public class DynaTopicCommentAppServiceImpl implements IDynaTopicCommentAppService {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ValueOperations valueOperations;
    @Autowired
    private IDynaTopicAppService dynaTopicAppService;
    @Autowired
    private IDynaTopicFavorAppService dynaTopicFavorAppService;
    @Autowired
    private IDynaTopicCommentReplyAppService dynaTopicCommentReplyAppService;
    @Autowired
    private IDynaTopicCommentReplyService dynaTopicCommentReplyService;
    @Autowired
    private IDynaTopicCommentMapper dynaTopicCommentMapper;
    @Autowired
    private DynaServerConfig dynaServerConfig;

    @Override
    public ObjectResponse<DynaTopicCommentListResponseDTO> getCommentPage(PageRestRequest<DynaTopicCommentListDTO> page) {
        if (page.getData().getDynaTopicCommentId() == null || page.getData().getDynaTopicCommentId().equals(0)) {
            valueOperations.increment(TopicCacheConstant.TOPIC_VIEW_NUM + page.getData().getDynaTopicId(), 1);
        }
        Integer specialId = dynaServerConfig.getBookIdiotId();
        List<String> excludeIds = page.getData().getExcludeIds();
        DynaTopicCommentListResponseDTO result = new DynaTopicCommentListResponseDTO();
        if (page.getData().getDynaTopicCommentId() == null || page.getData().getDynaTopicCommentId().equals(0)) {
            result = this.getWithSpecialList(page, specialId);
        } else {
            List<DynaTopicCommentListDTO> dynaTopicCommentList = dynaTopicCommentMapper.getCommentByTopic(page.getData().getDynaTopicId(),
                    page.getData().getDynaTopicCommentId(), excludeIds, page.getData().getUserId(), page.getPage().getPageSize());
            List<String> sortIdList = Lists.newArrayList();
            Integer commentId = this.commentIdSort(dynaTopicCommentList, sortIdList);
            result.setDynaTopicCommentId(commentId);
            result.setCommentList(dynaTopicCommentList);
            result.setSpecialList(Lists.newArrayList());
        }
        ObjectResponse<DynaTopicCommentListResponseDTO> response = new ObjectResponse();
        return response.data(result);
    }

    /**
     * 获取特殊列表，例如书大痴回复
     *
     * @param page
     * @return
     */
    private DynaTopicCommentListResponseDTO getWithSpecialList(PageRestRequest<DynaTopicCommentListDTO> page, Integer specialId) {
        Integer dynaTopicId = page.getData().getDynaTopicId();
        Integer pageSize = page.getPage().getPageSize();
        Integer userId = page.getData().getUserId();
        Integer dynaTopicCommentId = page.getData().getDynaTopicCommentId();

        DynaTopicCommentListResponseDTO result = new DynaTopicCommentListResponseDTO();
        List<DynaTopicCommentListDTO> specialList = Lists.newArrayList();

        //1、书大痴评论列表
        if (specialId != null) {
            specialList = dynaTopicCommentMapper.getCommentByTopicAndSpecialId(dynaTopicId, specialId, pageSize);
        }
        //2、获取热门评论列表
        List<DynaTopicCommentListDTO> hotList = dynaTopicCommentMapper.getHotCommentByTopic(dynaTopicId, specialId, userId, pageSize);
        specialList.addAll(hotList);
        //3、排除1、2的评论id
        List<String> specialIdList = Lists.newArrayList();
        Integer minId = this.commentIdSort(specialList, specialIdList);

        List<DynaTopicCommentListDTO> dynaTopicCommentList = dynaTopicCommentMapper.getCommentByTopic(dynaTopicId, dynaTopicCommentId, specialIdList, userId, pageSize);
        if (dynaTopicCommentList != null && dynaTopicCommentList.size() > 0) {
            List<String> sortIdList = Lists.newArrayList();
            Integer commentId = this.commentIdSort(dynaTopicCommentList, sortIdList);
            result.setDynaTopicCommentId(commentId);
            this.setCommentInfo(dynaTopicCommentList, page.getData());
        } else {
            result.setDynaTopicCommentId(minId);
        }
        this.setCommentInfo(specialList, page.getData());
        result.setSpecialList(specialList);
        result.setCommentList(dynaTopicCommentList != null ? dynaTopicCommentList : Lists.newArrayList());
        result.setExcludeIds(specialIdList);
        return result;
    }

    private Integer commentIdSort(List<DynaTopicCommentListDTO> list, List<String> sortIdList) {
        if (list == null || list.size() <= 0) {
            return 0;
        }
        for (DynaTopicCommentListDTO dto : list) {
            sortIdList.add(String.valueOf(dto.getDynaTopicCommentId()));
        }
        Collections.sort(sortIdList);
        return Integer.valueOf(sortIdList.get(0));
    }

    private void setCommentInfo(List<DynaTopicCommentListDTO> list, DynaTopicCommentListDTO queryDTO) {
        if (list.isEmpty()) {
            return;
        }
        Set<Integer> commentIdSet = Sets.newHashSetWithExpectedSize(list.size());
        for (DynaTopicCommentListDTO dto : list) {
            commentIdSet.add(dto.getDynaTopicCommentId());
        }
        // 获取每个评论的回复数量和点赞数量
        this.setNumIfHasCache(list, commentIdSet);
        // 用户是否点赞
        this.setUserFavor(list, queryDTO, commentIdSet);
    }

    private void setNumIfHasCache(List<DynaTopicCommentListDTO> list, Set<Integer> commentIdSet) {
        List<String> replyKeyList = Lists.newArrayListWithExpectedSize(commentIdSet.size());
        List<String> favorKeyList = Lists.newArrayListWithExpectedSize(commentIdSet.size());
        for (DynaTopicCommentListDTO dto : list) {
            replyKeyList.add(TopicCacheConstant.TOPIC_COMMENT_REPLY_NUM + dto.getDynaTopicCommentId());
            favorKeyList.add(TopicCacheConstant.TOPIC_COMMENT_FAVOR_NUM + dto.getDynaTopicCommentId());
        }
        List<Integer> replyNumList = valueOperations.multiGet(replyKeyList);
        List<Integer> favorNumList = valueOperations.multiGet(favorKeyList);
        // 如果存在于缓存中，则设置为缓存中的数量
        int index = 0;
        for (DynaTopicCommentListDTO dto : list) {
            Integer replyNum = replyNumList.get(index);
            if (replyNum != null) {
                dto.setNumReply(replyNum);
            }
            Integer favorNum = favorNumList.get(index);
            if (favorNum != null) {
                dto.setNumFavor(favorNum);
            }
            index++;
        }
    }

    private void setUserFavor(List<DynaTopicCommentListDTO> list, DynaTopicCommentListDTO queryDTO,
                              Set<Integer> commentIdSet) {
        if (queryDTO == null || queryDTO.getUserId() == null || queryDTO.getUserId().equals(0)) {
            return;
        }
        if (commentIdSet.isEmpty()) {
            return;
        }
        Set<Integer> commentIdHasFavorSet = dynaTopicFavorAppService.findCommentFavorListByUser(commentIdSet,
                queryDTO.getUserId());
        for (DynaTopicCommentListDTO dto : list) {
            if (commentIdHasFavorSet.contains(dto.getDynaTopicCommentId())) {
                dto.setHasFavor(true);
            } else {
                dto.setHasFavor(false);
            }
        }
    }

    @Override
    public DynaTopicCommentListDTO getCommentDetail(PageRestRequest<DynaTopicReplyListDTO> page) {
        DynaTopicCommentListDTO dto = dynaTopicCommentMapper.getCommentDetail(page.getData().getDynaTopicCommentId());
        Integer commentId = dto.getDynaTopicCommentId();
        // 设置评论数和点赞数
        Object replyNum = valueOperations.get(TopicCacheConstant.TOPIC_COMMENT_REPLY_NUM + commentId);
        Object favorNum = valueOperations.get(TopicCacheConstant.TOPIC_COMMENT_FAVOR_NUM + commentId);
        if (replyNum != null) {
            dto.setNumReply(Integer.parseInt(String.valueOf(replyNum)));
        }
        if (favorNum != null) {
            dto.setNumFavor(Integer.parseInt(String.valueOf(favorNum)));
        }
        // 设置是否点赞
        Set<Integer> commentIdSet = Sets.newHashSetWithExpectedSize(1);
        commentIdSet.add(commentId);
        Set<Integer> commentIdHasFavorSet = dynaTopicFavorAppService.findCommentFavorListByUser(commentIdSet,
                page.getData().getUserId());
        if (commentIdHasFavorSet.contains(commentId)) {
            dto.setHasFavor(true);
        } else {
            dto.setHasFavor(false);
        }
        return dto;
    }

    @Override
    public DynaTopicCommentListDTO getCommentDetail(Integer id) {
        DynaTopicCommentListDTO dto = dynaTopicCommentMapper.getCommentDetail(id);
        if (dto != null) {
            return dto;
        }
        dto.setNumReply(null);
        dto.setNumFavor(null);
        return dto;
    }

    @Override
    public List<DynaTopicCommentDTO> getRecentCommentUserList(Integer dynaTopicId) {
        return dynaTopicCommentMapper.getRecentCommentUserList(dynaTopicId);
    }

    @Override
    public long getCommentCountGroupByAccount(Integer dynaTopicId) {
        Long count = dynaTopicCommentMapper.getCommentCountGroupByAccount(dynaTopicId);
        return count != null ? count.longValue() : 0;
    }

    @Transactional
    @Override
    public ObjectResponse<DynaTopicCommentDTO> addComment(DynaTopicCommentDTO dto) {
        dto.setNumFavor(0);
        dto.setNumReply(0);
        dto.setIsDel(0);
        dto.setIndexNo(0);
        dynaTopicCommentMapper.add(dto);
        log.info("新增评论: dto: {}", dto);
        dynaTopicAppService.incrementTopicCount(dto, OperateEnum.ADD);
        DynaTopicCommentDTO resultDTO = new DynaTopicCommentDTO();
        resultDTO.setDynaTopicCommentId(dto.getDynaTopicCommentId());
        return new ObjectResponse<>().data(resultDTO).rel(true);
    }

    @Transactional
    @Override
    public BaseResponse deleteComment(DynaTopicCommentDTO dto) {
        DynaTopicCommentDTO oldDTO = dynaTopicCommentMapper.findById(dto.getDynaTopicCommentId());
        if (oldDTO == null) {
            log.info("评论不存在，删除成功, dto: {}", dto);
            return BaseResponse.success("删除成功");
        }
        if (!oldDTO.getUserId().equals(dto.getUserId())) {
            return BaseResponse.failure(StatusEnum.NO_ENTRY.getStatus(), "只能删除自己发表的评论");
        }
        dynaTopicCommentMapper.deleteDynaTopicComment(dto.getDynaTopicCommentId());
        Long replyCount = dynaTopicCommentReplyAppService.countReplyByComment(dto.getDynaTopicCommentId());
        dynaTopicCommentReplyService.deleteByCommentId(dto.getDynaTopicCommentId());
        log.info("删除评论: dto: {}, replyCount: {}", dto, replyCount);
        dynaTopicAppService.incrementTopicCount(dto, OperateEnum.DELETE);
        dynaTopicAppService.incrementTopicCountReply(dto, replyCount);
        return BaseResponse.success("删除成功");
    }

    @Override
    public void incrementCommentCount(DynaTopicCommentReplyDTO dto, OperateEnum operate) throws ServiceException {
        String redisKey = TopicCacheConstant.TOPIC_COMMENT_REPLY_NUM + dto.getDynaTopicCommentId();
        Boolean flag = redisTemplate.expire(redisKey, 1, TimeUnit.DAYS);
        if (flag) {
            this.incrementByRedisKey(redisKey, operate);
            return;
        }
        // 从数据库更新
        DynaTopicCommentDTO commentDTO = dynaTopicCommentMapper.findById(dto.getDynaTopicCommentId());
        valueOperations.setIfAbsent(redisKey, commentDTO.getNumReply());
        redisTemplate.expire(redisKey, 1, TimeUnit.DAYS);
        this.incrementByRedisKey(redisKey, operate);
    }

    @Override
    public void incrementCommentCount(DynaTopicCommentFavorDTO dto, OperateEnum operate) throws ServiceException {
        // 话题点赞数：topic:comment:favorNum:dynaTopicCommentId
        String redisKey = TopicCacheConstant.TOPIC_COMMENT_FAVOR_NUM + dto.getDynaTopicCommentId();
        Boolean flag = redisTemplate.expire(redisKey, 1, TimeUnit.DAYS);
        if (flag) {
            this.incrementByRedisKey(redisKey, operate);
            return;
        }
        // 从数据库更新
        DynaTopicCommentDTO commentDTO = dynaTopicCommentMapper.findById(dto.getDynaTopicCommentId());
        valueOperations.setIfAbsent(redisKey, commentDTO.getNumFavor());
        redisTemplate.expire(redisKey, 1, TimeUnit.DAYS);
        this.incrementByRedisKey(redisKey, operate);
    }

    private void incrementByRedisKey(String redisKey, OperateEnum operate) {
        if (operate == OperateEnum.ADD) {
            valueOperations.increment(redisKey, 1L);
        } else {
            valueOperations.increment(redisKey, -1L);
        }
    }
}
