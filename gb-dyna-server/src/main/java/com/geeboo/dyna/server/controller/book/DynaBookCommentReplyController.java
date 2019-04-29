package com.geeboo.dyna.server.controller.book;

import com.geeboo.auth.client.annotation.IgnoreClientToken;
import com.geeboo.auth.client.annotation.IgnoreUserToken;
import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.PageRestRequest;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.dyna.server.client.dto.book.DynaBookCommentReplyDTO;
import com.geeboo.dyna.server.service.book.IDynaBookCommentReplyService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@IgnoreUserToken
@IgnoreClientToken
@Api(tags = "动态_课程评论回复表相关接口}")
@RestController
@RequestMapping("/facade/dynaBookCommentReply")
public class DynaBookCommentReplyController {
    @Autowired
    private IDynaBookCommentReplyService dynaBookCommentReplyService;

    /**
     * 增加动态_课程评论回复表
     *
     * @param dto 需要插入的实体
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    BaseResponse create(@RequestBody DynaBookCommentReplyDTO dto) {
        return dynaBookCommentReplyService.add(dto);
    }

    /**
     * 修改动态_课程评论回复表
     *
     * @param dto 需要修改的实体
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    BaseResponse update(@RequestBody DynaBookCommentReplyDTO dto) {
        return dynaBookCommentReplyService.update(dto);
    }

    /**
     * 删除动态_课程评论回复表
     *
     * @param dto 包含系统主键
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    BaseResponse delete(@RequestBody DynaBookCommentReplyDTO dto) {
        Integer dynaBookCommentReplyId = dto.getDynaBookCommentReplyId();
        return dynaBookCommentReplyService.delete(dynaBookCommentReplyId);
    }

    /**
     * 分页查询
     *
     * @param pageRestRequest 分页条件，当前页，每页显示条数
     * @return
     */
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    TableResultResponse<DynaBookCommentReplyDTO> page(@RequestBody PageRestRequest<DynaBookCommentReplyDTO> pageRestRequest) {
        return dynaBookCommentReplyService.page(pageRestRequest.getData(), pageRestRequest.getPage());
    }

    /**
     * 普通查询
     *
     * @param dto 查询条件
     * @return
     */
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    TableResultResponse<DynaBookCommentReplyDTO> query(@RequestBody DynaBookCommentReplyDTO dto) {
        return dynaBookCommentReplyService.query(dto);
    }

    /**
     * 通过ID获取动态_课程评论回复表实体
     *
     * @param dto 包含系统主键ID
     * @return
     */
    @RequestMapping(value = "/findById", method = RequestMethod.POST)
    ObjectResponse<DynaBookCommentReplyDTO> findById(@RequestBody DynaBookCommentReplyDTO dto) {
        Integer dynaBookCommentReplyId = dto.getDynaBookCommentReplyId();
        return dynaBookCommentReplyService.findById(dynaBookCommentReplyId);
    }

    /**
     * 通过条件获取动态_课程评论回复表实体
     *
     * @param dto 查询条件
     * @return
     */
    @RequestMapping(value = "/findByCondition", method = RequestMethod.POST)
    ObjectResponse<DynaBookCommentReplyDTO> findByCondition(@RequestBody DynaBookCommentReplyDTO dto) {
        return dynaBookCommentReplyService.findByCondition(dto);
    }
}