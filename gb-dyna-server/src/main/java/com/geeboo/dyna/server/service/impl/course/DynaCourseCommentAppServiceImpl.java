package com.geeboo.dyna.server.service.impl.course;

import com.geeboo.common.constant.StatusEnum;
import com.geeboo.common.exception.runtime.ServiceException;
import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.PageRestRequest;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.dyna.server.client.dto.course.*;
import com.geeboo.dyna.server.constant.CourseCacheConstant;
import com.geeboo.dyna.server.constant.OperateEnum;
import com.geeboo.dyna.server.mapper.course.IDynaCourseCommentMapper;
import com.geeboo.dyna.server.mapper.course.IDynaCourseStatMapper;
import com.geeboo.dyna.server.service.course.IDynaCourseCommentAppService;
import com.geeboo.dyna.server.service.course.IDynaCourseCommentReplyAppService;
import com.geeboo.dyna.server.service.course.IDynaCourseFavorAppService;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Title: <br>
 * Description: Copyright: Copyright (c) 2018
 *
 * @author 郭明毅 guomy 创建时间:2018/9/13 22:31
 */
@Slf4j
@Service
public class DynaCourseCommentAppServiceImpl implements IDynaCourseCommentAppService {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ValueOperations valueOperations;
    @Autowired
    private IDynaCourseFavorAppService dynaCourseFavorAppService;
    @Autowired
    private IDynaCourseCommentReplyAppService dynaCourseCommentReplyAppService;
    @Autowired
    private IDynaCourseCommentMapper dynaCourseCommentMapper;
    @Autowired
    private IDynaCourseStatMapper dynaCourseStatMapper;

    @Override
    public TableResultResponse<DynaCourseCommentListDTO> getCommentPage(
            PageRestRequest<DynaCourseCommentListDTO> page) {
        Integer courseId = page.getData().getCourseId();
        Integer courseCommentId = page.getData().getDynaCourseCommentId();
        Integer userId = page.getData().getUserId();
        Integer pageSize = page.getPage().getPageSize();
        List<DynaCourseCommentListDTO> list = dynaCourseCommentMapper.getCommentByCourseAndLastId(courseId,
                courseCommentId, userId, pageSize);
        this.setCommentInfo(list, page.getData());
        long total = this.getTotalCommentNum(courseId);
        page.getPage().setTotalItems(total);
        TableResultResponse response = new TableResultResponse<>(total, page.getPage().getTotalPages(), list);
        return response;
    }

    /**
     * 评论总数
     *
     * @return
     */
    private long getTotalCommentNum(Integer courseId) {
        // 图书评论数
        String redisKey = CourseCacheConstant.COURSE_COMMENT_NUM + courseId;
        Integer total = (Integer) valueOperations.get(redisKey);
        if (total != null) {
            return total.longValue();
        }
        Integer numComment = dynaCourseCommentMapper.getCommentCountNumByCourseId(courseId);
        return numComment;
    }

    private void setCommentInfo(List<DynaCourseCommentListDTO> list, DynaCourseCommentListDTO queryDTO) {
        if (list.isEmpty()) {
            return;
        }
        Set<Integer> commentIdSet = Sets.newHashSetWithExpectedSize(list.size());
        for (DynaCourseCommentListDTO dto : list) {
            commentIdSet.add(dto.getDynaCourseCommentId());
        }
        this.setNumIfHasCache(list, commentIdSet);
        this.setUserFavor(list, queryDTO, commentIdSet);
    }

