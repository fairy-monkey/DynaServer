package com.geeboo.dyna.server.service.impl.squire;

import com.geeboo.cache.service.IRedisService;
import com.geeboo.common.constant.StatusEnum;
import com.geeboo.common.msg.BaseResponse;
import com.geeboo.dyna.server.constant.CacheConstant;
import com.geeboo.dyna.server.constant.SquireCacheConstant;
import com.geeboo.dyna.server.entity.squire.DynaSquireDO;
import com.geeboo.dyna.server.entity.squire.DynaSquireUserStatDO;
import com.geeboo.dyna.server.mapper.squire.IDynaSquireUserStatMapper;
import com.geeboo.dyna.server.service.squire.IDynaSquireSyncService;
import com.geeboo.dyna.server.service.squire.IDynaSquireWriteService;
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
 * @author 郭明毅 guomy 创建时间:2018/10/12 11:41
 */
@Slf4j
@Service
public class DynaSquireSyncServiceImpl implements IDynaSquireSyncService {
    @Autowired
    private IRedisService redisService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ValueOperations valueOperations;
    @Autowired
    private IDynaSquireWriteService dynaSquireWriteService;
    @Autowired
    private IDynaSquireUserStatMapper dynaSquireUserStatMapper;

    @Override
    public BaseResponse flushSquireStatToDb() {
        Optional<String> identifier = redisService.lock(SquireCacheConstant.SQUIRE_STAT_FLUSH_LOCK, 30, TimeUnit.SECONDS);
        if (!identifier.isPresent()) {
            log.warn("广场统计入库没有获得锁，跳过执行");
            return BaseResponse.failure(StatusEnum.ILLEGAL_STATE.getStatus(), StatusEnum.ILLEGAL_STATE.getDescribe());
        }
        try {
            log.info("广场统计缓存入库开始...");
            List<DynaSquireDO> commentNumList = this.getCommentNumList();
            log.info("广场统计评论数入库, commentNumList: {}", commentNumList);
            List<DynaSquireDO> favorNumList = this.getFavorNumList();
            log.info("广场统计点赞数入库, favorNumList: {}", favorNumList);
            if (!commentNumList.isEmpty()) {
                dynaSquireWriteService.batchUpdateCommentNum(commentNumList);
            }
            if (!favorNumList.isEmpty()) {
                dynaSquireWriteService.batchUpdateFavorNum(favorNumList);
            }
        } finally {
            redisService.unlock(SquireCacheConstant.SQUIRE_STAT_FLUSH_LOCK, identifier.get());
        }
        return BaseResponse.success(StatusEnum.FAILURE.getDescribe());
    }

    private List<DynaSquireDO> getCommentNumList() {
        Set<String> commentKeys = redisTemplate.keys(SquireCacheConstant.SQUIRE_COMMENT_NUM_PREFIX + CacheConstant.KEYS_SUFFIX);
        List<Integer> commentNumList = valueOperations.multiGet(commentKeys);
        List<DynaSquireDO> resultList = Lists.newArrayListWithExpectedSize(commentKeys.size());
        int index = 0;
        for (String redisKey : commentKeys) {
            Integer squireId = Integer.parseInt(redisKey.substring(redisKey.lastIndexOf(":") + 1, redisKey.length()));
            DynaSquireDO squireDO = new DynaSquireDO();
            squireDO.setDynaSquireId(squireId);
            Integer commentNum = commentNumList.get(index);
            if (commentNum != null) {
                squireDO.setNumReply(commentNum);
                resultList.add(squireDO);
            }
            index++;
        }
        return resultList;
    }

    private List<DynaSquireDO> getFavorNumList() {
        Set<String> favorKeys = redisTemplate.keys(SquireCacheConstant.SQUIRE_FAVOR_NUM_PREFIX + CacheConstant.KEYS_SUFFIX);
        List<Integer> favorNumList = valueOperations.multiGet(favorKeys);
        List<DynaSquireDO> resultList = Lists.newArrayListWithExpectedSize(favorKeys.size());
        int index = 0;
        for (String redisKey : favorKeys) {
            Integer squireId = Integer.parseInt(redisKey.substring(redisKey.lastIndexOf(":") + 1, redisKey.length()));
            DynaSquireDO squireDO = new DynaSquireDO();
            squireDO.setDynaSquireId(squireId);
            Integer favorNum = favorNumList.get(index);
            if (favorNum != null) {
                squireDO.setNumFavor(favorNum);
                resultList.add(squireDO);
            }
            index++;
        }
        return resultList;
    }

    @Transactional
    @Override
    public BaseResponse flushUserStatToDb() {
        Optional<String> identifier = redisService.lock(SquireCacheConstant.SQUIRE_USER_STAT_FLUSH_LOCK, 30, TimeUnit.SECONDS);
        if (!identifier.isPresent()) {
            log.warn("用户动态统计缓存入库没有获得锁，跳过执行");
            return BaseResponse.failure(StatusEnum.ILLEGAL_STATE.getStatus(), StatusEnum.ILLEGAL_STATE.getDescribe());
        }
        try {
            log.info("用户动态统计缓存入库开始...");
            List<DynaSquireUserStatDO> userStatList = this.getUserStatList();
            log.info("用户动态统计缓存入库, userStatList: {}", userStatList);
            if (!userStatList.isEmpty()) {
                dynaSquireUserStatMapper.batchUpdateUserStat(userStatList);
            }
        } finally {
            redisService.unlock(SquireCacheConstant.SQUIRE_USER_STAT_FLUSH_LOCK, identifier.get());
        }
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }

    private List<DynaSquireUserStatDO> getUserStatList() {
        Set<String> userKeys = redisTemplate.keys(SquireCacheConstant.USER_SQUIRE_NUM_PREFIX + CacheConstant.KEYS_SUFFIX);
        List<Integer> userStatList = valueOperations.multiGet(userKeys);
        List<DynaSquireUserStatDO> resultList = Lists.newArrayListWithExpectedSize(userKeys.size());
        int index = 0;
        for (String redisKey : userKeys) {
            Integer userId = Integer.parseInt(redisKey.substring(redisKey.lastIndexOf(":") + 1, redisKey.length()));
            DynaSquireUserStatDO userStatDO = new DynaSquireUserStatDO();
            userStatDO.setUserId(userId);
            Integer squireNum = userStatList.get(index);
            if (squireNum != null) {
                userStatDO.setNumSquire(squireNum);
                resultList.add(userStatDO);
            }
            index++;
        }
        return resultList;
    }
}
