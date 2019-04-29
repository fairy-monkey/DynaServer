package com.geeboo.dyna.server.service.impl.squire;

import com.geeboo.cache.service.IRedisService;
import com.geeboo.common.bean.BeanUtils;
import com.geeboo.common.constant.StatusEnum;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.util.DateUtil;
import com.geeboo.dyna.server.client.dto.squire.DynaSquireFavorDTO;
import com.geeboo.dyna.server.client.dto.squire.DynaSquireFavorRequestDTO;
import com.geeboo.dyna.server.constant.OperateEnum;
import com.geeboo.dyna.server.constant.SquireCacheConstant;
import com.geeboo.dyna.server.entity.squire.DynaSquireFavorDO;
import com.geeboo.dyna.server.eventbus.center.squire.SquireEventBusCenter;
import com.geeboo.dyna.server.eventbus.dto.squire.SquireEventDTO;
import com.geeboo.dyna.server.mapper.squire.IDynaSquireFavorMapper;
import com.geeboo.dyna.server.service.squire.IDynaSquireFavorService;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class DynaSquireFavorServiceImpl implements IDynaSquireFavorService {
    @Autowired
    private IRedisService redisService;
    @Autowired
    private IDynaSquireFavorMapper dynaSquireFavorMapper;

    @Override
    public ObjectResponse<Boolean> doFavor(DynaSquireFavorRequestDTO dto) {
        //点赞锁
        String lockKey = SquireCacheConstant.SQUIRE_FAVOR_LOCK_PREFIX + dto.getSquireFavorId() + ":" + dto.getUserId();
        Optional<String> identifier = redisService.lock(lockKey, 3, TimeUnit.SECONDS);
        if (!identifier.isPresent()) {
            return ObjectResponse.failure(StatusEnum.ILLEGAL_STATE.getStatus(), "操作太频繁了，休息一下吧");
        }
        try {
            DynaSquireFavorDTO favorDTO =
                    dynaSquireFavorMapper.getFavorBySquireAndUser(dto.getDynaSquireId(), dto.getUserId());
            if (favorDTO == null) {
                if (dto.getOperateType().intValue() == 0) {
                    return ObjectResponse.failure(StatusEnum.VERIFICATION_FAILURE.getStatus(), "未点赞不能取消点赞");
                }
                favorDTO = newDynaSquireFavorDTO(dto);
                dynaSquireFavorMapper.add(favorDTO);
                // 统计数量
                SquireEventBusCenter.post(new SquireEventDTO().bussinessId(dto.getDynaSquireId()).favorNum(true).operate(
                        dto.getOperateType().intValue() == 1
                                ?
                                OperateEnum.ADD
                                :
                                OperateEnum.DELETE));
                return new ObjectResponse().data(true).rel(true);
            }
            if (favorDTO.getIsFavor().intValue() == dto.getOperateType().intValue()) {
                return ObjectResponse.failure(StatusEnum.VERIFICATION_FAILURE.getStatus(), "已经点赞/取消点赞，不可重复操作");
            }
            favorDTO.setIsFavor(dto.getOperateType());
            favorDTO.setModifyTime(DateUtil.getCurrentTime());
            DynaSquireFavorDO favorDO = new DynaSquireFavorDO();
            BeanUtils.copyIgnoreNull(favorDTO, favorDO);
            dynaSquireFavorMapper.updateByPrimaryKeySelective(favorDO);
            // 统计数量
            SquireEventBusCenter.post(new SquireEventDTO().bussinessId(dto.getDynaSquireId()).favorNum(true).operate(
                    dto.getOperateType().intValue() == 1 ? OperateEnum.ADD : OperateEnum.DELETE));
            return new ObjectResponse().data(false).rel(true);
        } finally {
            redisService.unlock(lockKey, identifier.get());
        }
    }

    private DynaSquireFavorDTO newDynaSquireFavorDTO(DynaSquireFavorRequestDTO dto) {
        DynaSquireFavorDTO favorDTO = new DynaSquireFavorDTO();
        favorDTO.setDynaSquireId(dto.getDynaSquireId());
        favorDTO.setUserId(dto.getUserId());
        favorDTO.setIsFavor(dto.getOperateType());
        favorDTO.setCreateBy(dto.getUserId());
        favorDTO.setCreateTime(DateUtil.getCurrentTime());
        favorDTO.setModifyBy(0);
        favorDTO.setModifyTime(BigInteger.ZERO);
        return favorDTO;
    }

    @Override
    public Set<Integer> findSquireFavorListByUser(Set<Integer> squireIdSet, Integer userId) {
        List<DynaSquireFavorDTO> list = dynaSquireFavorMapper.findSquireFavorListByUser(squireIdSet, userId);
        Set<Integer> idSet = Sets.newHashSetWithExpectedSize(list.size());
        for (DynaSquireFavorDTO dto : list) {
            idSet.add(dto.getDynaSquireId());
        }
        return idSet;
    }

    @Override
    public ObjectResponse<Set<Integer>> findSquireFavorList(Set<Integer> squireIdSet, Integer userId) {
        Set<Integer> idSet = this.findSquireFavorListByUser(squireIdSet, userId);
        return new ObjectResponse<>().data(idSet).rel(true);
    }

    @Override
    public Long countFavorBySquire(Integer squireId) {
        return dynaSquireFavorMapper.countFavorBySquire(squireId);
    }

    @Override
    public ObjectResponse<DynaSquireFavorDTO> getDynaSquireFavorDTO(DynaSquireFavorDTO dto) {
        DynaSquireFavorDTO dynaSquireFavorDTO = dynaSquireFavorMapper.getFavorBySquireAndUser(dto.getDynaSquireId(), dto.getUserId());
        if (null != dynaSquireFavorDTO) {
            return new ObjectResponse<DynaSquireFavorDTO>().data(dynaSquireFavorDTO).rel(true);
        }
        return new ObjectResponse<DynaSquireFavorDTO>().data(null);
    }
}


