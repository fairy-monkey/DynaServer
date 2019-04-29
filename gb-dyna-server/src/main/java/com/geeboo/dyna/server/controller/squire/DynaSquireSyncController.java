package com.geeboo.dyna.server.controller.squire;

import com.geeboo.auth.client.annotation.IgnoreClientToken;
import com.geeboo.auth.client.annotation.IgnoreUserToken;
import com.geeboo.common.msg.BaseResponse;
import com.geeboo.dyna.server.service.squire.IDynaSquireSyncService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Title: <br>
 * Description: Copyright: Copyright (c) 2018
 *
 * @author 郭明毅 guomy 创建时间:2018/10/12 15:26
 */
@IgnoreUserToken
@IgnoreClientToken
@Api(tags = "动态_广场信息表相关接口}")
@RestController
@RequestMapping("/facade/dyna")
public class DynaSquireSyncController {
    @Autowired
    private IDynaSquireSyncService dynaSquireSyncService;

    @PostMapping("/squire/flushSquireStatToDb")
    public BaseResponse flushSquireStatToDb() {
        return dynaSquireSyncService.flushSquireStatToDb();
    }

    @PostMapping("/squire/flushUserStatToDb")
    public BaseResponse flushUserStatToDb() {
        return dynaSquireSyncService.flushUserStatToDb();
    }
}
