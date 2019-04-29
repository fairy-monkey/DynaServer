package com.geeboo.dyna.server.controller.squire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.geeboo.auth.client.annotation.IgnoreClientToken;
import com.geeboo.auth.client.annotation.IgnoreUserToken;
import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.PageRestRequest;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.dyna.server.client.dto.squire.DynaSquireCommentDTO;
import com.geeboo.dyna.server.client.dto.squire.DynaSquireCommentListDTO;
import com.geeboo.dyna.server.service.squire.IDynaSquireCommentAppService;

import io.swagger.annotations.Api;

@IgnoreUserToken
@IgnoreClientToken
@Api(tags = "动态_广场评论表相关接口}")
@RestController
@RequestMapping("/facade/dyna/squire")
public class DynaSquireCommentAppController {
    @Autowired
    private IDynaSquireCommentAppService dynaSquireCommentAppService;

    /**
     * 增加动态_广场评论表
     *
     * @param dto 需要插入的实体
     * @return
     */
    @RequestMapping(value = "/comment/app", method = RequestMethod.POST)
    BaseResponse addComment(@RequestBody DynaSquireCommentDTO dto) {
        return dynaSquireCommentAppService.addComment(dto);
    }

    /**
     * 删除动态_广场评论表
     *
     * @param dto 包含系统主键
     * @return
     */
    @RequestMapping(value = "/comment/app", method = RequestMethod.DELETE)
    BaseResponse deleteComment(@RequestBody DynaSquireCommentDTO dto) {
        return dynaSquireCommentAppService.deleteComment(dto);
    }

    /**
     * 更新评论
     *
     * @param dto
     * @return
     */
    @RequestMapping(value = "/comment/app/update", method = RequestMethod.POST)
    BaseResponse updateComment(@RequestBody DynaSquireCommentDTO dto) {
        return dynaSquireCommentAppService.updateComment(dto);
    }

    /**
     * 分页查询
     *
     * @param pageRestRequest 分页条件，当前页，每页显示条数
     * @return
     */
    @RequestMapping(value = "/comment/app/page", method = RequestMethod.POST)
    TableResultResponse<DynaSquireCommentListDTO> getCommentPage(
                @RequestBody PageRestRequest<DynaSquireCommentListDTO> pageRestRequest) {
        return dynaSquireCommentAppService.page(pageRestRequest.getData(), pageRestRequest.getPage());
    }


    @RequestMapping(value = "/comment/app/getById", method = RequestMethod.POST)
    ObjectResponse<DynaSquireCommentDTO> getById(@RequestBody DynaSquireCommentDTO dto) {
        return dynaSquireCommentAppService.getById(dto);
    }

}
