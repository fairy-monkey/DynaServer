package com.geeboo.dyna.server.service.topic;

import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.common.page.Page;
import com.geeboo.dyna.server.client.dto.topic.DynaTopicCommentReplyDTO;

public interface IDynaTopicCommentReplyService {
    /**
     * 增加动态_话题评论回复表
     *
     * @param dto
     * @return
     */
    BaseResponse add(DynaTopicCommentReplyDTO dto);

    /**
     * 修改动态_话题评论回复表
     *
     * @param dto
     * @return
     */
    BaseResponse update(DynaTopicCommentReplyDTO dto);

    /**
     * 删除动态_话题评论回复表
     *
     * @param id 系统主键
     * @return
     */
    BaseResponse delete(Integer id);

    /**
     * 根据评论主键删除
     *
     * @param commentId
     * @return
     */
    BaseResponse deleteByCommentId(Integer commentId);

    /**
     * 分页查询
     *
     * @param dto  查询条件
     * @param page 当前页,每页显示的条数
     * @return
     */
    TableResultResponse<DynaTopicCommentReplyDTO> page(DynaTopicCommentReplyDTO dto, Page<DynaTopicCommentReplyDTO> page);

    /**
     * 通过ID获取动态_话题评论回复表实体
     *
     * @param id 主键id
     * @return
     */
    ObjectResponse findById(Integer id);

    /**
     * 通过条件获取动态_话题评论回复表实体
     *
     * @param dto 查询条件
     * @return
     */
    ObjectResponse findByCondition(DynaTopicCommentReplyDTO dto);

    /**
     * 隐藏评论回复
     *
     * @param dto
     * @return
     */
    BaseResponse hiddenCommentReply(DynaTopicCommentReplyDTO dto);
}