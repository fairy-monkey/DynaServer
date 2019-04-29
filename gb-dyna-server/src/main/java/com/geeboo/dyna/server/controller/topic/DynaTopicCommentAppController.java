package com.geeboo.dyna.server.controller.topic;

import com.geeboo.auth.client.annotation.IgnoreClientToken;
import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.PageRestRequest;
import com.geeboo.dyna.server.client.dto.topic.DynaTopicCommentDTO;
import com.geeboo.dyna.server.client.dto.topic.DynaTopicCommentListDTO;
import com.geeboo.dyna.server.client.dto.topic.DynaTopicCommentListResponseDTO;
import com.geeboo.dyna.server.client.dto.topic.DynaTopicReplyListDTO;
import com.geeboo.dyna.server.service.topic.IDynaTopicCommentAppService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * Title: <br>
 * Description: Copyright: Copyright (c) 2018
 *
 * @author 郭明毅 guomy 创建时间:2018/9/14 11:03
 */
@IgnoreClientToken
@Api(tags = "动态_评论APP相关接口}")
@RestController
@RequestMapping("/facade/dyna/topic/comment/app")
public class DynaTopicCommentAppController {
    @Autowired
    private IDynaTopicCommentAppService dynaTopicCommentAppService;

    @PostMapping(value = "/getCommentPage")
    public ObjectResponse<DynaTopicCommentListResponseDTO> getCommentPage(@RequestBody PageRestRequest<DynaTopicCommentListDTO> page) {
        return dynaTopicCommentAppService.getCommentPage(page);
    }

    /**
     * 获取评论详情
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public DynaTopicCommentListDTO getCommentDetail(@PathVariable(value = "id") Integer id) {
        return dynaTopicCommentAppService.getCommentDetail(id);
    }

    @PostMapping(value = "/getCommentDetail")
    public DynaTopicCommentListDTO getCommentDetail(@RequestBody PageRestRequest<DynaTopicReplyListDTO> page) {
        return dynaTopicCommentAppService.getCommentDetail(page);
    }


    /**
     * 添加评论
     *
     * @param dto
     * @return
     */
    @PostMapping(value = "/comment")
    public ObjectResponse<DynaTopicCommentDTO> addComment(@RequestBody DynaTopicCommentDTO dto) {
        return dynaTopicCommentAppService.addComment(dto);
    }

    /**
     * 添加评论
     *
     * @param dto
     * @return
     */
    @DeleteMapping(value = "/comment")
    public BaseResponse deleteComment(@RequestBody DynaTopicCommentDTO dto) {
        return dynaTopicCommentAppService.deleteComment(dto);
    }

}
