package com.geeboo.dyna.server.controller.sensitivestatistic;

import com.geeboo.auth.client.annotation.IgnoreClientToken;
import com.geeboo.auth.client.annotation.IgnoreUserToken;
import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.dyna.server.client.dto.senstivestatistic.SenstiveStatisticDTO;
import com.geeboo.dyna.server.service.sensitivestatistic.ISensitiveStatisticService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tangwei
 * @Title: SensitivestatisticController
 * @ProjectName DynaServer
 * @Description:
 * @date 2018/11/30 15:55
 */
@IgnoreUserToken
@IgnoreClientToken
@Api(tags = "敏感词通用统计相关接口}")
@RestController
@RequestMapping("/facade/dyna/sensitivestatistic")
public class SensitiveStatisticController {
    @Autowired
    private ISensitiveStatisticService senstiveStatisticService;

    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    public TableResultResponse getList(@RequestBody SenstiveStatisticDTO dto) {
        return senstiveStatisticService.getList(dto);
    }

    @RequestMapping(value = "/getNoSensitiveList", method = RequestMethod.POST)
    public TableResultResponse getNoSensitiveList(@RequestBody SenstiveStatisticDTO dto) {
        return senstiveStatisticService.getNoSensitiveList(dto);
    }

    @RequestMapping(value = "/getCount", method = RequestMethod.POST)
    public ObjectResponse getCount(@RequestBody SenstiveStatisticDTO dto) {
        return senstiveStatisticService.getCount(dto);
    }

    @RequestMapping(value = "/getNoSensitiveCount", method = RequestMethod.POST)
    ObjectResponse getNoSensitiveCount(@RequestBody SenstiveStatisticDTO dto) {
        return senstiveStatisticService.getNoSensitiveCount(dto);
    }

    /**
     * 更新为包含敏感词
     *
     * @param dto 需要修改的实体
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    BaseResponse update(@RequestBody SenstiveStatisticDTO dto) {
        return senstiveStatisticService.update(dto);
    }
}
