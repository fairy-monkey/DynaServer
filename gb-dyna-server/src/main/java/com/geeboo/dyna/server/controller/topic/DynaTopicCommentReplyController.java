package com.geeboo.dyna.server.controller.topic;

import com.geeboo.auth.client.annotation.IgnoreClientToken;
import com.geeboo.auth.client.annotation.IgnoreUserToken;
import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.PageRestRequest;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.dyna.server.client.dto.topic.DynaTopicCommentReplyDTO;
import com.geeboo.dyna.server.service.topic.IDynaTopicCommentReplyService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@IgnoreUserToken
@IgnoreClientToken
@Api(tags = "动态_话题评论回复表相关接口}")
@RestController
@RequestMapping("/facade/dyna")
public class DynaTopicCommentReplyController {
    @Autowired
    private IDynaTopicCommentReplyService dynaTopicCommentReplyService;

    /**
     * 增加动态_话题评论回复表
     *
     * @param dto 需要插入的实体
     * @return
     */
    @RequestMapping(value = "/topicCommentReply", method = RequestMethod.POST)
    BaseResponse add(@RequestBody DynaTopicCommentReplyDTO dto) {
        return dynaTopicCommentReplyService.add(dto);
    }

    /**
     * 修改动态_话题评论回复表
     *
     * @param dto 需要修改的实体
     * @return
     */
    @RequestMapping(value = "/topicCommentReply", method = RequestMethod.PUT)
    BaseResponse update(@RequestBody DynaTopicCommentReplyDTO dto) {
        return dynaTopicCommentReplyService.update(dto);
    }

    /**
     * 删除动态_话题评论回复表
     *
     * @param dto 包含系统主键
     * @return
     */
    @RequestMapping(value = "/topicCommentReply", method = RequestMethod.DELETE)
    BaseResponse delete(@RequestBody DynaTopicCommentReplyDTO dto) {
        Integer dynaTopicCommentReplyId = dto.getDynaTopicCommentReplyId();
        return dynaTopicCommentReplyService.delete(dynaTopicCommentReplyId);
    }

    /**
     * 分页查询
     *
     * @param pageRestRequest 分页条件，当前页，每页显示条数
     * @return
     */
    @RequestMapping(value = "/topicCommentReply/page", method = RequestMethod.POST)
    TableResultResponse<DynaTopicCommentReplyDTO> page(@RequestBody PageRestRequest<DynaTopicCommentReplyDTO> pageRestRequest) {
        return dynaTopicCommentReplyService.page(pageRestRequest.getData(), pageRestRequest.getPage());
    }

    /**
     * 通过条件获取动态_话题评论回复表实体
     *
     * @param dto 查询条件
     * @return
     */
    @RequestMapping(value = "/topicCommentReply/findByCondition", method = RequestMethod.POST)
    ObjectResponse<DynaTopicCommentReplyDTO> findByCondition(@RequestBody DynaTopicCommentReplyDTO dto) {
        return dynaTopicCommentReplyService.findByCondition(dto);
    }

    /**
     * 通过ID获取动态_话题评论回复表实体
     *
     * @param dto 主键ID
     * @return
     */
    @RequestMapping(value = "/topicCommentReply/findById", method = RequestMethod.POST)
    ObjectResponse<DynaTopicCommentReplyDTO> findById(@RequestBody DynaTopicCommentReplyDTO dto) {
        Integer dynaTopicCommentReplyId = dto.getDynaTopicCommentReplyId();
        return dynaTopicCommentReplyService.findById(dynaTopicCommentReplyId);
    }

    @PutMapping(value = "/topicCommentReply/hiddenCommentReply")
    public BaseResponse hiddenCommentReply(@RequestBody DynaTopicCommentReplyDTO dto) {
        return dynaTopicCommentReplyService.hiddenCommentReply(dto);
    }
}
