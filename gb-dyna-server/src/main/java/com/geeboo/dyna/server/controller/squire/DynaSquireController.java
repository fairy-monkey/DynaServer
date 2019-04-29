package com.geeboo.dyna.server.controller.squire;

import com.geeboo.auth.client.annotation.IgnoreClientToken;
import com.geeboo.auth.client.annotation.IgnoreUserToken;
import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.PageRestRequest;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.dyna.server.client.dto.squire.DynaSquireDTO;
import com.geeboo.dyna.server.service.squire.IDynaSquireService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@IgnoreUserToken
@IgnoreClientToken
@Api(tags = "动态_广场举报表相关接口}")
@RestController
@RequestMapping("/facade/dyna")
public class DynaSquireController {

    @Autowired
    private IDynaSquireService dynaSquireService;


    /**
     * 分页查询
     *
     * @param pageRestRequest 分页条件，当前页，每页显示条数
     * @return
     */
    @RequestMapping(value = "/squire/page", method = RequestMethod.POST)
    TableResultResponse<DynaSquireDTO> page(@RequestBody PageRestRequest<DynaSquireDTO> pageRestRequest) {
        return dynaSquireService.page(pageRestRequest.getData(), pageRestRequest.getPage());
    }


    /**
     * 删除动态_图书评论表
     *
     * @param dto 包含系统主键
     * @return
     */
    @RequestMapping(value = "/squire/delete", method = RequestMethod.POST)
    BaseResponse delete(@RequestBody DynaSquireDTO dto) {
        Integer dynaSquireId = dto.getDynaSquireId();
        return dynaSquireService.delete(dynaSquireId);
    }


}
