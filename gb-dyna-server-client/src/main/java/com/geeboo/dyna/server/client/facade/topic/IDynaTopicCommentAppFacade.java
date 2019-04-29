package com.geeboo.dyna.server.client.facade.topic;

import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.PageRestRequest;
import com.geeboo.dyna.server.client.dto.topic.DynaTopicCommentDTO;
import com.geeboo.dyna.server.client.dto.topic.DynaTopicCommentListDTO;
import com.geeboo.dyna.server.client.dto.topic.DynaTopicCommentListResponseDTO;
import com.geeboo.dyna.server.client.dto.topic.DynaTopicReplyListDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;


/**
 * Title: <br>
 * Description: Copyright: Copyright (c) 2018
 *
 * @author 郭明毅 guomy 创建时间:2018/9/14 11:01
 */
@FeignClient(value = "${gb-dyna-server}", configuration = {})
public interface IDynaTopicCommentAppFacade {
    /**
     * 评论分页
     *
     * @param page
     * @return
     */
    @PostMapping(value = "/DynaServer/facade/dyna/topic/comment/app/getCommentPage")
    ObjectResponse<DynaTopicCommentListResponseDTO> getCommentPage(@RequestBody PageRestRequest<DynaTopicCommentListDTO> page);

    /**
     * 获取评论详情
     * 不包含点赞、评论数
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/DynaServer/facade/dyna/topic/comment/app/{id}")
    DynaTopicCommentListDTO getCommentDetail(@PathVariable(value = "id") Integer id);

    @PostMapping(value = "/DynaServer/facade/dyna/topic/comment/app/getCommentDetail")
    DynaTopicCommentListDTO getCommentDetail(@RequestBody PageRestRequest<DynaTopicReplyListDTO> page);


    /**
     * 添加评论
     *
     * @param dto
     * @return
     */
    @PostMapping(value = "/DynaServer/facade/dyna/topic/comment/app/comment")
    ObjectResponse<DynaTopicCommentDTO> addComment(@RequestBody DynaTopicCommentDTO dto);

    /**
     * 删除评论
     *
     * @param dto
     * @return
     */
    @DeleteMapping(value = "/DynaServer/facade/dyna/topic/comment/app/comment")
    BaseResponse deleteComment(@RequestBody DynaTopicCommentDTO dto);
}