    private void setNumIfHasCache(List<DynaCourseCommentListDTO> list, Set<Integer> commentIdSet) {
        List<String> replyKeyList = Lists.newArrayListWithExpectedSize(commentIdSet.size());
        List<String> favorKeyList = Lists.newArrayListWithExpectedSize(commentIdSet.size());
        for (DynaCourseCommentListDTO dto : list) {
            replyKeyList.add(CourseCacheConstant.COURSE_COMMENT_REPLY_NUM + dto.getDynaCourseCommentId());
            favorKeyList.add(CourseCacheConstant.COURSE_COMMENT_FAVOR_NUM + dto.getDynaCourseCommentId());
        }
        // 图书评论回复数
        List<Integer> replyNumList = valueOperations.multiGet(replyKeyList);
        // 图书评论点赞数
        List<Integer> favorNumList = valueOperations.multiGet(favorKeyList);
        // 如果存在于缓存中，则设置为缓存中的数量
        int index = 0;
        for (DynaCourseCommentListDTO dto : list) {
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

    private void setUserFavor(List<DynaCourseCommentListDTO> list, DynaCourseCommentListDTO queryDTO,
                              Set<Integer> commentIdSet) {
        if (queryDTO == null || queryDTO.getUserId() == null || queryDTO.getUserId().equals(0)) {
            return;
        }
        if (commentIdSet.isEmpty()) {
            return;
        }
        Set<Integer> commentIdHasFavorSet = dynaCourseFavorAppService.findCommentFavorListByUser(commentIdSet,
                queryDTO.getUserId());
        for (DynaCourseCommentListDTO dto : list) {
            if (commentIdHasFavorSet.contains(dto.getDynaCourseCommentId())) {
                dto.setHasFavor(true);
            } else {
                dto.setHasFavor(false);
            }
        }
    }

    @Override
    public DynaCourseCommentListDTO getCommentDetail(Integer id) {
        DynaCourseCommentListDTO dto = dynaCourseCommentMapper.getCommentDetail(id);
        if (dto == null) {
            return dto;
        }
        List<DynaCourseCommentListDTO> list = Lists.newArrayList(dto);
        Set<Integer> commentIdSet = Sets.newHashSet(dto.getDynaCourseCommentId());
        this.setUserFavor(list, dto, commentIdSet);
        this.setNumIfHasCache(list, commentIdSet);
        return dto;
    }

    @Override
    public List<DynaCourseCommentDTO> getRecentCommentUserList(Integer dynaCourseId) {
        return dynaCourseCommentMapper.getRecentCommentUserList(dynaCourseId);
    }

    @Override
    public long getCommentCountGroupByAccount(Integer dynaCourseId) {
        Long count = dynaCourseCommentMapper.getCommentCountGroupByAccount(dynaCourseId);
        return count != null ? count.longValue() : 0;
    }

    @Transactional
    @Override
    public ObjectResponse<DynaCourseCommentDTO> addComment(DynaCourseCommentDTO dto) {
        dto.setNumFavor(0);
        dto.setNumReply(0);
        dto.setIsDel(0);
        dto.setIndexNo(0);
        dynaCourseCommentMapper.add(dto);
        log.info("新增评论: dto: {}", dto);
        this.incrementCommentCount(dto, OperateEnum.ADD);
        DynaCourseCommentDTO resultDTO = new DynaCourseCommentDTO();
        resultDTO.setDynaCourseCommentId(dto.getDynaCourseCommentId());
        return new ObjectResponse<>().data(resultDTO).rel(true);
    }

    @Transactional
    @Override
    public BaseResponse deleteComment(DynaCourseCommentDTO dto) {
        DynaCourseCommentDTO oldDTO = dynaCourseCommentMapper.findById(dto.getDynaCourseCommentId());
        if (oldDTO == null) {
            log.info("评论不存在，删除成功, dto: {}", dto);
            return BaseResponse.success("删除成功");
        }
        if (!oldDTO.getUserId().equals(dto.getUserId())) {
            return BaseResponse.failure(StatusEnum.NO_ENTRY.getStatus(), "只能删除自己发表的评论");
        }
        dynaCourseCommentMapper.deleteDynaCourseComment(dto.getDynaCourseCommentId());
        Long replyCount = dynaCourseCommentReplyAppService.countReplyByComment(dto.getDynaCourseCommentId());
        log.info("删除评论: dto: {}, replyCount: {}", dto, replyCount);
        this.incrementCommentCount(dto, OperateEnum.DELETE);
        return BaseResponse.success("删除成功");
    }

    @Override
    public void incrementCommentCount(DynaCourseCommentReplyDTO dto, OperateEnum operate) throws ServiceException {
        String redisKey = CourseCacheConstant.COURSE_COMMENT_REPLY_NUM + dto.getDynaCourseCommentId();
        Boolean flag = redisTemplate.expire(redisKey, 1, TimeUnit.DAYS);
        if (flag) {
            this.incrementByRedisKey(redisKey, operate);
            return;
        }
        // 从数据库更新
        DynaCourseCommentDTO commentDTO = dynaCourseCommentMapper.findById(dto.getDynaCourseCommentId());
        valueOperations.setIfAbsent(redisKey, commentDTO.getNumReply());
        redisTemplate.expire(redisKey, 1, TimeUnit.DAYS);
        this.incrementByRedisKey(redisKey, operate);
    }

    @Override
    public void incrementCommentCount(DynaCourseCommentFavorDTO dto, OperateEnum operate) throws ServiceException {
        String redisKey = CourseCacheConstant.COURSE_COMMENT_FAVOR_NUM + dto.getDynaCourseCommentId();
        Boolean flag = redisTemplate.expire(redisKey, 1, TimeUnit.DAYS);
        if (flag) {
            this.incrementByRedisKey(redisKey, operate);
            return;
        }
        // 从数据库更新
        DynaCourseCommentDTO commentDTO = dynaCourseCommentMapper.findById(dto.getDynaCourseCommentId());
        valueOperations.setIfAbsent(redisKey, commentDTO.getNumFavor());
        redisTemplate.expire(redisKey, 1, TimeUnit.DAYS);
        this.incrementByRedisKey(redisKey, operate);
    }

    @Override
    public void incrementCommentCount(DynaCourseCommentDTO dto, OperateEnum operate) throws ServiceException {
        String redisKey = CourseCacheConstant.COURSE_COMMENT_NUM + dto.getCourseId();
        Boolean flag = redisTemplate.expire(redisKey, 1, TimeUnit.DAYS);
        if (flag) {
            this.incrementByRedisKey(redisKey, operate);
            return;
        }
        // 从数据库更新
        DynaCourseStatDTO statDTO = dynaCourseStatMapper.findByCourseId(dto.getCourseId());
        if (statDTO == null) {
            statDTO = new DynaCourseStatDTO();
            statDTO.setNumComment(0);
        }
        valueOperations.setIfAbsent(redisKey, statDTO.getNumComment());
        redisTemplate.expire(redisKey, 1, TimeUnit.DAYS);
        this.incrementByRedisKey(redisKey, operate);
    }

    @Override
    public ObjectResponse<DynaCourseStatDTO> getCourseStat(Integer courseId) {
        ObjectResponse<DynaCourseStatDTO> response = new ObjectResponse<>();
        String redisKey = CourseCacheConstant.COURSE_COMMENT_NUM + courseId;
        Integer value = (Integer) valueOperations.get(redisKey);
        if (value != null) {
            DynaCourseStatDTO statDTO = new DynaCourseStatDTO();
            statDTO.setNumComment(value);
            response.setData(statDTO);
            return response;
        }
        DynaCourseStatDTO stat = dynaCourseStatMapper.findByCourseId(courseId);
        response.setData(stat);
        return response;
    }

    @Transactional
    @Override
    public BaseResponse updateComment(DynaCourseCommentDTO dto) {
        dynaCourseCommentMapper.update(dto);
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }

    private void incrementByRedisKey(String redisKey, OperateEnum operate) {
        if (operate == OperateEnum.ADD) {
            valueOperations.increment(redisKey, 1L);
        } else {
            valueOperations.increment(redisKey, -1L);
        }
    }
}
