package com.geeboo.dyna.server.service.impl.book;

import com.geeboo.cache.service.IRedisService;
import com.geeboo.common.constant.StatusEnum;
import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.common.page.Page;
import com.geeboo.dyna.server.client.dto.book.DynaBookCommentDTO;
import com.geeboo.dyna.server.constant.BookCacheConstant;
import com.geeboo.dyna.server.constant.CacheConstant;
import com.geeboo.dyna.server.entity.book.DynaBookCommentDO;
import com.geeboo.dyna.server.mapper.book.IDynaBookCommentMapper;
import com.geeboo.dyna.server.service.book.IDynaBookCommentReplyService;
import com.geeboo.dyna.server.service.book.IDynaBookCommentService;
import com.github.pagehelper.PageHelper;
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

@Slf4j
@Service
public class DynaBookCommentServiceImpl implements IDynaBookCommentService {
    @Autowired
    private IDynaBookCommentReplyService dynaBookCommentReplyService;
    @Autowired
    private IRedisService redisService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ValueOperations valueOperations;
    @Autowired
    private IDynaBookCommentMapper dynaBookCommentMapper;

    @Transactional
    @Override
    public BaseResponse add(DynaBookCommentDTO dto) {
        dynaBookCommentMapper.add(dto);
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }

    @Transactional
    @Override
    public BaseResponse update(DynaBookCommentDTO dto) {
        dynaBookCommentMapper.update(dto);
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }

    @Transactional
    @Override
    public BaseResponse delete(Integer dynaBookCommentId) {
        String lockKey = BookCacheConstant.BOOK_COMMENT_LOCK_PREFIX + dynaBookCommentId;
        Optional<String> identifier = redisService.lock(lockKey, 3, TimeUnit.SECONDS);
        if (!identifier.isPresent()) {
            return BaseResponse.failure(StatusEnum.ILLEGAL_STATE.getStatus(), "其他用户正在操作该评论，请稍后再试");
        }
        try {
            DynaBookCommentDO commentDO = dynaBookCommentMapper.selectByPrimaryKey(dynaBookCommentId);
            if (commentDO == null) {
                return BaseResponse.failure(StatusEnum.NOT_FOUND.getStatus(), "评论不存在");
            }
            dynaBookCommentReplyService.deleteByCommentId(dynaBookCommentId);
            dynaBookCommentMapper.deleteDynaBookComment(dynaBookCommentId);
        } finally {
            redisService.unlock(lockKey, identifier.get());
        }
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }

    @Override
    public TableResultResponse<DynaBookCommentDTO> page(DynaBookCommentDTO dto, Page<DynaBookCommentDTO> page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        List<DynaBookCommentDTO> list = dynaBookCommentMapper.query(dto);
        com.github.pagehelper.Page<DynaBookCommentDTO> result = (com.github.pagehelper.Page<DynaBookCommentDTO>) list;
        TableResultResponse<DynaBookCommentDTO> response = new TableResultResponse<>(result.getTotal(),
                result.getResult());
        return response;
    }

    @Override
    public TableResultResponse<DynaBookCommentDTO> query(DynaBookCommentDTO dto) {
        List<DynaBookCommentDTO> list = dynaBookCommentMapper.query(dto);
        TableResultResponse<DynaBookCommentDTO> response = new TableResultResponse<>(list.size(), list);
        return response;
    }

    @Override
    public List<DynaBookCommentDTO> queryList(DynaBookCommentDTO dto) {
        return dynaBookCommentMapper.query(dto);
    }

    @Override
    public ObjectResponse<DynaBookCommentDTO> findById(Integer id) {
        DynaBookCommentDTO dto = dynaBookCommentMapper.findById(id);
        if (dto != null) {
            ObjectResponse<DynaBookCommentDTO> response = new ObjectResponse<>();
            return response.data(dto);
        } else {
            return ObjectResponse.failure(StatusEnum.FAILURE.getStatus(), StatusEnum.FAILURE.getDescribe());
        }
    }

