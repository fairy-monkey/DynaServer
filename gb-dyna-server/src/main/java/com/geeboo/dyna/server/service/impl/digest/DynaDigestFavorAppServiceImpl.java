package com.geeboo.dyna.server.service.impl.digest;


import com.geeboo.cache.service.IRedisService;
import com.geeboo.common.constant.StatusEnum;
import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.util.DateUtil;
import com.geeboo.dyna.server.client.dto.digest.DynaDigestFavorDTO;
import com.geeboo.dyna.server.constant.DigestCacheConstant;
import com.geeboo.dyna.server.mapper.digest.IDynaDigestFavorMapper;
import com.geeboo.dyna.server.service.digest.IDynaDigestAppService;
import com.geeboo.dyna.server.service.digest.IDynaDigestFavorAppService;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class DynaDigestFavorAppServiceImpl implements IDynaDigestFavorAppService {
    @Autowired
    private IDynaDigestFavorMapper dynaDigestFavorMapper;

    @Autowired
    IDynaDigestAppService dynaDigestAppService;

    @Autowired
    private IRedisService redisService;
    @Autowired
    private ValueOperations valueOperations;

    /**
     * @description：书摘点赞/取消
     * @author：luozh
     * @date：2018/9/28 15:58
     */
    @Override
    @Transactional
    public BaseResponse doDigestFavor(DynaDigestFavorDTO dto) {

        Integer dynaDigestId = dto.getDynaDigestId();
        Integer userId = dto.getUserId();
        BigInteger currentTime = DateUtil.getCurrentTime();
        Integer isFavor = dto.getIsFavor();

        String lockKey = DigestCacheConstant.DIGEST_FAVOR_LOCK_PREFIX + userId + ":" + dynaDigestId;
        Optional<String> identifier = redisService.lock(lockKey, 3, TimeUnit.SECONDS);
        if (!identifier.isPresent()) {
            return BaseResponse.failure(StatusEnum.ILLEGAL_STATE.getStatus(), "操作太频繁了，休息一下吧");
        }

        try {

            DynaDigestFavorDTO favorDTO = dynaDigestFavorMapper.getFavorByDigestAndUser(dynaDigestId, userId);
            if (favorDTO == null) {
                if (dto.getIsFavor().intValue() == 0) {
                    return BaseResponse.failure(StatusEnum.VERIFICATION_FAILURE.getStatus(), "未点赞不能取消点赞");
                }
                dto.setCreateTime(currentTime);
                dto.setCreateBy(userId);
                dto.setModifyTime(BigInteger.ZERO);
                dto.setModifyBy(0);
                dynaDigestFavorMapper.add(dto);
            } else {
                dto.setModifyBy(userId);
                dto.setModifyTime(currentTime);
                dynaDigestFavorMapper.update(dto);
            }
            dynaDigestAppService.incrementNumSupport(dynaDigestId, isFavor);
            valueOperations.set(DigestCacheConstant.DIGEST_FAVOR_LOCK_PREFIX + userId, dynaDigestId);
            return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
        } finally {
            redisService.unlock(lockKey, identifier.get());
        }
    }

    /**
     * 根据用户Id获取所有点赞的书摘id
     *
     * @param dto
     * @return
     */
    @Override
    public Set<Integer> getDigestIdsByUserId(DynaDigestFavorDTO dto) {
        Integer userId = dto.getUserId();
        List<DynaDigestFavorDTO> list = dynaDigestFavorMapper.getDigestFavorList(userId);
        if (list.isEmpty()) {
            return new TreeSet<>();
        }
        Set<Integer> digestFavorSet = Sets.newHashSetWithExpectedSize(list.size());
        for (DynaDigestFavorDTO digestFavorDTO : list) {
            if (digestFavorDTO != null && digestFavorDTO.getIsFavor() == 1) {
                digestFavorSet.add(digestFavorDTO.getDynaDigestId());
            }
        }
        return digestFavorSet;
    }
}
