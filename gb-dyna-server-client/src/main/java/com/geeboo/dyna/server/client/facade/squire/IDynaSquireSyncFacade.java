package com.geeboo.dyna.server.client.facade.squire;

import com.geeboo.common.msg.BaseResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Title: <br>
 * Description: Copyright: Copyright (c) 2018
 *
 * @author 郭明毅 guomy 创建时间:2018/10/12 16:14
 */
@FeignClient(value = "${gb-dyna-server}", configuration = {})
public interface IDynaSquireSyncFacade {
    /**
     * 广场统计缓存入库
     *
     * @return
     */
    @RequestMapping(value = "/DynaServer/facade/dyna/squire/flushSquireStatToDb", method = RequestMethod.POST)
    BaseResponse flushSquireStatToDb();

    /**
     * 用户广场统计缓存入库
     *
     * @return
     */
    @RequestMapping(value = "/DynaServer/facade/dyna/squire/flushUserStatToDb", method = RequestMethod.POST)
    BaseResponse flushUserStatToDb();
}
