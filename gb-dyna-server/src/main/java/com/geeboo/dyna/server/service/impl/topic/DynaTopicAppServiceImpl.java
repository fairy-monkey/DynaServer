package com.geeboo.dyna.server.service.impl.topic;

import com.geeboo.common.bean.BeanUtils;
import com.geeboo.common.constant.StatusEnum;
import com.geeboo.common.exception.runtime.ServiceException;
import com.geeboo.common.exception.util.ExceptionUtil;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.PageRestRequest;
import com.geeboo.dyna.server.client.dto.topic.*;
import com.geeboo.dyna.server.constant.CacheConstant;
import com.geeboo.dyna.server.constant.OperateEnum;
import com.geeboo.dyna.server.constant.TopicCacheConstant;
import com.geeboo.dyna.server.mapper.topic.IDynaTopicMapper;
import com.geeboo.dyna.server.service.topic.*;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Title: <br>
 * Description: Copyright: Copyright (c) 2018
 *
 * @author 郭明毅 guomy 创建时间:2018/9/12 13:56
 */
@Slf4j
@Service
public class DynaTopicAppServiceImpl implements IDynaTopicAppService {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ValueOperations valueOperations;
    @Autowired
    private ZSetOperations zSetOperations;
    @Autowired
    private IDynaTopicService topicService;
    @Autowired
    private IDynaTopicContentService dynaTopicContentService;
    @Autowired
    private IDynaTopicStatService dynaTopicStatService;
    @Autowired
    private IDynaTopicCommentAppService dynaTopicCommentAppService;
    @Autowired
    private IDynaTopicMapper dynaTopicMapper;

    @Override
    public ObjectResponse<DynaTopicListAndUserDTO> getTopicPage(PageRestRequest<DynaTopicDTO> page) {
        if (page.getPage().getPageSize() > 100) {
            return ObjectResponse.failure(StatusEnum.BAD_REQUEST.getStatus(), "分页大小不能超过100");
        }
        List<DynaTopicListDTO> searchList;
        Long size = zSetOperations.size(TopicCacheConstant.TOPIC_SORT_ZSET);
        // 返回一个有序的LinkedHashSet
        Set<Integer> idSet = zSetOperations.range(TopicCacheConstant.TOPIC_SORT_ZSET, page.getPage().getOffset(), page.getPage().getOffset() + page.getPage().getPageSize() - 1);
        if (idSet.isEmpty()) {
            searchList = Lists.newArrayListWithExpectedSize(0);
        } else {
            //获取话题列表
            searchList = dynaTopicMapper.batchGetTopicListByIdSet(idSet);
        }
        //将searchList排序返回新的list
        List<DynaTopicListDTO> resultList = this.getListWithSort(searchList, idSet);
        IdHelper helper = this.setTopicDiscussInfo(resultList);
        page.getPage().setTotalItems(size);
        return new ObjectResponse<DynaTopicListAndUserDTO>().data(new DynaTopicListAndUserDTO(resultList, helper.accountIdSet, size, page.getPage().getTotalPages())).rel(true);
    }

    /**
     * 根据LinkedHashSet的顺序对list元素进行排序
     *
     * @param searchList
     * @param idSet
     * @return
     */
    private List<DynaTopicListDTO> getListWithSort(List<DynaTopicListDTO> searchList, Set<Integer> idSet) {
        if (searchList.isEmpty()) {
            return Lists.newArrayListWithExpectedSize(0);
        }
        Map<Integer, DynaTopicListDTO> map = Maps.newHashMapWithExpectedSize(searchList.size());
        for (DynaTopicListDTO dto : searchList) {
            map.put(dto.getDynaTopicId(), dto);
        }
        List<DynaTopicListDTO> list = Lists.newArrayListWithExpectedSize(searchList.size());
        for (Integer id : idSet) {
            list.add(map.get(id));
        }
        return list;
    }

