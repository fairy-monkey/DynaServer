package com.geeboo.dyna.server.controller.digest;

import com.geeboo.auth.client.annotation.IgnoreClientToken;
import com.geeboo.common.msg.PageRestRequest;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.dyna.server.client.dto.digest.DynaDigestListDTO;
import com.geeboo.dyna.server.service.digest.IDynaDigestAppService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description：
 * @author：luozh
 * @date：2018/9/28 19:01
 */
@IgnoreClientToken
@Api(tags = "动态_书摘APP相关接口}")
@RestController
@RequestMapping("/facade/dyna/digest/app")
public class DynaDigestAppController {
    @Autowired
    private IDynaDigestAppService dynaDigestAppService;

    @PostMapping(value = "/getDigestPage")
    public TableResultResponse<DynaDigestListDTO> getDigestPage(@RequestBody PageRestRequest<DynaDigestListDTO> page) {
        return dynaDigestAppService.getDigestPage(page);
    }

}
