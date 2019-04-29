package com.geeboo.dyna.server.controller.book;

import com.geeboo.auth.client.annotation.IgnoreClientToken;
import com.geeboo.auth.client.annotation.IgnoreUserToken;
import com.geeboo.common.msg.BaseResponse;
import com.geeboo.dyna.server.service.book.IDynaBookSyncService;
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
@RequestMapping("/facade/dyna/book/sync")
public class DynaBookSyncController {
    @Autowired
    private IDynaBookSyncService dynaBookSyncService;

    /**
     * 定时缓存入库
     *
     * @return
     */
    @PostMapping(value = "/timeToFlushRedisToDb")
    public BaseResponse timeToFlushRedisToDb() {
        return dynaBookSyncService.timeToFlushRedisToDb();
    }
}
