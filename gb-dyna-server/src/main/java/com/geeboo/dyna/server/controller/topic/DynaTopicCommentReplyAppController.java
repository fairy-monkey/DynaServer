package com.geeboo.dyna.server.controller.topic;

import com.geeboo.auth.client.annotation.IgnoreClientToken;
import com.geeboo.auth.client.annotation.IgnoreUserToken;
import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.PageRestRequest;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.dyna.server.client.dto.topic.DynaTopicCommentReplyDTO;
import com.geeboo.dyna.server.client.dto.topic.DynaTopicReplyListDTO;
import com.geeboo.dyna.server.service.topic.IDynaTopicCommentReplyAppService;
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
@RequestMapping("/facade/dyna/topic/reply/app")
public class DynaTopicCommentReplyAppController {
    @Autowired
    private IDynaTopicCommentReplyAppService dynaTopicCommentReplyAppService;

    @PostMapping(value = "/getReplyPage")
    public TableResultResponse<DynaTopicReplyListDTO> getReplyPage(@RequestBody PageRestRequest<DynaTopicReplyListDTO> page) {
        return dynaTopicCommentReplyAppService.getReplyPage(page);
    }

    /**
     * 添加回复
     * 返回新增回复主键ID，被回复者ID
     * 如果是回复评论，被回复者ID为0，需要自行查找
     *
     * @param dto
     * @return
     */
    @PostMapping(value = "/reply")
    public ObjectResponse<DynaTopicCommentReplyDTO> addReply(@RequestBody DynaTopicCommentReplyDTO dto) {
        return dynaTopicCommentReplyAppService.addReply(dto);
    }

    /**
     * 删除回复
     *
     * @param dto
     * @return
     */
    @DeleteMapping(value = "/reply")
    public BaseResponse deleteReply(@RequestBody DynaTopicCommentReplyDTO dto) {
        return dynaTopicCommentReplyAppService.deleteReply(dto);
    }
}