    /**
     * 设置其他信息
     *
     * @param list
     */
    private IdHelper setTopicDiscussInfo(List<DynaTopicListDTO> list) {
        if (list.isEmpty()) {
            return new IdHelper(0);
        }
        IdHelper helper = new IdHelper(list.size());
        this.buildBaseTopicInfo(list, helper);
        List<String> contentList = this.getContentList(helper.topicIdSet);
        this.setContentIntoList(list, contentList);
        contentList = null;
        Map<Integer, DynaTopicStatDTO> statDTOMap = this.getCountMapFromDb(helper.topicIdForCountSet);
        this.setStatIntoList(list, statDTOMap, helper.accountIdSet);
        // 把用户ID换成真正头像（目前需要API层做）
        return helper;
    }

    /**
     * 设置基本信息。存在redis的直接设置，不存在的记录set
     *
     * @param list
     * @param helper
     */
    private void buildBaseTopicInfo(List<DynaTopicListDTO> list, IdHelper helper) {
        for (DynaTopicListDTO dto : list) {
            helper.topicIdSet.add(dto.getDynaTopicId());
            // 获取话题最近参与的用户，最多3个
            Set<Integer> recentAccountSet = zSetOperations.range(TopicCacheConstant.TOPIC_RECENT_ACCOUNT + dto.getDynaTopicId(), 0L, 2L);
            if (!recentAccountSet.isEmpty()) {
                List<String> accountList = new ArrayList<>(recentAccountSet.size());
                for (Integer accountId : recentAccountSet) {
                    accountList.add(accountId.toString());
                    helper.accountIdSet.add(accountId);
                }
                dto.setAccountPhotoList(accountList);
            } else {
                helper.topicIdForCountSet.add(dto.getDynaTopicId());
            }
            // 评论数
            Object commentCount = valueOperations.get(TopicCacheConstant.TOPIC_COMMENT_NUM + dto.getDynaTopicId());
            dto.setNumComment(this.getNumAndAddSetIfNull(commentCount, helper.topicIdForCountSet, dto.getDynaTopicId()));
            // 参与讨论数
            Object participateCount =
                    valueOperations.get(TopicCacheConstant.TOPIC_PARTICIPATE_NUM + dto.getDynaTopicId());
            dto.setNumTotal(this.getNumAndAddSetIfNull(participateCount, helper.topicIdForParticipateCountSet, dto.getDynaTopicId()));
        }
    }

    /**
     * 获取正文列表
     *
     * @param topicIdSet
     * @return LinkedList<String>
     */
    private List<String> getContentList(LinkedHashSet<Integer> topicIdSet) {
        // 先查缓存
        LinkedList<String> contentKeyList = Lists.newLinkedList();
        for (Integer id : topicIdSet) {
            contentKeyList.add(TopicCacheConstant.TOPIC_CONTENT_PREFIX + id);
        }
        List<Object> contentRedisList = valueOperations.multiGet(contentKeyList);
        // <id, 下标>
        Map<Integer, Integer> topicIdHasNotContentMap = Maps.newLinkedHashMap();
        List<String> contentValueList = Lists.newArrayListWithExpectedSize(topicIdSet.size());
        List<Integer> topicIdList = Lists.newArrayList(topicIdSet);
        int index = 0;
        for (Object obj : contentRedisList) {
            contentValueList.add((String) obj);
            if (obj == null) {
                topicIdHasNotContentMap.put(topicIdList.get(index), index);
            }
            index++;
        }
        if (topicIdHasNotContentMap.isEmpty()) {
            return contentValueList;
        }
        // 再查数据库，补缓存
        List<DynaTopicContentDTO> contentDbList =
                dynaTopicContentService.batchGetContentByTopicId(topicIdHasNotContentMap);
        for (DynaTopicContentDTO contentDTO : contentDbList) {
            int indexInList = topicIdHasNotContentMap.get(contentDTO.getDynaTopicId());
            contentValueList.set(indexInList, contentDTO.getDynaTopicContent());
            valueOperations.set(TopicCacheConstant.TOPIC_CONTENT_PREFIX
                    + contentDTO.getDynaTopicId(), contentDTO.getDynaTopicContent(), 3, TimeUnit.DAYS);
        }
        return contentValueList;
    }

