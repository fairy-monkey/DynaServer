package com.geeboo.dyna.server.controller.course;

import com.geeboo.auth.client.annotation.IgnoreClientToken;
import com.geeboo.auth.client.annotation.IgnoreUserToken;
import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.PageRestRequest;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.dyna.server.client.dto.course.DynaCourseCommentReplyDTO;
import com.geeboo.dyna.server.client.dto.course.DynaCourseReplyListDTO;
import com.geeboo.dyna.server.service.course.IDynaCourseCommentReplyAppService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Title: <br>
 * Description: Copyright: Copyright (c) 2018
 *
 * @author 郭明毅 guomy 创建时间:2018/9/14 11:03
 */
@IgnoreUserToken
@IgnoreClientToken
@Api(tags = "动态_评论APP相关接口}")
@RestController
@RequestMapping("/facade/dyna/course/reply/app")
public class DynaCourseCommentReplyAppController {
    @Autowired
    private IDynaCourseCommentReplyAppService dynaCourseCommentReplyAppService;

    @PostMapping(value = "/getReplyPage")
    public TableResultResponse<DynaCourseReplyListDTO> getReplyPage(
                @RequestBody PageRestRequest<DynaCourseReplyListDTO> page) {
        return dynaCourseCommentReplyAppService.getReplyPage(page);
    }

    /**
     * 添加回复
     *
     * @param dto
     * @return
     */
    @PostMapping(value = "/reply")
    public ObjectResponse<DynaCourseCommentReplyDTO> addReply(@RequestBody DynaCourseCommentReplyDTO dto) {
        return dynaCourseCommentReplyAppService.addReply(dto);
    }

    /**
     * 删除回复
     *
     * @param dto
     * @return
     */
    @DeleteMapping(value = "/reply")
    public BaseResponse deleteReply(@RequestBody DynaCourseCommentReplyDTO dto) {
        return dynaCourseCommentReplyAppService.deleteReply(dto);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    BaseResponse updateReply(@RequestBody DynaCourseCommentReplyDTO dto) {
        return dynaCourseCommentReplyAppService.updateReply(dto);
    }
}
