package com.geeboo.dyna.server.controller.squire;

import com.geeboo.dyna.server.service.squire.IDynaSquireComplaintAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.geeboo.auth.client.annotation.IgnoreClientToken;
import com.geeboo.auth.client.annotation.IgnoreUserToken;
import com.geeboo.common.msg.BaseResponse;
import com.geeboo.dyna.server.client.dto.squire.DynaSquireComplaintDTO;

import io.swagger.annotations.Api;

/**
 * Title: <br>
 * Description: Copyright: Copyright (c) 2018
 *
 * @author 林诗炀 linsy 创建时间:2018/11/19 10:09
 */
@IgnoreUserToken
@IgnoreClientToken
@Api(tags = "动态_广场APP相关接口}")
@RestController
@RequestMapping("/facade/dyna/squire/complaint/app")
public class DynaSquireComplaintAppController {
    @Autowired
    private IDynaSquireComplaintAppService dynaSquireComplaintAppService;

    @PostMapping(value = "/complaint")
    public BaseResponse complaint(@RequestBody DynaSquireComplaintDTO dto) {
        return dynaSquireComplaintAppService.complaint(dto);
    }
}
