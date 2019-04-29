package com.geeboo.dyna.server.service.impl.topic;

import com.geeboo.cache.service.IRedisService;
import com.geeboo.common.bean.BeanUtils;
import com.geeboo.common.constant.StatusEnum;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.util.DateUtil;
import com.geeboo.dyna.server.client.dto.topic.DynaTopicCommentFavorDTO;
import com.geeboo.dyna.server.client.dto.topic.DynaTopicFavorDTO;
import com.geeboo.dyna.server.constant.OperateEnum;
import com.geeboo.dyna.server.constant.TopicCacheConstant;
import com.geeboo.dyna.server.entity.topic.DynaTopicFavorDO;
import com.geeboo.dyna.server.mapper.topic.IDynaTopicFavorMapper;
import com.geeboo.dyna.server.service.topic.IDynaTopicCommentAppService;
import com.geeboo.dyna.server.service.topic.IDynaTopicFavorAppService;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Title: <br>
 * Description: Copyright: Copyright (c) 2018
 *
 * @author 郭明毅 guomy 创建时间:2018/9/17 19:54
 */
@Slf4j
@Service
public class DynaTopicFavorAppServiceImpl implements IDynaTopicFavorAppService {
    @Autowired
    private IRedisService redisService;
    @Autowired
    private IDynaTopicCommentAppService dynaTopicCommentAppService;
    @Autowired
    private IDynaTopicFavorMapper dynaTopicFavorMapper;

    @Transactional
    @Override
    public ObjectResponse<Boolean> doFavor(DynaTopicCommentFavorDTO dto) {
        //话题评论锁：topic:comment: dynaTopicId:dynaTopicCommentId:userId
        String lockKey = TopicCacheConstant.TOPIC_COMMENT_LOCK_PREFIX + dto.getDynaTopicId() + ":" + dto.getDynaTopicCommentId() + ":" + dto.getUserId();
        Optional<String> identifier = redisService.lock(lockKey, 3, TimeUnit.SECONDS);
        if (!identifier.isPresent()) {
            return ObjectResponse.failure(StatusEnum.ILLEGAL_STATE.getStatus(), "操作太频繁了，休息一下吧");
        }
        try {
            DynaTopicFavorDTO favorDTO = dynaTopicFavorMapper.getFavorByCommentAndUser(dto.getDynaTopicId(), dto.getDynaTopicCommentId(), dto.getUserId());
            //话题点赞表中不存在记录
            if (favorDTO == null) {
                //操作类型：0，取消点赞
                if (dto.getOperateType().intValue() == 0) {
                    return ObjectResponse.failure(StatusEnum.VERIFICATION_FAILURE.getStatus(), "未点赞不能取消点赞");
                }
                //操作类型：1，新增点赞
                favorDTO = newDynaTopicFavorDTO(dto);
                dynaTopicFavorMapper.add(favorDTO);
                dynaTopicCommentAppService.incrementCommentCount(dto, dto.getOperateType().intValue() == 1 ? OperateEnum.ADD : OperateEnum.DELETE);
                return new ObjectResponse(StatusEnum.SUCCESS.getStatus(), StatusEnum.SUCCESS.getDescribe()).data(true).rel(true);
            }
            if (favorDTO.getIsFavor().intValue() == dto.getOperateType().intValue()) {
                return ObjectResponse.failure(StatusEnum.VERIFICATION_FAILURE.getStatus(), "已经点赞/取消点赞，不可重复操作");
            }
            favorDTO.setIsFavor(dto.getOperateType());
            favorDTO.setModifyTime(DateUtil.getCurrentTime());
            DynaTopicFavorDO favorDO = new DynaTopicFavorDO();
            BeanUtils.copyIgnoreNull(favorDTO, favorDO);
            dynaTopicFavorMapper.updateByPrimaryKeySelective(favorDO);
            dynaTopicCommentAppService.incrementCommentCount(dto, dto.getOperateType().intValue() == 1 ? OperateEnum.ADD : OperateEnum.DELETE);
            return new ObjectResponse(StatusEnum.SUCCESS.getStatus(), StatusEnum.SUCCESS.getDescribe()).data(false).rel(true);
        } finally {
            redisService.unlock(lockKey, identifier.get());
        }
    }

    private DynaTopicFavorDTO newDynaTopicFavorDTO(DynaTopicCommentFavorDTO dto) {
        DynaTopicFavorDTO favorDTO = new DynaTopicFavorDTO();
        favorDTO.setDynaTopicId(dto.getDynaTopicId());
        favorDTO.setDynaTopicCommentId(dto.getDynaTopicCommentId());
        favorDTO.setUserId(dto.getUserId());
        favorDTO.setIsFavor(dto.getOperateType());
        favorDTO.setCreateBy(dto.getUserId());
        favorDTO.setCreateTime(DateUtil.getCurrentTime());
        favorDTO.setModifyBy(0);
        favorDTO.setModifyTime(BigInteger.ZERO);
        return favorDTO;
    }

    @Override
    public Set<Integer> findCommentFavorListByUser(Set<Integer> commentIdSet, Integer userId) {
        List<DynaTopicFavorDTO> list = dynaTopicFavorMapper.findCommentFavorListByUser(commentIdSet, userId);
        Set<Integer> commentIdHasFavorSet = Sets.newHashSetWithExpectedSize(list.size());
        for (DynaTopicFavorDTO dto : list) {
            commentIdHasFavorSet.add(dto.getDynaTopicCommentId());
        }
        return commentIdHasFavorSet;
    }
}
