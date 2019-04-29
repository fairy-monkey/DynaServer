package com.geeboo.dyna.server.controller.squire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.geeboo.auth.client.annotation.IgnoreClientToken;
import com.geeboo.auth.client.annotation.IgnoreUserToken;
import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.PageRestRequest;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.dyna.server.client.dto.squire.DynaSquireCommentListDTO;
import com.geeboo.dyna.server.service.squire.IDynaSquireCommentService;

import io.swagger.annotations.Api;

@IgnoreUserToken
@IgnoreClientToken
@Api(tags = "动态_广场评论表相关接口}")
@RestController
@RequestMapping("/facade/dyna/squireComment")
public class DynaSquireCommentController {

    @Autowired
    private IDynaSquireCommentService dynaSquireCommentService;


    /**
     * 分页查询
     *
     * @param pageRestRequest 分页条件，当前页，每页显示条数
     * @return
     */
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    TableResultResponse<DynaSquireCommentListDTO> page(
            @RequestBody PageRestRequest<DynaSquireCommentListDTO> pageRestRequest) {
        return dynaSquireCommentService.page(pageRestRequest.getData(), pageRestRequest.getPage());
    }


    /**
     * 删除动态_广场评论表
     *
     * @param dto 包含系统主键
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    BaseResponse deleteComment(@RequestBody DynaSquireCommentListDTO dto) {
        return dynaSquireCommentService.delete(dto);
    }




}