    private void setContentIntoList(List<DynaTopicListDTO> list, List<String> contentList) {
        if (list.size() != contentList.size()) {
            log.warn("列表长度与内容列表长度不等, list.size: {}, contentList: {}", list, contentList);
            return;
        }
        int index = 0;
        for (DynaTopicListDTO dto : list) {
            String content = contentList.get(index++);
            dto.setContent(content);
        }
    }

    private int getNumAndAddSetIfNull(Object num, Set<Integer> idSet, Integer id) {
        if (num != null) {
            if (num instanceof Integer) {
                return (Integer) num;
            } else {
                return Integer.parseInt((String) num);
            }
        } else {
            idSet.add(id);
            return CacheConstant.UNUSED_NUMBER;
        }
    }

    /**
     * 获取需要从数据库查询的统计信息
     *
     * @param topicIdForCountSet
     * @return
     */
    private Map<Integer, DynaTopicStatDTO> getCountMapFromDb(Set<Integer> topicIdForCountSet) {
        if (topicIdForCountSet.isEmpty()) {
            return Maps.newHashMapWithExpectedSize(0);
        }
        ObjectResponse<Map<Integer, DynaTopicStatDTO>> response;
        try {
            response = dynaTopicStatService.batchGetStatByTopicId(topicIdForCountSet);
            if (!response.isSuccess() || !response.isRel()) {
                log.error("从数据库查询统计信息异常, topicIdForCountSet: {}, response: {}", topicIdForCountSet, response);
                return Maps.newHashMapWithExpectedSize(0);
            }
            return response.getData();
        } catch (Exception e) {
            log.error("从数据库查询统计信息异常, topicIdForCountSet: {}", topicIdForCountSet, e);
        }
        return Maps.newHashMapWithExpectedSize(0);
    }

    private void setStatIntoList(List<DynaTopicListDTO> list, Map<Integer, DynaTopicStatDTO> statDTOMap, Set<Integer> accountIdSet) {
        for (DynaTopicListDTO dto : list) {
            DynaTopicStatDTO statDTO = statDTOMap.get(dto.getDynaTopicId());
            if (statDTO == null) {
                continue;
            }
            dto.setNumComment(dto.getNumComment() != CacheConstant.UNUSED_NUMBER ? dto.getNumComment() : statDTO.getNumComment());
            dto.setNumTotal(dto.getNumTotal() != CacheConstant.UNUSED_NUMBER ? dto.getNumTotal() : statDTO.getNumTotal());
            if (dto.getAccountPhotoList() == null) {
                List<String> accountIdList = Lists.newArrayListWithExpectedSize(3);
                dto.setAccountPhotoList(accountIdList);
                if (statDTO.getFirstUserId().equals(0)) {
                    continue;
                }
                accountIdList.add(statDTO.getFirstUserId().toString());
                accountIdSet.add(statDTO.getFirstUserId());
                if (statDTO.getSecondUserId().equals(0)) {
                    continue;
                }
                accountIdList.add(statDTO.getSecondUserId().toString());
                accountIdSet.add(statDTO.getSecondUserId());
                if (statDTO.getThirdUserId().equals(0)) {
                    continue;
                }
                accountIdList.add(statDTO.getThirdUserId().toString());
                accountIdSet.add(statDTO.getThirdUserId());
            }
        }
    }

    @Override
    public ObjectResponse<DynaTopicDTO> findLatestTopic() {
        // 返回一个有序的LinkedHashSet
        Set<Integer> idSet = zSetOperations.range(TopicCacheConstant.TOPIC_SORT_ZSET, 0, 0);
        if (idSet.isEmpty()) {
            topicService.initAppTopicSort(null);
            idSet = zSetOperations.range(TopicCacheConstant.TOPIC_SORT_ZSET, 0, 1);
            if (idSet.isEmpty()) {
                return ObjectResponse.failure(StatusEnum.FAILURE.getStatus(), "查找不到首页话题");
            }
        }
        List<DynaTopicListDTO> resultList = dynaTopicMapper.batchGetTopicListByIdSet(idSet);
        //取排序后的第一条记录
        DynaTopicListDTO listDTO = resultList.get(0);
        DynaTopicDTO topicDTO = this.getFullTopicInfo(listDTO);
        return new ObjectResponse<>().data(topicDTO).rel(true);
    }

