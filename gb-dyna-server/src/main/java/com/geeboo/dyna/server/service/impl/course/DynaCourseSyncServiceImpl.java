package com.geeboo.dyna.server.service.impl.course;

import com.geeboo.cache.service.IRedisService;
import com.geeboo.common.constant.StatusEnum;
import com.geeboo.common.msg.BaseResponse;
import com.geeboo.dyna.server.constant.CourseCacheConstant;
import com.geeboo.dyna.server.constant.CacheConstant;
import com.geeboo.dyna.server.entity.course.DynaCourseCommentDO;
import com.geeboo.dyna.server.entity.course.DynaCourseStatDO;
import com.geeboo.dyna.server.mapper.course.IDynaCourseCommentMapper;
import com.geeboo.dyna.server.mapper.course.IDynaCourseStatMapper;
import com.geeboo.dyna.server.service.course.IDynaCourseSyncService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Title: <br>
 * Description: Copyright: Copyright (c) 2018
 *
 * @author 郭明毅 guomy 创建时间:2018/9/28 10:12
 */
@Slf4j
@Service
public class DynaCourseSyncServiceImpl implements IDynaCourseSyncService {
    @Autowired
    private IRedisService redisService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ValueOperations valueOperations;
    @Autowired
    private IDynaCourseCommentMapper dynaCourseCommentMapper;
    @Autowired
    private IDynaCourseStatMapper dynaCourseStatMapper;

    @Transactional
    @Override
    public BaseResponse timeToFlushRedisToDb() {
        Optional<String> identifier = redisService.lock(CourseCacheConstant.COURSE_COMMENT_FLUSH_DB_LOCK, 3, TimeUnit.MINUTES);
        if (!identifier.isPresent()) {
            log.warn("共读圈统计入库没有获得锁，跳过执行");
            return BaseResponse.failure(StatusEnum.ILLEGAL_STATE.getStatus(), StatusEnum.ILLEGAL_STATE.getDescribe());
        }
        log.info("共读圈定时缓存入库...");
        try {
            this.flushReplyNum();
            this.flushFavorNum();
            this.flushCommentNum();
        } finally {
            redisService.unlock(CourseCacheConstant.COURSE_COMMENT_FLUSH_DB_LOCK, identifier.get());
        }
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }

    private void flushReplyNum() {
        Set<String> keySet = redisTemplate.keys(CourseCacheConstant.COURSE_COMMENT_REPLY_NUM + CacheConstant.KEYS_SUFFIX);
        if (keySet.isEmpty()) {
            return;
        }
        List<DynaCourseCommentDO> list = Lists.newArrayListWithExpectedSize(keySet.size());
        for (String redisKey : keySet) {
            Integer commentId = Integer.parseInt(redisKey.substring(redisKey.lastIndexOf(":") + 1, redisKey.length()));
            DynaCourseCommentDO commentDO = new DynaCourseCommentDO();
            commentDO.setDynaCourseCommentId(commentId);
            Integer value = (Integer) valueOperations.get(CourseCacheConstant.COURSE_COMMENT_REPLY_NUM + commentId);
            if (value == null) {
                continue;
            }
            commentDO.setNumReply(value);
            list.add(commentDO);
        }
        log.info("共读圈统计回复数入库, list: {}", list);
        dynaCourseCommentMapper.batchUpdateReplyNum(list);
    }

    private void flushFavorNum() {
        Set<String> keySet = redisTemplate.keys(CourseCacheConstant.COURSE_COMMENT_FAVOR_NUM + CacheConstant.KEYS_SUFFIX);
        if (keySet.isEmpty()) {
            return;
        }
        List<DynaCourseCommentDO> list = Lists.newArrayListWithExpectedSize(keySet.size());
        for (String redisKey : keySet) {
            Integer commentId = Integer.parseInt(redisKey.substring(redisKey.lastIndexOf(":") + 1, redisKey.length()));
            DynaCourseCommentDO commentDO = new DynaCourseCommentDO();
            commentDO.setDynaCourseCommentId(commentId);
            Integer value = (Integer) valueOperations.get(CourseCacheConstant.COURSE_COMMENT_FAVOR_NUM + commentId);
            if (value == null) {
                continue;
            }
            commentDO.setNumFavor(value);
            list.add(commentDO);
        }
        log.info("共读圈统计点赞数入库, list: {}", list);
        dynaCourseCommentMapper.batchUpdateFavorNum(list);
    }

    private void flushCommentNum() {
        Set<String> keySet = redisTemplate.keys(CourseCacheConstant.COURSE_COMMENT_NUM + CacheConstant.KEYS_SUFFIX);
        if (keySet.isEmpty()) {
            return;
        }
        List<DynaCourseStatDO> list = Lists.newArrayListWithExpectedSize(keySet.size());
        for (String redisKey : keySet) {
            Integer courseId = Integer.parseInt(redisKey.substring(redisKey.lastIndexOf(":") + 1, redisKey.length()));
            DynaCourseStatDO statDO = new DynaCourseStatDO();
            statDO.setCourseId(courseId);
            Integer value = (Integer) valueOperations.get(CourseCacheConstant.COURSE_COMMENT_NUM + courseId);
            if (value == null) {
                continue;
            }
            statDO.setNumComment(value);
            list.add(statDO);
        }
        log.info("共读圈统计评论数入库, list: {}", list);
        dynaCourseStatMapper.batchSaveOrUpdateCommentNum(list);
    }
}
