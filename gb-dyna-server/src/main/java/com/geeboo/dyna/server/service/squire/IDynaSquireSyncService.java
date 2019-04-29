package com.geeboo.dyna.server.service.squire;

import com.geeboo.common.msg.BaseResponse;

/**
 * Title: <br>
 * Description: Copyright: Copyright (c) 2018
 *
 * @author 郭明毅 guomy 创建时间:2018/10/12 11:39
 */
public interface IDynaSquireSyncService {

    /**
     * 广场统计缓存入库
     *
     * @return
     */
    BaseResponse flushSquireStatToDb();

    /**
     * 用户广场统计缓存入库
     *
     * @return
     */
    BaseResponse flushUserStatToDb();
}