    private DynaTopicDTO getFullTopicInfo(DynaTopicListDTO listDTO) {
        DynaTopicDTO dto = new DynaTopicDTO();
        BeanUtils.copyIgnoreNull(listDTO, dto);
        //话题正文，后面需要拼接ID
        String content = (String) valueOperations.get(TopicCacheConstant.TOPIC_CONTENT_PREFIX + listDTO.getDynaTopicId());
        //内容为空就送数据库取
        if (content == null) {
            DynaTopicContentDTO contentDTO = dynaTopicContentService.findByTopicId(listDTO.getDynaTopicId());
            if (contentDTO != null) {
                content = contentDTO.getDynaTopicContent();
            }
        }
        dto.setDynaTopicContent(content);
        //获取话题最近参与用户，根据当前时间倒序，最多3个
        Set<Integer> recentAccountSet = zSetOperations.range(TopicCacheConstant.TOPIC_RECENT_ACCOUNT + listDTO.getDynaTopicId(), 0L, 2L);
        //获取话题评论数
        Integer commentNum = (Integer) valueOperations.get(TopicCacheConstant.TOPIC_COMMENT_NUM + listDTO.getDynaTopicId());
        //获取话题
        Integer replyNum = (Integer) valueOperations.get(TopicCacheConstant.TOPIC_REPLY_NUM + listDTO.getDynaTopicId());
        //话题参与讨论数
        Integer participateNum = (Integer) valueOperations.get(TopicCacheConstant.TOPIC_PARTICIPATE_NUM + listDTO.getDynaTopicId());
        DynaTopicStatDTO statDTO = null;
        if (recentAccountSet.isEmpty() || commentNum == null || replyNum == null || participateNum == null) {
            statDTO = dynaTopicStatService.findByTopicId(listDTO.getDynaTopicId());
        }
        dto.setNumComment(commentNum != null ? commentNum : statDTO.getNumComment());
        dto.setNumReply(replyNum != null ? replyNum : statDTO.getNumReply());
        dto.setNumTotal(participateNum != null ? participateNum : statDTO.getNumTotal());
        this.setRecentCommentList(dto, recentAccountSet, statDTO);
        return dto;
    }

    private void setRecentCommentList(DynaTopicDTO dto, Set<Integer> recentAccountSet, DynaTopicStatDTO statDTO) {
        if (!recentAccountSet.isEmpty()) {
            List<DynaTopicCommentDTO> commentList = Lists.newArrayListWithExpectedSize(recentAccountSet.size());
            dto.setCommentList(commentList);
            for (Integer userId : recentAccountSet) {
                DynaTopicCommentDTO commentDTO = new DynaTopicCommentDTO();
                commentDTO.setUserId(userId);
                commentList.add(commentDTO);
            }
        } else if (statDTO != null) {
            List<DynaTopicCommentDTO> commentList = Lists.newArrayListWithExpectedSize(3);
            dto.setCommentList(commentList);
            if (statDTO.getFirstUserId().equals(0)) {
                return;
            }
            DynaTopicCommentDTO firstCommentDTO = new DynaTopicCommentDTO();
            firstCommentDTO.setUserId(statDTO.getFirstUserId());
            commentList.add(firstCommentDTO);
            if (statDTO.getSecondUserId().equals(0)) {
                return;
            }
            DynaTopicCommentDTO secendCommentDTO = new DynaTopicCommentDTO();
            secendCommentDTO.setUserId(statDTO.getSecondUserId());
            commentList.add(secendCommentDTO);
            if (statDTO.getThirdUserId().equals(0)) {
                return;
            }
            DynaTopicCommentDTO thirdCommentDTO = new DynaTopicCommentDTO();
            thirdCommentDTO.setUserId(statDTO.getThirdUserId());
            commentList.add(thirdCommentDTO);
        }
    }

