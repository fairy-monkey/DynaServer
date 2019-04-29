package com.geeboo.dyna.server.client.facade.sensitivestatistic;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.dyna.server.client.dto.senstivestatistic.SenstiveStatisticDTO;

/**
 * @author tangwei
 * @Title: ISensitiveStatisticFacade
 * @ProjectName DynaServer
 * @Description: 敏感词统计feign接口
 * @date 2018/11/30 17:39
 */
@FeignClient(value = "${gb-dyna-server}", configuration = {})
public interface ISensitiveStatisticFacade {
    @RequestMapping(value = "/DynaServer/facade/dyna/sensitivestatistic/getList", method = RequestMethod.POST)
    TableResultResponse getList(@RequestBody SenstiveStatisticDTO dto);

    @RequestMapping(value = "/DynaServer/facade/dyna/sensitivestatistic/getNoSensitiveList", method = RequestMethod.POST)
    TableResultResponse getNoSensitiveList(@RequestBody SenstiveStatisticDTO dto);

    @RequestMapping(value = "/DynaServer/facade/dyna/sensitivestatistic/getCount", method = RequestMethod.POST)
    ObjectResponse getCount(@RequestBody SenstiveStatisticDTO dto);

    @RequestMapping(value = "/DynaServer/facade/dyna/sensitivestatistic/getNoSensitiveCount", method = RequestMethod.POST)
    ObjectResponse getNoSensitiveCount(@RequestBody SenstiveStatisticDTO dto);

    @RequestMapping(value = "/DynaServer/facade/dyna/sensitivestatistic/update", method = RequestMethod.POST)
    BaseResponse update(@RequestBody SenstiveStatisticDTO dto);
}
