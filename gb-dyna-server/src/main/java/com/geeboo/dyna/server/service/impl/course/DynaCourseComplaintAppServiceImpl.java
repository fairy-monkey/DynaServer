package com.geeboo.dyna.server.service.impl.course;

import com.geeboo.cache.service.IRedisService;
import com.geeboo.common.constant.StatusEnum;
import com.geeboo.common.msg.BaseResponse;
import com.geeboo.dyna.server.client.dto.course.DynaCourseComplaintDTO;
import com.geeboo.dyna.server.constant.CourseCacheConstant;
import com.geeboo.dyna.server.mapper.course.IDynaCourseComplaintMapper;
import com.geeboo.dyna.server.service.course.IDynaCourseComplaintAppService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * Title: <br>
 * Description: Copyright: Copyright (c) 2018
 *
 * @author 郭明毅 guomy 创建时间:2018/9/19 11:17
 */
@Slf4j
@Service
public class DynaCourseComplaintAppServiceImpl implements IDynaCourseComplaintAppService {
    @Autowired
    private IRedisService redisService;
    @Autowired
    private IDynaCourseComplaintMapper dynaCourseComplaintMapper;

    @Override
    public BaseResponse complaint(DynaCourseComplaintDTO dto) {
        String lockKey = CourseCacheConstant.COURSE_COMPLAINT_LOCK_PREFIX + dto.getCourseId() + ":" + dto.getUserId();
        Optional<String> identifier = redisService.lock(lockKey, 3, TimeUnit.SECONDS);
        if (!identifier.isPresent()) {
            return BaseResponse.failure(StatusEnum.ILLEGAL_STATE.getStatus(), "操作太快了，休息一下吧");
        }
        try {
            Long count = dynaCourseComplaintMapper.countComplaint(dto);
            // 查询是否重复举报
            if (count != null && count.longValue() > 0L) {
                return BaseResponse.failure(StatusEnum.VERIFICATION_FAILURE.getStatus(), "举报正在处理中，无法重复举报");
            }
            dto.setComplaintStatus(0);
            dynaCourseComplaintMapper.add(dto);
        } finally {
            redisService.unlock(lockKey, identifier.get());
        }
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }
}
