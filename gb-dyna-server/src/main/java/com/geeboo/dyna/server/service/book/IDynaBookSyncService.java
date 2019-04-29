package com.geeboo.dyna.server.service.book;

import com.geeboo.common.msg.BaseResponse;

/**
 * Title: 动态书评同步<br>
 * Description: Copyright: Copyright (c) 2018
 *
 * @author 郭明毅 guomy 创建时间:2018/9/28 10:11
 */
public interface IDynaBookSyncService {
    /**
     * 定时缓存入库
     *
     * @return
     */
    BaseResponse timeToFlushRedisToDb();
}