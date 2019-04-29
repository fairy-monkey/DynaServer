package com.geeboo.dyna.server.client.facade.topic;

import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.PageRestRequest;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.dyna.server.client.dto.topic.DynaTopicCommentReplyDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "${gb-dyna-server}", configuration = {})
public interface IDynaTopicCommentReplyFacade {
    /**
     * 增加
     *
     * @param dto 待新增的实体对象
     * @return
     */
    @RequestMapping(value = "/DynaServer/facade/dyna/topicCommentReply", method = RequestMethod.POST)
    BaseResponse add(@RequestBody DynaTopicCommentReplyDTO dto);

    /**
     * 修改
     *
     * @param dto 待需改的实体对象
     * @return
     */
    @RequestMapping(value = "/DynaServer/facade/dyna/topicCommentReply", method = RequestMethod.PUT)
    BaseResponse update(@RequestBody DynaTopicCommentReplyDTO dto);

    /**
     * 删除
     *
     * @param dto 系统主键
     * @return
     */
    @RequestMapping(value = "/DynaServer/facade/dyna/topicCommentReply", method = RequestMethod.DELETE)
    BaseResponse delete(@RequestBody DynaTopicCommentReplyDTO dto);

    /**
     * 分页查询
     *
     * @param pageRestRequest 分页条件，当前页，每页显示条数
     * @return
     */
    @RequestMapping(value = "/DynaServer/facade/dyna/topicCommentReply/page", method = RequestMethod.POST)
    TableResultResponse<DynaTopicCommentReplyDTO> page(@RequestBody PageRestRequest<DynaTopicCommentReplyDTO> pageRestRequest);

    /**
     * 通过ID获取动态_话题评论回复表实体
     *
     * @param dto 主键ID
     * @return
     */
    @RequestMapping(value = "/DynaServer/facade/dyna/topicCommentReply/findById", method = RequestMethod.POST)
    ObjectResponse<DynaTopicCommentReplyDTO> findById(@RequestBody DynaTopicCommentReplyDTO dto);

    /**
     * 通过条件获取动态_话题评论回复表实体
     *
     * @param dto 查询条件
     * @return
     */
    @RequestMapping(value = "/DynaServer/facade/dyna/topicCommentReply/findByCondition", method = RequestMethod.POST)
    ObjectResponse<DynaTopicCommentReplyDTO> findByCondition(DynaTopicCommentReplyDTO dto);

    /**
     * 隐藏评论回复
     *
     * @param dto
     * @return
     */
    @PutMapping(value = "/DynaServer/facade/dyna/topicCommentReply/hiddenCommentReply")
    BaseResponse hiddenCommentReply(@RequestBody DynaTopicCommentReplyDTO dto);
}