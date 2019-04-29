package com.geeboo.dyna.server.service.impl.course;

import com.geeboo.cache.service.IRedisService;
import com.geeboo.common.constant.StatusEnum;
import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.common.page.Page;
import com.geeboo.dyna.server.client.dto.course.DynaCourseCommentDTO;
import com.geeboo.dyna.server.constant.CourseCacheConstant;
import com.geeboo.dyna.server.constant.CacheConstant;
import com.geeboo.dyna.server.entity.course.DynaCourseCommentDO;
import com.geeboo.dyna.server.mapper.course.IDynaCourseCommentMapper;
import com.geeboo.dyna.server.service.course.IDynaCourseCommentReplyService;
import com.geeboo.dyna.server.service.course.IDynaCourseCommentService;
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
public class DynaCourseCommentServiceImpl implements IDynaCourseCommentService {
    @Autowired
    private IDynaCourseCommentReplyService dynaCourseCommentReplyService;
    @Autowired
    private IRedisService redisService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ValueOperations valueOperations;
    @Autowired
    private IDynaCourseCommentMapper dynaCourseCommentMapper;

    @Transactional
    @Override
    public BaseResponse add(DynaCourseCommentDTO dto) {
        dynaCourseCommentMapper.add(dto);
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }

    @Transactional
    @Override
    public BaseResponse update(DynaCourseCommentDTO dto) {
        dynaCourseCommentMapper.update(dto);
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }

    @Transactional
    @Override
    public BaseResponse delete(Integer dynaCourseCommentId) {
        String lockKey = CourseCacheConstant.COURSE_COMMENT_LOCK_PREFIX + dynaCourseCommentId;
        Optional<String> identifier = redisService.lock(lockKey, 3, TimeUnit.SECONDS);
        if (!identifier.isPresent()) {
            return BaseResponse.failure(StatusEnum.ILLEGAL_STATE.getStatus(), "其他用户正在操作该评论，请稍后再试");
        }
        try {
            DynaCourseCommentDO commentDO = dynaCourseCommentMapper.selectByPrimaryKey(dynaCourseCommentId);
            if (commentDO == null) {
                return BaseResponse.failure(StatusEnum.NOT_FOUND.getStatus(), "评论不存在");
            }
            dynaCourseCommentReplyService.deleteByCommentId(dynaCourseCommentId);
            dynaCourseCommentMapper.deleteDynaCourseComment(dynaCourseCommentId);
        } finally {
            redisService.unlock(lockKey, identifier.get());
        }
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }

    @Override
    public TableResultResponse<DynaCourseCommentDTO> page(DynaCourseCommentDTO dto, Page<DynaCourseCommentDTO> page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        List<DynaCourseCommentDTO> list = dynaCourseCommentMapper.queryCourse(dto);
        com.github.pagehelper.Page<DynaCourseCommentDTO> result = (com.github.pagehelper.Page<DynaCourseCommentDTO>) list;
        TableResultResponse<DynaCourseCommentDTO> response =
                new TableResultResponse<>(result.getTotal(), result.getResult());
        return response;
    }

    @Override
    public TableResultResponse<DynaCourseCommentDTO> query(DynaCourseCommentDTO dto) {
        List<DynaCourseCommentDTO> list = dynaCourseCommentMapper.query(dto);
        com.github.pagehelper.Page<DynaCourseCommentDTO> result = (com.github.pagehelper.Page<DynaCourseCommentDTO>) list;
        TableResultResponse<DynaCourseCommentDTO> response =
                new TableResultResponse<>(result.getTotal(), result.getResult());
        return response;
    }

    @Override
    public List<DynaCourseCommentDTO> queryList(DynaCourseCommentDTO dto) {
        return dynaCourseCommentMapper.query(dto);
    }

    @Override
    public ObjectResponse<DynaCourseCommentDTO> findById(Integer id) {
        DynaCourseCommentDTO dto = dynaCourseCommentMapper.findById(id);
        if (dto != null) {
            ObjectResponse<DynaCourseCommentDTO> response = new ObjectResponse<>();
            return response.data(dto);
        } else {
            return ObjectResponse.failure(StatusEnum.FAILURE.getStatus(), StatusEnum.FAILURE.getDescribe());
        }
    }

    @Override
    public ObjectResponse<DynaCourseCommentDTO> findByCondition(DynaCourseCommentDTO dto) {
        DynaCourseCommentDTO model = dynaCourseCommentMapper.findByCondition(dto);
        if (model != null) {
            ObjectResponse<DynaCourseCommentDTO> response = new ObjectResponse<>();
            return response.data(model);
        } else {
            return ObjectResponse.failure(StatusEnum.FAILURE.getStatus(), StatusEnum.FAILURE.getDescribe());
        }
    }

    @Transactional
    @Override
    public BaseResponse hiddenComment(DynaCourseCommentDTO dto) {
        DynaCourseCommentDO commentDO = dynaCourseCommentMapper.selectByPrimaryKey(dto.getDynaCourseCommentId());
        if (commentDO == null) {
            return BaseResponse.failure(StatusEnum.NOT_FOUND.getStatus(), "评论不存在");
        }
        commentDO.setIsDel(dto.getIsDel());
        dynaCourseCommentMapper.updateByPrimaryKeySelective(commentDO);
        return BaseResponse.success(dto.getIsDel() == 1 ? "隐藏成功" : "显示成功");
    }

    @Transactional
    @Override
    public BaseResponse setFavorNum(DynaCourseCommentDTO dto) {
        String lockKey = CourseCacheConstant.COURSE_COMMENT_LOCK_PREFIX + dto.getDynaCourseCommentId();
        Optional<String> identifier = redisService.lock(lockKey, 3, TimeUnit.SECONDS);
        if (!identifier.isPresent()) {
            return BaseResponse.failure(StatusEnum.ILLEGAL_STATE.getStatus(), "其他用户正在操作该评论，请稍后再试");
        }
        try {
            DynaCourseCommentDO commentDO = dynaCourseCommentMapper.selectByPrimaryKey(dto.getDynaCourseCommentId());
            if (commentDO == null) {
                return BaseResponse.failure(StatusEnum.NOT_FOUND.getStatus(), "评论不存在");
            }
            commentDO.setNumFavor(dto.getNumFavor());
            valueOperations.set(CourseCacheConstant.COURSE_COMMENT_FAVOR_NUM + dto.getDynaCourseCommentId(), dto.getNumFavor());
            dynaCourseCommentMapper.updateByPrimaryKeySelective(commentDO);
            log.info("设置点赞数, dto: {}", dto);
        } finally {
            redisService.unlock(lockKey, identifier.get());
        }
        return BaseResponse.success("设置成功");
    }

    @Override
    public BaseResponse flushRedisToDb() {
        Optional<String> identifier = redisService.lock(CourseCacheConstant.COURSE_COMMENT_FLUSH_DB_LOCK, 3, TimeUnit.MINUTES);
        if (!identifier.isPresent()) {
            return BaseResponse.failure(StatusEnum.ILLEGAL_STATE.getStatus(), StatusEnum.ILLEGAL_STATE.getDescribe());
        }
        try {
            this.flushReplyNum();
            this.flushFavorNum();
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
        dynaCourseCommentMapper.batchUpdateFavorNum(list);
    }
}
