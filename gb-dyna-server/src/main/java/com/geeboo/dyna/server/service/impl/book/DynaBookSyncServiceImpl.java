package com.geeboo.dyna.server.service.impl.book;

import com.geeboo.cache.service.IRedisService;
import com.geeboo.common.constant.StatusEnum;
import com.geeboo.common.msg.BaseResponse;
import com.geeboo.dyna.server.constant.BookCacheConstant;
import com.geeboo.dyna.server.constant.CacheConstant;
import com.geeboo.dyna.server.entity.book.DynaBookCommentDO;
import com.geeboo.dyna.server.entity.book.DynaBookStatDO;
import com.geeboo.dyna.server.mapper.book.IDynaBookCommentMapper;
import com.geeboo.dyna.server.mapper.book.IDynaBookStatMapper;
import com.geeboo.dyna.server.service.book.IDynaBookSyncService;
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
public class DynaBookSyncServiceImpl implements IDynaBookSyncService {
    @Autowired
    private IRedisService redisService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ValueOperations valueOperations;
    @Autowired
    private IDynaBookCommentMapper dynaBookCommentMapper;
    @Autowired
    private IDynaBookStatMapper dynaBookStatMapper;

    @Transactional
    @Override
    public BaseResponse timeToFlushRedisToDb() {
        Optional<String> identifier = redisService.lock(BookCacheConstant.BOOK_COMMENT_FLUSH_DB_LOCK, 3, TimeUnit.MINUTES);
        if (!identifier.isPresent()) {
            return BaseResponse.failure(StatusEnum.ILLEGAL_STATE.getStatus(), StatusEnum.ILLEGAL_STATE.getDescribe());
        }
        try {
            this.flushReplyNum();
            this.flushFavorNum();
            this.flushCommentNum();
        } finally {
            redisService.unlock(BookCacheConstant.BOOK_COMMENT_FLUSH_DB_LOCK, identifier.get());
        }
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }

    private void flushReplyNum() {
        Set<String> keySet = redisTemplate.keys(BookCacheConstant.BOOK_COMMENT_REPLY_NUM + CacheConstant.KEYS_SUFFIX);
        if (keySet.isEmpty()) {
            return;
        }
        List<DynaBookCommentDO> list = Lists.newArrayListWithExpectedSize(keySet.size());
        for (String redisKey : keySet) {
            Integer commentId = Integer.parseInt(redisKey.substring(redisKey.lastIndexOf(":") + 1, redisKey.length()));
            Integer value = (Integer) valueOperations.get(BookCacheConstant.BOOK_COMMENT_REPLY_NUM + commentId);
            if (value == null) {
                continue;
            }
            DynaBookCommentDO commentDO = new DynaBookCommentDO();
            commentDO.setDynaBookCommentId(commentId);
            commentDO.setNumReply(value);
            list.add(commentDO);
        }
        dynaBookCommentMapper.batchUpdateReplyNum(list);
    }

    private void flushFavorNum() {
        Set<String> keySet = redisTemplate.keys(BookCacheConstant.BOOK_COMMENT_FAVOR_NUM + CacheConstant.KEYS_SUFFIX);
        if (keySet.isEmpty()) {
            return;
        }
        int size = 1000;
        List<DynaBookCommentDO> list = Lists.newArrayListWithExpectedSize(size);
        int count = 0;
        int total = keySet.size();
        for (String redisKey : keySet) {
            Integer commentId = Integer.parseInt(redisKey.substring(redisKey.lastIndexOf(":") + 1, redisKey.length()));
            Integer value = (Integer) valueOperations.get(BookCacheConstant.BOOK_COMMENT_FAVOR_NUM + commentId);
            if (null == value) {
                continue;
            }
            DynaBookCommentDO commentDO = new DynaBookCommentDO();
            commentDO.setDynaBookCommentId(commentId);
            if (value > 0) {
                commentDO.setNumFavor(value);
            } else {
                commentDO.setNumFavor(0);
            }
            list.add(commentDO);
            count++;
            if (list.size() == size) {
                dynaBookCommentMapper.batchUpdateFavorNum(list);
                list = Lists.newArrayListWithExpectedSize(total - count >= size ? size : total - count);
            }
        }
    }

    private void flushCommentNum() {
        Set<String> keySet = redisTemplate.keys(BookCacheConstant.BOOK_COMMENT_NUM + CacheConstant.KEYS_SUFFIX);
        if (keySet.isEmpty()) {
            return;
        }
        List<DynaBookStatDO> list = Lists.newArrayListWithExpectedSize(keySet.size());
        for (String redisKey : keySet) {
            Integer bookUserId = Integer.parseInt(redisKey.substring(redisKey.lastIndexOf(":") + 1, redisKey.length()));
            Integer value = (Integer) valueOperations.get(BookCacheConstant.BOOK_COMMENT_NUM + bookUserId);
            if (null == value) {
                continue;
            }
            DynaBookStatDO statDO = new DynaBookStatDO();
            statDO.setBookUserId(bookUserId);
            statDO.setNumComment(value);
            list.add(statDO);
        }
        dynaBookStatMapper.batchSaveOrUpdateCommentNum(list);
    }
}
