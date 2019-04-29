package com.geeboo.dyna.server.service.impl.squire;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geeboo.cache.service.IRedisService;
import com.geeboo.common.constant.StatusEnum;
import com.geeboo.common.msg.BaseResponse;
import com.geeboo.dyna.server.client.dto.squire.DynaSquireComplaintDTO;
import com.geeboo.dyna.server.constant.TopicCacheConstant;
import com.geeboo.dyna.server.mapper.squire.IDynaSquireComplaintMapper;
import com.geeboo.dyna.server.service.squire.IDynaSquireComplaintAppService;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: <br>
 * Description: Copyright: Copyright (c) 2018
 *
 * @author 林诗炀 linsy 创建时间:2018/11/19 10:09
 */
@Slf4j
@Service
public class DynaSquireComplaintAppServiceImpl implements IDynaSquireComplaintAppService {
    @Autowired
    private IRedisService redisService;
    @Autowired
    private IDynaSquireComplaintMapper dynaSquireComplaintMapper;

    @Override
    public BaseResponse complaint(DynaSquireComplaintDTO dto) {
        String lockKey = TopicCacheConstant.TOPIC_COMPLAINT_LOCK_PREFIX + dto.getDynaSquireId() + ":" + dto.getUserId();
        Optional<String> identifier = redisService.lock(lockKey, 3, TimeUnit.SECONDS);
        if (!identifier.isPresent()) {
            return BaseResponse.failure(StatusEnum.ILLEGAL_STATE.getStatus(), "操作太快了，休息一下吧");
        }
        try {
            Long count = dynaSquireComplaintMapper.countComplaint(dto);
            // 查询是否重复举报
            if (count != null && count.longValue() > 0L) {
                return BaseResponse.failure(StatusEnum.VERIFICATION_FAILURE.getStatus(), "举报正在处理中，无法重复举报");
            }
            dto.setComplaintStatus(0);
            dynaSquireComplaintMapper.add(dto);
        } finally {
            redisService.unlock(lockKey, identifier.get());
        }
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }
}