    @Override
    public void incrementTopicCount(DynaTopicCommentDTO dto, OperateEnum operate) throws ServiceException {
        try {
            this.incrementCommentCount(dto, operate);
            this.incrementParticipateCount(dto);
            this.incrementRecentUser(dto, operate);
        } catch (Exception e) {
            log.error("更新话题相关缓存异常, dto: {}", dto, e);
            ExceptionUtil.throwServiceException("更新话题相关缓存异常", e);
        }
    }

//    private void addSpecialAndUserComment(DynaTopicCommentDTO dto) {
//        //书大痴Id
//        Integer idiotId = dynaServerConfig.getBookIdiotId();
//        if (dto.getUserId().toString().equals(idiotId + "")) {
//            String redisKey = TopicCacheConstant.TOPIC_COMMENT_IDIOT + dto.getDynaTopicId() + ':' + idiotId;
//            redisTemplate.opsForList().leftPush(redisKey, JSONMapper.json(dto));
//            return;
//        }
//        String redisKey = TopicCacheConstant.TOPIC_COMMENT_USER + dto.getDynaTopicId() + ':' + dto.getUserId();
//        redisTemplate.opsForList().leftPush(redisKey, JSONMapper.json(dto));
//        redisTemplate.expire(redisKey, 5, TimeUnit.MINUTES);
//    }

    private void incrementRecentUser(DynaTopicCommentDTO dto, OperateEnum operate) {
        String redisKey = TopicCacheConstant.TOPIC_RECENT_ACCOUNT + dto.getDynaTopicId();
        if (operate == OperateEnum.ADD) {
            Boolean flag = redisTemplate.expire(redisKey, 7, TimeUnit.DAYS);
            if (flag != null && flag.booleanValue()) {
                zSetOperations.add(redisKey, dto.getUserId(), -(dto.getCreateTime().longValue()));
                zSetOperations.removeRange(redisKey, 3L, -1L);
                return;
            }
            this.setRecentUserFromDbToRedis(dto, redisKey);
            zSetOperations.add(redisKey, dto.getUserId(), -(dto.getCreateTime().longValue()));
            zSetOperations.removeRange(redisKey, 3L, -1L);
            return;
        }
        // 删除操作，直接重新加载
        this.setRecentAccountFromComment(dto, redisKey);
    }

    @Override
    public void incrementTopicCountReply(DynaTopicCommentDTO dto, Long count) throws ServiceException {
        try {
            log.info("{}, {}", dto, count);
        } catch (Exception e) {
            log.error("更新话题相关缓存异常, dto: {}", dto, e);
            ExceptionUtil.throwServiceException("更新话题相关缓存异常", e);
        }
    }

    private void incrementCommentCount(DynaTopicCommentDTO dto, OperateEnum operate) {
        long delta;
        if (operate == OperateEnum.ADD) {
            delta = 1;
        } else {
            delta = -1;
        }
        String redisKey = TopicCacheConstant.TOPIC_COMMENT_NUM + dto.getDynaTopicId();
        Boolean flag = redisTemplate.expire(redisKey, 7, TimeUnit.DAYS);
        if (flag != null && flag.booleanValue()) {
            valueOperations.increment(redisKey, delta);
            return;
        }
        DynaTopicStatDTO statDTO = dynaTopicStatService.findByTopicId(dto.getDynaTopicId());
        if (statDTO == null) {
            log.error("需要从数据库加载统计信息时查无记录, dto: {}", dto);
            return;
        }
        valueOperations.setIfAbsent(redisKey, statDTO.getNumComment());
        redisTemplate.expire(redisKey, 7, TimeUnit.DAYS);
        valueOperations.increment(redisKey, delta);
    }

