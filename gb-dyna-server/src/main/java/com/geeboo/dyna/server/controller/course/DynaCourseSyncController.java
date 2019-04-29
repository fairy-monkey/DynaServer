package com.geeboo.dyna.server.controller.course;

import com.geeboo.auth.client.annotation.IgnoreClientToken;
import com.geeboo.auth.client.annotation.IgnoreUserToken;
import com.geeboo.common.msg.BaseResponse;
import com.geeboo.dyna.server.service.course.IDynaCourseSyncService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Title: <br>
 * Description: Copyright: Copyright (c) 2018
 *
 * @author 郭明毅 guomy 创建时间:2018/9/28 10:26
 */
@IgnoreUserToken
@IgnoreClientToken
@Api(tags = "动态_评论APP相关接口}")
@RestController
@RequestMapping("/facade/dyna/course/sync")
public class DynaCourseSyncController {
    @Autowired
    private IDynaCourseSyncService dynaCourseSyncService;

    /**
     * 定时缓存入库
     *
     * @return
     */
    @PostMapping(value = "/timeToFlushRedisToDb")
    public BaseResponse timeToFlushRedisToDb() {
        return dynaCourseSyncService.timeToFlushRedisToDb();
    }
}