    @Override
    public ObjectResponse<DynaBookCommentDTO> findByCondition(DynaBookCommentDTO dto) {
        DynaBookCommentDTO model = dynaBookCommentMapper.findByCondition(dto);
        if (null != model) {
            ObjectResponse<DynaBookCommentDTO> response = new ObjectResponse<>();
            return response.data(model);
        } else {
            return ObjectResponse.failure(StatusEnum.FAILURE.getStatus(), StatusEnum.FAILURE.getDescribe());
        }
    }

    @Transactional
    @Override
    public BaseResponse hiddenComment(DynaBookCommentDTO dto) {
        DynaBookCommentDO commentDO = dynaBookCommentMapper.selectByPrimaryKey(dto.getDynaBookCommentId());
        if (commentDO == null) {
            return BaseResponse.failure(StatusEnum.NOT_FOUND.getStatus(), "评论不存在");
        }
        commentDO.setIsDel(dto.getIsDel());
        dynaBookCommentMapper.updateByPrimaryKeySelective(commentDO);
        return BaseResponse.success(dto.getIsDel() == 1 ? "隐藏成功" : "显示成功");
    }

    @Transactional
    @Override
    public BaseResponse setFavorNum(DynaBookCommentDTO dto) {
        String lockKey = BookCacheConstant.BOOK_COMMENT_LOCK_PREFIX + dto.getDynaBookCommentId();
        Optional<String> identifier = redisService.lock(lockKey, 3, TimeUnit.SECONDS);
        if (!identifier.isPresent()) {
            return BaseResponse.failure(StatusEnum.ILLEGAL_STATE.getStatus(), "其他用户正在操作该评论，请稍后再试");
        }
        try {
            DynaBookCommentDO commentDO = dynaBookCommentMapper.selectByPrimaryKey(dto.getDynaBookCommentId());
            if (commentDO == null) {
                return BaseResponse.failure(StatusEnum.NOT_FOUND.getStatus(), "评论不存在");
            }
            commentDO.setNumFavor(dto.getNumFavor());
            valueOperations.set(BookCacheConstant.BOOK_COMMENT_FAVOR_NUM + dto.getDynaBookCommentId(),
                    dto.getNumFavor());
            dynaBookCommentMapper.updateByPrimaryKeySelective(commentDO);
            log.info("设置点赞数, dto: {}", dto);
        } finally {
            redisService.unlock(lockKey, identifier.get());
        }
        return BaseResponse.success("设置成功");
    }

    @Override
    public BaseResponse flushRedisToDb() {
        Optional<String> identifier = redisService.lock(BookCacheConstant.BOOK_COMMENT_FLUSH_DB_LOCK, 3,
                TimeUnit.MINUTES);
        if (!identifier.isPresent()) {
            return BaseResponse.failure(StatusEnum.ILLEGAL_STATE.getStatus(), StatusEnum.ILLEGAL_STATE.getDescribe());
        }
        try {
            this.flushReplyNum();
            this.flushFavorNum();
        } finally {
            redisService.unlock(BookCacheConstant.BOOK_COMMENT_FLUSH_DB_LOCK, identifier.get());
        }
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }

    @Transactional
    @Override
    public BaseResponse updateComment(DynaBookCommentDTO dto) {
        dynaBookCommentMapper.update(dto);
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
            DynaBookCommentDO commentDO = new DynaBookCommentDO();
            commentDO.setDynaBookCommentId(commentId);
            Integer value = (Integer) valueOperations.get(BookCacheConstant.BOOK_COMMENT_REPLY_NUM + commentId);
            if (value == null) {
                continue;
            }
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
        List<DynaBookCommentDO> list = Lists.newArrayListWithExpectedSize(keySet.size());
        for (String redisKey : keySet) {
            Integer commentId = Integer.parseInt(redisKey.substring(redisKey.lastIndexOf(":") + 1, redisKey.length()));
            DynaBookCommentDO commentDO = new DynaBookCommentDO();
            commentDO.setDynaBookCommentId(commentId);
            Integer value = (Integer) valueOperations.get(BookCacheConstant.BOOK_COMMENT_FAVOR_NUM + commentId);
            if (value == null) {
                continue;
            }
            commentDO.setNumFavor(value);
            list.add(commentDO);
        }
        dynaBookCommentMapper.batchUpdateFavorNum(list);
    }
}