    private void incrementParticipateCount(DynaTopicCommentDTO dto) {
        String redisKey = TopicCacheConstant.TOPIC_PARTICIPATE_NUM + dto.getDynaTopicId();
        long count = dynaTopicCommentAppService.getCommentCountGroupByAccount(dto.getDynaTopicId());
        valueOperations.set(redisKey, count, 7, TimeUnit.DAYS);
    }

    /**
     * 存在并发问题未考虑
     *
     * @param dto
     * @param redisKey
     */
    private void setRecentUserFromDbToRedis(DynaTopicCommentDTO dto, String redisKey) {
        DynaTopicStatDTO statDTO = dynaTopicStatService.findByTopicId(dto.getDynaTopicId());
        if (statDTO == null) {
            log.error("需要从数据库加载统计信息时查无记录, dto: {}", dto);
            return;
        }
        Integer firstUserId = statDTO.getFirstUserId();
        if (firstUserId.intValue() == 0) {
            return;
        }
        zSetOperations.add(redisKey, firstUserId, 1L);
        Integer secondUserId = statDTO.getSecondUserId();
        if (secondUserId.intValue() == 0) {
            return;
        }
        zSetOperations.add(redisKey, secondUserId, 2L);
        Integer thirdUserId = statDTO.getThirdUserId();
        if (thirdUserId.intValue() == 0) {
            return;
        }
        zSetOperations.add(redisKey, thirdUserId, 3L);
        redisTemplate.expire(redisKey, 1, TimeUnit.DAYS);
    }

    /**
     * 存在并发问题未考虑
     *
     * @param dto
     * @param redisKey
     */
    private void setRecentAccountFromComment(DynaTopicCommentDTO dto, String redisKey) {
        redisTemplate.delete(redisKey);
        List<DynaTopicCommentDTO> list = dynaTopicCommentAppService.getRecentCommentUserList(dto.getDynaTopicId());
        if (list.isEmpty()) {
            return;
        }
        DynaTopicCommentDTO firstDTO = list.get(0);
        zSetOperations.add(redisKey, firstDTO.getUserId(), 1L);
        if (list.size() < 2) {
            return;
        }
        DynaTopicCommentDTO secendDTO = list.get(1);
        zSetOperations.add(redisKey, secendDTO.getUserId(), 2L);
        if (list.size() < 3) {
            return;
        }
        DynaTopicCommentDTO thirdDTO = list.get(2);
        zSetOperations.add(redisKey, thirdDTO.getUserId(), 3L);
        redisTemplate.expire(redisKey, 1, TimeUnit.DAYS);
    }

    @Override
    public void incrementTopicCount(DynaTopicCommentReplyDTO dto, OperateEnum operate) throws ServiceException {
        String redisKey = TopicCacheConstant.TOPIC_REPLY_NUM + dto.getDynaTopicId();
        Boolean flag = redisTemplate.expire(redisKey, 1, TimeUnit.DAYS);
        if (flag) {
            this.incrementByRedisKey(redisKey, operate);
            return;
        }
        // 从数据库更新
        DynaTopicStatDTO statDTO = dynaTopicStatService.findByTopicId(dto.getDynaTopicId());
        if (statDTO == null) {
            log.error("需要从数据库加载统计信息时查无记录, dto: {}", dto);
            return;
        }
        valueOperations.setIfAbsent(redisKey, statDTO.getNumReply());
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

    private class IdHelper {
        LinkedHashSet<Integer> topicIdSet;
        Set<Integer> topicIdForCountSet;
        Set<Integer> topicIdForParticipateCountSet;
        Set<Integer> accountIdSet;

        public IdHelper(int topicListSize) {
            topicIdSet = Sets.newLinkedHashSetWithExpectedSize(topicListSize);
            topicIdForCountSet = Sets.newHashSet();
            topicIdForParticipateCountSet = Sets.newHashSet();
            accountIdSet = Sets.newHashSet();
        }
    }
}
