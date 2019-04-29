package com.geeboo.dyna.server.client.facade.course;

import com.geeboo.common.msg.BaseResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Title: 动态书评同步<br>
 * Description: Copyright: Copyright (c) 2018
 *
 * @author 郭明毅 guomy 创建时间:2018/9/28 10:19
 */
@FeignClient(value = "${gb-dyna-server}", configuration = {})
public interface IDynaCourseSyncFacade {
    /**
     * 定时缓存入库
     *
     * @return
     */
    @PostMapping(value = "/DynaServer/facade/dyna/course/sync/timeToFlushRedisToDb")
    BaseResponse timeToFlushRedisToDb();
}
