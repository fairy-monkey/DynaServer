package com.geeboo.dyna.server.service.impl.topic;

import com.geeboo.cache.service.IRedisService;
import com.geeboo.common.bean.BeanUtils;
import com.geeboo.common.constant.StatusEnum;
import com.geeboo.common.exception.util.ExceptionUtil;
import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.common.page.Page;
import com.geeboo.common.util.DateUtil;
import com.geeboo.dyna.server.client.dto.topic.*;
import com.geeboo.dyna.server.constant.CacheConstant;
import com.geeboo.dyna.server.constant.TopicCacheConstant;
import com.geeboo.dyna.server.entity.topic.DynaTopicDO;
import com.geeboo.dyna.server.entity.topic.DynaTopicStatDO;
import com.geeboo.dyna.server.mapper.topic.IDynaTopicMapper;
import com.geeboo.dyna.server.service.topic.IDynaTopicCommentService;
import com.geeboo.dyna.server.service.topic.IDynaTopicContentService;
import com.geeboo.dyna.server.service.topic.IDynaTopicService;
import com.geeboo.dyna.server.service.topic.IDynaTopicStatService;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class DynaTopicServiceImpl implements IDynaTopicService {
    @Autowired
    private IDynaTopicContentService dynaTopicContentService;
    @Autowired
    private IDynaTopicStatService dynaTopicStatService;
    @Autowired
    private IDynaTopicCommentService dynaTopicCommentService;
    @Autowired
    private IDynaTopicMapper dynaTopicMapper;
    @Autowired
    private IRedisService redisService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ValueOperations valueOperations;
    @Autowired
    private ZSetOperations zSetOperations;

    @Transactional
    @Override
    public BaseResponse add(DynaTopicDTO dto) {
        dto.setBusinessId(0);
        dto.setBackgroundColor("#FFFFFF");
        dto.setIsDel(0);
        if (dto.getIndexNo() == null) {
            dto.setIndexNo(0);
        }
        if (dto.getPublishTime() == null || dto.getPublishTime().equals(BigInteger.ZERO)) {
            dto.setPublishStatus(0);
        } else {
            dto.setPublishStatus(2);
        }
        dynaTopicMapper.add(dto);
        // 内容
        DynaTopicContentDTO contentDTO = new DynaTopicContentDTO();
        contentDTO.setDynaTopicId(dto.getDynaTopicId());
        contentDTO.setDynaTopicContent(dto.getDynaTopicContent());
        contentDTO.setCreateBy(0);
        contentDTO.setCreateTime(BigInteger.ZERO);
        contentDTO.setModifyBy(0);
        contentDTO.setModifyTime(BigInteger.ZERO);
        dynaTopicContentService.add(contentDTO);
        DynaTopicStatDTO statDTO = DynaTopicStatDTO.getNullInstance();
        statDTO.setDynaTopicId(dto.getDynaTopicId());
        dynaTopicStatService.add(statDTO);
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }

    @Transactional
    @Override
    public BaseResponse update(DynaTopicDTO dto) {
        if (dto.getPublishTime() == null || dto.getPublishTime().equals(BigInteger.ZERO)) {
            dto.setPublishStatus(0);
            dto.setPublishTime(BigInteger.ZERO);
        } else {
            dto.setPublishStatus(2);
        }
        dynaTopicMapper.update(dto);
        DynaTopicContentDTO searchDTO = new DynaTopicContentDTO();
        searchDTO.setDynaTopicId(dto.getDynaTopicId());
        ObjectResponse<DynaTopicContentDTO> response = dynaTopicContentService.findByCondition(searchDTO);
        if (response.getData() == null) {
            ExceptionUtil.throwServiceException("内容不存在, id: " + dto.getDynaTopicId());
        }
        DynaTopicContentDTO contentDTO = response.getData();
        contentDTO.setDynaTopicContent(dto.getDynaTopicContent());
        dynaTopicContentService.update(contentDTO);
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }

    @Transactional
    @Override
    public BaseResponse delete(Integer dynaTopicId) {
        DynaTopicDO topicDO = dynaTopicMapper.selectByPrimaryKey(dynaTopicId);
        if (topicDO.getPublishStatus() == 1) {
            return BaseResponse.failure(StatusEnum.ILLEGAL_STATE.getStatus(), "话题已发布，不能删除");
        }
        dynaTopicMapper.delete(topicDO);
        DynaTopicContentDTO contentDTO = dynaTopicContentService.findByTopicId(dynaTopicId);
        dynaTopicContentService.delete(contentDTO.getDynaTopicContentId());
        DynaTopicStatDTO statDTO = dynaTopicStatService.findByTopicId(dynaTopicId);
        dynaTopicContentService.delete(statDTO.getDynaTopicStatId());
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }

    @Override
    public TableResultResponse<DynaTopicDTO> page(DynaTopicDTO dto, Page<DynaTopicDTO> page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        List<DynaTopicDTO> list = dynaTopicMapper.query(dto);
        for (DynaTopicDTO myDynaTopicDTO : list) {
            // 获取话题评论头像 取3笔
            DynaTopicCommentDTO dynaTopicCommentDTO = new DynaTopicCommentDTO(myDynaTopicDTO.getDynaTopicId());
            // 原则上不能连接其他表的mapper，只能通过service来调用, modify by guomingyi
            List<DynaTopicCommentDTO> commentList = dynaTopicCommentService.queryList(dynaTopicCommentDTO);
            List<DynaTopicCommentDTO> indexCommentList = new ArrayList<>();
            if (commentList != null && !commentList.isEmpty()) {
                int length = commentList.size();
                if (length > 3) {
                    length = 3;
                }
                for (int i = 0; i < length; i++) {
                    indexCommentList.add(commentList.get(i));
                }
                myDynaTopicDTO.setCommentList(indexCommentList);
            }
        }
        com.github.pagehelper.Page<DynaTopicDTO> result = (com.github.pagehelper.Page<DynaTopicDTO>) list;
        TableResultResponse<DynaTopicDTO> response = new TableResultResponse<>(result.getTotal(), result.getPages(), result.getResult());
        return response;
    }

    @Override
    public ObjectResponse<DynaTopicDTO> findById(Integer id) {
        DynaTopicDTO dto = dynaTopicMapper.findById(id);
        if (dto != null) {
            DynaTopicContentDTO searchDTO = dynaTopicContentService.findByTopicId(id);
            if (searchDTO != null) {
                dto.setDynaTopicContent(searchDTO.getDynaTopicContent());
            }
            return new ObjectResponse<DynaTopicDTO>().data(dto);
        } else {
            return ObjectResponse.failure(StatusEnum.FAILURE.getStatus(), StatusEnum.FAILURE.getDescribe());
        }
    }

    @Override
    public ObjectResponse<DynaTopicDTO> findByCondition(DynaTopicDTO dto) {
        DynaTopicDTO model = dynaTopicMapper.findByCondition(dto);
        if (null != model) {
            ObjectResponse<DynaTopicDTO> response = new ObjectResponse<>();
            return response.data(model);
        } else {
            return ObjectResponse.failure(StatusEnum.FAILURE.getStatus(), StatusEnum.FAILURE.getDescribe());
        }
    }

    @Override
    public BaseResponse publish(Integer id) {
        String lockKey = TopicCacheConstant.TOPIC_LOCK_PREFIX + id;
        Optional<String> identifier = redisService.lock(lockKey, 3, TimeUnit.SECONDS);
        if (!identifier.isPresent()) {
            return BaseResponse.failure(StatusEnum.ILLEGAL_STATE.getStatus(), "其他用户正在操作该话题，请稍后再试");
        }
        try {
            DynaTopicDO topicDO = dynaTopicMapper.selectByPrimaryKey(id);
            if (topicDO == null) {
                return BaseResponse.failure(StatusEnum.NOT_FOUND.getStatus(), "话题不存在");
            } else if (topicDO.getPublishStatus().intValue() == 1) {
                return BaseResponse.failure(StatusEnum.ILLEGAL_STATE.getStatus(), "话题已上架");
            }
            topicDO.setPublishStatus(1);
            topicDO.setPublishTime(!BigInteger.ZERO.equals(topicDO.getPublishTime())
                    ? topicDO.getPublishTime()
                    : DateUtil.getCurrentTime());
            dynaTopicMapper.updateByPrimaryKeySelective(topicDO);
            // 更新前端排序zset
            DynaTopicDTO dto = new DynaTopicDTO();
            BeanUtils.copyIgnoreNull(topicDO, dto);
            this.setIntoZset(dto);
            // 做content缓存，缓存一天
            DynaTopicContentDTO contentDTO = dynaTopicContentService.findByTopicId(id);
            if (contentDTO != null) {
                valueOperations.set(TopicCacheConstant.TOPIC_CONTENT_PREFIX + id, contentDTO.getDynaTopicContent(), 3,
                        TimeUnit.DAYS);
            }
        } finally {
            redisService.unlock(lockKey, identifier.get());
        }
        return BaseResponse.success("上架成功");
    }

    @Override
    public BaseResponse off(Integer id) {
        String lockKey = TopicCacheConstant.TOPIC_LOCK_PREFIX + id;
        Optional<String> identifier = redisService.lock(lockKey, 3, TimeUnit.SECONDS);
        if (!identifier.isPresent()) {
            return BaseResponse.failure(StatusEnum.ILLEGAL_STATE.getStatus(), "其他用户正在操作该话题，请稍后再试");
        }
        try {
            DynaTopicDO topicDO = dynaTopicMapper.selectByPrimaryKey(id);
            if (topicDO == null) {
                return BaseResponse.failure(StatusEnum.NOT_FOUND.getStatus(), "话题不存在");
            } else if (topicDO.getPublishStatus().intValue() == 0) {
                return BaseResponse.failure(StatusEnum.ILLEGAL_STATE.getStatus(), "话题已下架");
            }
            topicDO.setPublishStatus(0);
            topicDO.setPublishTime(BigInteger.ZERO);
            dynaTopicMapper.updateByPrimaryKeySelective(topicDO);
            // 更新前端排序zset
            DynaTopicDTO dto = new DynaTopicDTO();
            BeanUtils.copyIgnoreNull(topicDO, dto);
            this.setIntoZset(dto);
            // 清除content缓存
            redisTemplate.delete(TopicCacheConstant.TOPIC_CONTENT_PREFIX + id);
        } finally {
            redisService.unlock(lockKey, identifier.get());
        }
        return BaseResponse.success("下架成功");
    }

    @Override
    public BaseResponse recommend(Integer id, Integer sort) {
        if (sort == null || sort < 0) {
            return BaseResponse.failure(StatusEnum.BAD_REQUEST.getStatus(), "排序值必须是正整数");
        }
        String lockKey = TopicCacheConstant.TOPIC_LOCK_PREFIX + id;
        Optional<String> identifier = redisService.lock(lockKey, 3, TimeUnit.SECONDS);
        if (!identifier.isPresent()) {
            return BaseResponse.failure(StatusEnum.ILLEGAL_STATE.getStatus(), "其他用户正在操作该话题，请稍后再试");
        }
        try {
            //根据排序获取话题列表
            List<DynaTopicDTO> list = dynaTopicMapper.findTopicByMinSort(sort);
            int newSort = sort;
            // 要推荐的话题是否在这个列表中
            boolean sortInList = false;
            BigInteger gbTime = DateUtil.getCurrentTime();
            for (DynaTopicDTO topicDTO : list) {
                if (topicDTO.getDynaTopicId().equals(id)) {
                    topicDTO.setIndexNo(sort);
                    topicDTO.setModifyTime(gbTime);
                    topicDTO.setModifyBy(0);
                    sortInList = true;
                    continue;
                }
                topicDTO.setIndexNo(++newSort);
                topicDTO.setModifyTime(gbTime);
                topicDTO.setModifyBy(0);
            }
            if (!sortInList) {
                //
                DynaTopicDO topicDO = dynaTopicMapper.selectByPrimaryKey(id);
                topicDO.setIndexNo(sort);
                dynaTopicMapper.updateByPrimaryKeySelective(topicDO);
            }

            if (!list.isEmpty()) {
                dynaTopicMapper.batchUpdateIndex(list);
            }
            // 所有的sort都变了
            this.initAppTopicSort(null);
        } finally {
            redisService.unlock(lockKey, identifier.get());
        }
        return BaseResponse.success("推荐成功");
    }

    @Override
    public BaseResponse timeToPublish(DynaTopicDTO dto) {
        String lockKey = TopicCacheConstant.TOPIC_LOCK_PREFIX + dto.getDynaTopicId();
        Optional<String> identifier = redisService.lock(lockKey, 3, TimeUnit.SECONDS);
        if (!identifier.isPresent()) {
            return BaseResponse.failure(StatusEnum.ILLEGAL_STATE.getStatus(), "其他用户正在操作该话题，请稍后再试");
        }
        try {
            DynaTopicDO topicDO = dynaTopicMapper.selectByPrimaryKey(dto.getDynaTopicId());
            if (topicDO == null) {
                return BaseResponse.failure(StatusEnum.NOT_FOUND.getStatus(), "话题不存在");
            } else if (topicDO.getPublishStatus().intValue() == 1) {
                return BaseResponse.failure(StatusEnum.ILLEGAL_STATE.getStatus(), "话题已上架");
            }
            topicDO.setPublishStatus(2);
            topicDO.setPublishTime(dto.getPublishTime());
            dynaTopicMapper.updateByPrimaryKeySelective(topicDO);
        } finally {
            redisService.unlock(lockKey, identifier.get());
        }
        return BaseResponse.success("定时发布成功");
    }

    private void setIntoZset(DynaTopicDTO topicDTO) {
        if (topicDTO.getPublishStatus().intValue() != 1 || BigInteger.ZERO.equals(topicDTO.getPublishTime())) {
            zSetOperations.remove(TopicCacheConstant.TOPIC_SORT_ZSET, topicDTO.getDynaTopicId());
            this.updateZsetUUID();
            return;
        }
        Long score;
        if (topicDTO.getIndexNo() == null || topicDTO.getIndexNo() == 0) {
            score = CacheConstant.MAX_NUM_16 - DateUtil.getEpochMilliFromGbTime(topicDTO.getPublishTime());
        } else { // 用倒序的负数来排最小
            score = -(CacheConstant.MAX_NUM_16 - topicDTO.getIndexNo().longValue() * 10);
        }
        zSetOperations.add(TopicCacheConstant.TOPIC_SORT_ZSET, topicDTO.getDynaTopicId(), score);
        this.updateZsetUUID();
    }

    private void updateZsetUUID() {
        Long time = Calendar.getInstance().getTimeInMillis();
        valueOperations.set(TopicCacheConstant.TOPIC_SORT_UUID, time.toString());
    }

    @Override
    public BaseResponse initAppTopicSort(Integer topicId) {
        if (topicId != null) {
            DynaTopicDO topicDO = dynaTopicMapper.selectByPrimaryKey(topicId);
            DynaTopicDTO topicDTO = new DynaTopicDTO();
            BeanUtils.copyIgnoreProperties(topicDO, topicDTO);
            this.setIntoZset(topicDTO);
        } else {
            Optional<String> identifier = redisService.lock(TopicCacheConstant.TOPIC_SORT_ALL_LOCK, 1, TimeUnit.MINUTES);
            if (!identifier.isPresent()) {
                return BaseResponse.failure(StatusEnum.ILLEGAL_STATE.getStatus(), "排序正在进行中，请稍后再试");
            }
            try {
                // 全部排序
                this.removeZset();
                List<DynaTopicDTO> list = dynaTopicMapper.getTopicPublishList(null, 1000);
                Integer lastId = null;
                while (!list.isEmpty()) {
                    for (DynaTopicDTO dto : list) {
                        lastId = dto.getDynaTopicId();
                        this.setIntoZset(dto);
                    }
                    list = dynaTopicMapper.getTopicPublishList(lastId, 1000);
                }
            } finally {
                redisService.unlock(TopicCacheConstant.TOPIC_SORT_ALL_LOCK, identifier.get());
            }
        }
        return BaseResponse.success("排序成功");
    }

    private void removeZset() {
        redisTemplate.delete(TopicCacheConstant.TOPIC_SORT_ZSET);
    }

    @Override
    public BaseResponse timeToPublishTask() {
        List<DynaTopicDTO> list = dynaTopicMapper.getTopicTimeToPublishList(0, 1000);
        Integer lastId = null;
        while (!list.isEmpty()) {
            for (DynaTopicDTO dto : list) {
                lastId = dto.getDynaTopicId();
                this.publish(dto.getDynaTopicId());
            }
            list = dynaTopicMapper.getTopicTimeToPublishList(lastId, 1000);
        }
        return BaseResponse.success("定时任务执行成功");
    }

    @Transactional
    @Override
    public BaseResponse flushRedisToDb() {
        Optional<String> identifier = redisService.lock(TopicCacheConstant.TOPIC_FLUSH_DB_LOCK, 3, TimeUnit.MINUTES);
        if (!identifier.isPresent()) {
            return BaseResponse.failure(StatusEnum.ILLEGAL_STATE.getStatus(), StatusEnum.ILLEGAL_STATE.getDescribe());
        }
        try {
            Map<Integer, DynaTopicStatDO> topicMap = Maps.newHashMap();
            this.buildViewNum(topicMap);
            this.buildCommentNum(topicMap);
            this.buildReplyNum(topicMap);
            this.buildParticipateNum(topicMap);
            this.buildRecentAccount(topicMap);
            // 活跃的话题不会太多，循环就对了
            ObjectResponse<Map<Integer, DynaTopicStatDTO>> objectResponse = dynaTopicStatService
                    .batchGetStatByTopicId(topicMap.keySet());
            if (!objectResponse.isSuccess()) {
                ExceptionUtil.throwServiceException("查询统计信息异常, objectResponse: " + objectResponse);
            }
            Map<Integer, DynaTopicStatDTO> dtoMap = objectResponse.getData();
            for (Map.Entry<Integer, DynaTopicStatDO> entry : topicMap.entrySet()) {
                DynaTopicStatDTO statDTO = dtoMap.get(entry.getKey());
                DynaTopicStatDO statDO = entry.getValue();
                statDO.setDynaTopicStatId(statDTO.getDynaTopicStatId());
            }
            for (DynaTopicStatDO statDO : topicMap.values()) {
                dynaTopicStatService.updateByPrimaryKeySelective(statDO);
            }
        } finally {
            redisService.unlock(TopicCacheConstant.TOPIC_FLUSH_DB_LOCK, identifier.get());
        }
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }

    private void buildViewNum(Map<Integer, DynaTopicStatDO> topicMap) {
        Set<String> keySet = redisTemplate.keys(TopicCacheConstant.TOPIC_VIEW_NUM + CacheConstant.KEYS_SUFFIX);
        if (keySet.isEmpty()) {
            return;
        }
        for (String redisKey : keySet) {
            String idString = redisKey.substring(redisKey.lastIndexOf(":") + 1, redisKey.length());
            if (StringUtils.isBlank(idString)) {
                continue;
            }
            Integer topicId = Integer.parseInt(idString);
            DynaTopicStatDO statDO = topicMap.get(topicId);
            if (statDO == null) {
                statDO = new DynaTopicStatDO();
                topicMap.put(topicId, statDO);
            }
            Integer value = (Integer) valueOperations.get(redisKey);
            statDO.setNumView(value);
        }
    }

    private void buildCommentNum(Map<Integer, DynaTopicStatDO> topicMap) {
        Set<String> keySet = redisTemplate.keys(TopicCacheConstant.TOPIC_COMMENT_NUM + CacheConstant.KEYS_SUFFIX);
        if (keySet.isEmpty()) {
            return;
        }
        for (String redisKey : keySet) {
            String idString = redisKey.substring(redisKey.lastIndexOf(":") + 1, redisKey.length());
            if (StringUtils.isBlank(idString)) {
                continue;
            }
            Integer topicId = Integer.parseInt(idString);
            DynaTopicStatDO statDO = topicMap.get(topicId);
            if (statDO == null) {
                statDO = new DynaTopicStatDO();
                topicMap.put(topicId, statDO);
            }
            Integer value = (Integer) valueOperations.get(redisKey);
            statDO.setNumComment(value);
        }
    }

    private void buildReplyNum(Map<Integer, DynaTopicStatDO> topicMap) {
        Set<String> keySet = redisTemplate.keys(TopicCacheConstant.TOPIC_REPLY_NUM + CacheConstant.KEYS_SUFFIX);
        if (keySet.isEmpty()) {
            return;
        }
        for (String redisKey : keySet) {
            String idString = redisKey.substring(redisKey.lastIndexOf(":") + 1, redisKey.length());
            if (StringUtils.isBlank(idString)) {
                continue;
            }
            Integer topicId = Integer.parseInt(idString);
            DynaTopicStatDO statDO = topicMap.get(topicId);
            if (statDO == null) {
                statDO = new DynaTopicStatDO();
                topicMap.put(topicId, statDO);
            }
            Integer value = (Integer) valueOperations.get(redisKey);
            statDO.setNumReply(value);
        }
    }

    private void buildParticipateNum(Map<Integer, DynaTopicStatDO> topicMap) {
        Set<String> keySet = redisTemplate.keys(TopicCacheConstant.TOPIC_PARTICIPATE_NUM + CacheConstant.KEYS_SUFFIX);
        if (keySet.isEmpty()) {
            return;
        }
        for (String redisKey : keySet) {
            String idString = redisKey.substring(redisKey.lastIndexOf(":") + 1, redisKey.length());
            if (StringUtils.isBlank(idString)) {
                continue;
            }
            Integer topicId = Integer.parseInt(idString);
            DynaTopicStatDO statDO = topicMap.get(topicId);
            if (statDO == null) {
                statDO = new DynaTopicStatDO();
                topicMap.put(topicId, statDO);
            }
            Integer value = (Integer) valueOperations.get(redisKey);
            statDO.setNumTotal(value);
        }
    }

    private void buildRecentAccount(Map<Integer, DynaTopicStatDO> topicMap) {
        Set<String> keySet = redisTemplate.keys(TopicCacheConstant.TOPIC_RECENT_ACCOUNT + CacheConstant.KEYS_SUFFIX);
        if (keySet.isEmpty()) {
            return;
        }
        for (String redisKey : keySet) {
            String idString = redisKey.substring(redisKey.lastIndexOf(":") + 1, redisKey.length());
            if (StringUtils.isBlank(idString)) {
                continue;
            }
            Integer topicId = Integer.parseInt(idString);
            DynaTopicStatDO statDO = topicMap.get(topicId);
            if (statDO == null) {
                statDO = new DynaTopicStatDO();
                topicMap.put(topicId, statDO);
            }
            Set<Integer> accountSet = zSetOperations.range(redisKey, 0L, 2L);
            if (accountSet.isEmpty()) {
                continue;
            }
            List<Integer> accountList = new ArrayList<>(accountSet);
            statDO.setFirstUserId(accountList.get(0));
            if (accountList.size() < 2) {
                continue;
            }
            statDO.setSecondUserId(accountList.get(1));
            if (accountList.size() < 3) {
                continue;
            }
            statDO.setThirdUserId(accountList.get(2));
        }
    }
}
