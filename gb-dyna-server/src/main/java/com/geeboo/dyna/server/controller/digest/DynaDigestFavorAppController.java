package com.geeboo.dyna.server.controller.digest;


import com.geeboo.auth.client.annotation.IgnoreClientToken;
import com.geeboo.common.msg.BaseResponse;
import com.geeboo.dyna.server.client.dto.digest.DynaDigestFavorDTO;
import com.geeboo.dyna.server.service.digest.IDynaDigestFavorAppService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@IgnoreClientToken
@Api(tags = "动态_书摘APP相关接口}")
@RestController
@RequestMapping("/facade/dyna/digest/app")
public class DynaDigestFavorAppController {
    @Autowired
    IDynaDigestFavorAppService dynaDigestFavorAppService;

    @PostMapping(value = "/digestFavor")
    public BaseResponse digestFavor(@RequestBody DynaDigestFavorDTO dto) {
        return dynaDigestFavorAppService.doDigestFavor(dto);
    }
}