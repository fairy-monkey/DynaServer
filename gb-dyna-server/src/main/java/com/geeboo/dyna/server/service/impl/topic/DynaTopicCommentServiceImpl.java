package com.geeboo.dyna.server.service.impl.topic;

import com.geeboo.cache.service.IRedisService;
import com.geeboo.common.constant.StatusEnum;
import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.common.page.Page;
import com.geeboo.dyna.server.client.dto.topic.DynaTopicCommentDTO;
import com.geeboo.dyna.server.constant.CacheConstant;
import com.geeboo.dyna.server.constant.TopicCacheConstant;
import com.geeboo.dyna.server.entity.topic.DynaTopicCommentDO;
import com.geeboo.dyna.server.mapper.topic.IDynaTopicCommentMapper;
import com.geeboo.dyna.server.service.topic.IDynaTopicCommentReplyService;
import com.geeboo.dyna.server.service.topic.IDynaTopicCommentService;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class DynaTopicCommentServiceImpl implements IDynaTopicCommentService {
    @Autowired
    private IDynaTopicCommentReplyService dynaTopicCommentReplyService;
    @Autowired
    private IRedisService redisService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ValueOperations valueOperations;
    @Autowired
    private IDynaTopicCommentMapper dynaTopicCommentMapper;

    @Transactional
    @Override
    public BaseResponse add(DynaTopicCommentDTO dto) {
        dynaTopicCommentMapper.add(dto);
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }

    @Transactional
    @Override
    public BaseResponse update(DynaTopicCommentDTO dto) {
        dynaTopicCommentMapper.update(dto);
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }

    @Transactional
    @Override
    public BaseResponse delete(Integer dynaTopicCommentId) {
        String lockKey = TopicCacheConstant.TOPIC_COMMENT_LOCK_PREFIX + dynaTopicCommentId;
        Optional<String> identifier = redisService.lock(lockKey, 3, TimeUnit.SECONDS);
        if (!identifier.isPresent()) {
            return BaseResponse.failure(StatusEnum.ILLEGAL_STATE.getStatus(), "其他用户正在操作该评论，请稍后再试");
        }
        try {
            DynaTopicCommentDO commentDO = dynaTopicCommentMapper.selectByPrimaryKey(dynaTopicCommentId);
            if (commentDO == null) {
                return BaseResponse.failure(StatusEnum.NOT_FOUND.getStatus(), "评论不存在");
            }
            dynaTopicCommentReplyService.deleteByCommentId(dynaTopicCommentId);
            dynaTopicCommentMapper.deleteDynaTopicComment(dynaTopicCommentId);
        } finally {
            redisService.unlock(lockKey, identifier.get());
        }
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }

    @Override
    public TableResultResponse<DynaTopicCommentDTO> page(DynaTopicCommentDTO dto, Page<DynaTopicCommentDTO> page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        List<DynaTopicCommentDTO> list = dynaTopicCommentMapper.query(dto);
        com.github.pagehelper.Page<DynaTopicCommentDTO> result = (com.github.pagehelper.Page<DynaTopicCommentDTO>) list;
        TableResultResponse<DynaTopicCommentDTO> response = new TableResultResponse<>(result.getTotal(), result.getPages(), result.getResult());
        return response;
    }

    @Override
    public List<DynaTopicCommentDTO> queryList(DynaTopicCommentDTO dto) {
        return dynaTopicCommentMapper.query(dto);
    }

    @Override
    public ObjectResponse queryCommentList(DynaTopicCommentDTO dto) {
        List<DynaTopicCommentDTO> list = dynaTopicCommentMapper.query(dto);
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, DynaTopicCommentDTO> map = new LinkedHashMap<>();
        List<DynaTopicCommentDTO> newList = new ArrayList<>();
        for (DynaTopicCommentDTO dynaTopicCommentDTO : list) {
            int userId = dynaTopicCommentDTO.getUserId().intValue();
            // 这里需要对同一个用户的评论进行过滤
            if (map.get(userId + "") != null) {
                continue;
            } else {
                map.put(userId + "", dynaTopicCommentDTO);
                newList.add(dynaTopicCommentDTO);
            }
        }
        resultMap.put("list", newList);
        resultMap.put("commentNum", list.size());
        ObjectResponse response = new ObjectResponse();
        response.setData(resultMap);
        return response;
    }

    @Override
    public ObjectResponse<DynaTopicCommentDTO> findById(Integer id) {
        DynaTopicCommentDTO dto = dynaTopicCommentMapper.findById(id);
        if (dto != null) {
            ObjectResponse<DynaTopicCommentDTO> response = new ObjectResponse<>();
            return response.data(dto);
        } else {
            return ObjectResponse.failure(StatusEnum.FAILURE.getStatus(), StatusEnum.FAILURE.getDescribe());
        }
    }

    @Override
    public ObjectResponse<DynaTopicCommentDTO> findByCondition(DynaTopicCommentDTO dto) {
        DynaTopicCommentDTO model = dynaTopicCommentMapper.findByCondition(dto);
        if (model != null) {
            ObjectResponse<DynaTopicCommentDTO> response = new ObjectResponse<>();
            return response.data(model);
        } else {
            return ObjectResponse.failure(StatusEnum.FAILURE.getStatus(), StatusEnum.FAILURE.getDescribe());
        }
    }

    @Transactional
    @Override
    public BaseResponse hiddenComment(DynaTopicCommentDTO dto) {
        DynaTopicCommentDO commentDO = dynaTopicCommentMapper.selectByPrimaryKey(dto.getDynaTopicCommentId());
        if (commentDO == null) {
            return BaseResponse.failure(StatusEnum.NOT_FOUND.getStatus(), "评论不存在");
        }
        commentDO.setIsDel(dto.getIsDel());
        dynaTopicCommentMapper.updateByPrimaryKeySelective(commentDO);
        return BaseResponse.success("操作成功");
    }

    @Transactional
    @Override
    public BaseResponse setFavorNum(DynaTopicCommentDTO dto) {
        String lockKey = TopicCacheConstant.TOPIC_COMMENT_LOCK_PREFIX + dto.getDynaTopicCommentId();
        Optional<String> identifier = redisService.lock(lockKey, 3, TimeUnit.SECONDS);
        if (!identifier.isPresent()) {
            return BaseResponse.failure(StatusEnum.ILLEGAL_STATE.getStatus(), "其他用户正在操作该评论，请稍后再试");
        }
        try {
            DynaTopicCommentDO commentDO = dynaTopicCommentMapper.selectByPrimaryKey(dto.getDynaTopicCommentId());
            if (commentDO == null) {
                return BaseResponse.failure(StatusEnum.NOT_FOUND.getStatus(), "评论不存在");
            }
            commentDO.setNumFavor(dto.getNumFavor());
            valueOperations.set(TopicCacheConstant.TOPIC_COMMENT_FAVOR_NUM + dto.getDynaTopicCommentId(),
                    dto.getNumFavor());
            dynaTopicCommentMapper.updateByPrimaryKeySelective(commentDO);
            log.info("设置点赞数, dto: {}", dto);
        } finally {
            redisService.unlock(lockKey, identifier.get());
        }
        return BaseResponse.success("设置成功");
    }

    @Transactional
    @Override
    public BaseResponse flushRedisToDb() {
        Optional<String> identifier = redisService.lock(TopicCacheConstant.TOPIC_COMMENT_FLUSH_DB_LOCK, 3,
                TimeUnit.MINUTES);
        if (!identifier.isPresent()) {
            return BaseResponse.failure(StatusEnum.ILLEGAL_STATE.getStatus(), StatusEnum.ILLEGAL_STATE.getDescribe());
        }
        try {
            this.flushReplyNum();
            this.flushFavorNum();
        } finally {
            redisService.unlock(TopicCacheConstant.TOPIC_COMMENT_FLUSH_DB_LOCK, identifier.get());
        }
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }

    @Override
    public Integer queryToppicCommentNum(Integer dynaTopicId) {
        int numComment = 0;
        try {
            numComment = dynaTopicCommentMapper.queryToppicCommentNum(dynaTopicId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return numComment;
    }

    @Override
    public List<DynaTopicCommentDTO> getRecentCommentUserList(Integer dynaTopicId) {
        List<DynaTopicCommentDTO> recentCommentUserList = dynaTopicCommentMapper.getRecentCommentUserList(dynaTopicId);
        return recentCommentUserList;
    }

    private void flushReplyNum() {
        Set<String> keySet = redisTemplate.keys(TopicCacheConstant.TOPIC_COMMENT_REPLY_NUM + CacheConstant.KEYS_SUFFIX);
        if (keySet.isEmpty()) {
            return;
        }
        List<DynaTopicCommentDO> list = Lists.newArrayListWithExpectedSize(keySet.size());
        for (String redisKey : keySet) {
            Integer commentId = Integer.parseInt(redisKey.substring(redisKey.lastIndexOf(":") + 1, redisKey.length()));
            DynaTopicCommentDO commentDO = new DynaTopicCommentDO();
            commentDO.setDynaTopicCommentId(commentId);
            Integer value = (Integer) valueOperations.get(TopicCacheConstant.TOPIC_COMMENT_REPLY_NUM + commentId);
            if (value == null) {
                continue;
            }
            commentDO.setNumReply(value);
            list.add(commentDO);
        }
        dynaTopicCommentMapper.batchUpdateReplyNum(list);
    }

    private void flushFavorNum() {
        Set<String> keySet = redisTemplate.keys(TopicCacheConstant.TOPIC_COMMENT_FAVOR_NUM + CacheConstant.KEYS_SUFFIX);
        if (keySet.isEmpty()) {
            return;
        }
        List<DynaTopicCommentDO> list = Lists.newArrayListWithExpectedSize(keySet.size());
        for (String redisKey : keySet) {
            Integer commentId = Integer.parseInt(redisKey.substring(redisKey.lastIndexOf(":") + 1, redisKey.length()));
            DynaTopicCommentDO commentDO = new DynaTopicCommentDO();
            commentDO.setDynaTopicCommentId(commentId);
            Integer value = (Integer) valueOperations.get(TopicCacheConstant.TOPIC_COMMENT_FAVOR_NUM + commentId);
            if (value == null) {
                continue;
            }
            commentDO.setNumFavor(value);
            list.add(commentDO);
        }
        dynaTopicCommentMapper.batchUpdateFavorNum(list);
    }


}
