package com.geeboo.dyna.server.controller.book;

import com.geeboo.auth.client.annotation.IgnoreClientToken;
import com.geeboo.auth.client.annotation.IgnoreUserToken;
import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.PageRestRequest;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.dyna.server.client.dto.book.DynaBookCommentReplyDTO;
import com.geeboo.dyna.server.client.dto.book.DynaBookReplyListDTO;
import com.geeboo.dyna.server.service.book.IDynaBookCommentReplyAppService;
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
@RequestMapping("/facade/dyna/book/reply/app")
public class DynaBookCommentReplyAppController {
    @Autowired
    private IDynaBookCommentReplyAppService dynaBookCommentReplyAppService;

    @PostMapping(value = "/getReplyPage")
    public TableResultResponse<DynaBookReplyListDTO> getReplyPage(
                @RequestBody PageRestRequest<DynaBookReplyListDTO> page) {
        return dynaBookCommentReplyAppService.getReplyPage(page);
    }

    /**
     * 添加回复
     *
     * @param dto
     * @return
     */
    @PostMapping(value = "/reply")
    public ObjectResponse<DynaBookCommentReplyDTO> addReply(@RequestBody DynaBookCommentReplyDTO dto) {
        return dynaBookCommentReplyAppService.addReply(dto);
    }

    /**
     * 删除回复
     *
     * @param dto
     * @return
     */
    @DeleteMapping(value = "/reply")
    public BaseResponse deleteReply(@RequestBody DynaBookCommentReplyDTO dto) {
        return dynaBookCommentReplyAppService.deleteReply(dto);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    BaseResponse updateReply(@RequestBody DynaBookCommentReplyDTO dto) {
        return dynaBookCommentReplyAppService.updateReply(dto);
    }
}
