package com.geeboo.dyna.server.service.impl.course;

import com.geeboo.cache.service.IRedisService;
import com.geeboo.common.bean.BeanUtils;
import com.geeboo.common.constant.StatusEnum;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.util.DateUtil;
import com.geeboo.dyna.server.client.dto.course.DynaCourseCommentFavorDTO;
import com.geeboo.dyna.server.client.dto.course.DynaCourseFavorDTO;
import com.geeboo.dyna.server.constant.CourseCacheConstant;
import com.geeboo.dyna.server.constant.OperateEnum;
import com.geeboo.dyna.server.entity.course.DynaCourseFavorDO;
import com.geeboo.dyna.server.mapper.course.IDynaCourseFavorMapper;
import com.geeboo.dyna.server.service.course.IDynaCourseCommentAppService;
import com.geeboo.dyna.server.service.course.IDynaCourseFavorAppService;
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
public class DynaCourseFavorAppServiceImpl implements IDynaCourseFavorAppService {
    @Autowired
    private IRedisService redisService;
    @Autowired
    private IDynaCourseCommentAppService dynaCourseCommentAppService;
    @Autowired
    private IDynaCourseFavorMapper dynaCourseFavorMapper;

    @Transactional
    @Override
    public ObjectResponse<Boolean> doFavor(DynaCourseCommentFavorDTO dto) {
        String lockKey =
            CourseCacheConstant.COURSE_COMMENT_LOCK_PREFIX + dto.getCourseId() + ":" + dto.getDynaCourseCommentId() + ":"
                + dto.getUserId();
        Optional<String> identifier = redisService.lock(lockKey, 3, TimeUnit.SECONDS);
        if (!identifier.isPresent()) {
            return ObjectResponse.failure(StatusEnum.ILLEGAL_STATE.getStatus(), "操作太频繁了，休息一下吧");
        }
        try {
            DynaCourseFavorDTO favorDTO =
                dynaCourseFavorMapper.getFavorByCommentAndUser(dto.getCourseId(), dto.getDynaCourseCommentId(), dto.getUserId());
            if (favorDTO == null) {
                if (dto.getOperateType().intValue() == 0) {
                    return ObjectResponse.failure(StatusEnum.VERIFICATION_FAILURE.getStatus(), "未点赞不能取消点赞");
                }
                favorDTO = newDynaCourseFavorDTO(dto);
                dynaCourseFavorMapper.add(favorDTO);
                dynaCourseCommentAppService.incrementCommentCount(dto,
                    dto.getOperateType().intValue() == 1 ? OperateEnum.ADD : OperateEnum.DELETE);
                return new ObjectResponse().data(true).rel(true);
            }
            if (favorDTO.getIsFavor().intValue() == dto.getOperateType().intValue()) {
                return ObjectResponse.failure(StatusEnum.VERIFICATION_FAILURE.getStatus(), "已经点赞/取消点赞，不可重复操作");
            }
            favorDTO.setIsFavor(dto.getOperateType());
            favorDTO.setModifyTime(DateUtil.getCurrentTime());
            DynaCourseFavorDO favorDO = new DynaCourseFavorDO();
            BeanUtils.copyIgnoreNull(favorDTO, favorDO);
            dynaCourseFavorMapper.updateByPrimaryKeySelective(favorDO);
            dynaCourseCommentAppService.incrementCommentCount(dto,
                dto.getOperateType().intValue() == 1 ? OperateEnum.ADD : OperateEnum.DELETE);
            return new ObjectResponse().data(false).rel(true);
        } finally {
            redisService.unlock(lockKey, identifier.get());
        }
    }

    private DynaCourseFavorDTO newDynaCourseFavorDTO(DynaCourseCommentFavorDTO dto) {
        DynaCourseFavorDTO favorDTO = new DynaCourseFavorDTO();
        favorDTO.setCourseId(dto.getCourseId());
        favorDTO.setDynaCourseCommentId(dto.getDynaCourseCommentId());
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
        List<DynaCourseFavorDTO> list = dynaCourseFavorMapper.findCommentFavorListByUser(commentIdSet, userId);
        Set<Integer> commentIdHasFavorSet = Sets.newHashSetWithExpectedSize(list.size());
        for (DynaCourseFavorDTO dto : list) {
            commentIdHasFavorSet.add(dto.getDynaCourseCommentId());
        }
        return commentIdHasFavorSet;
    }
}
