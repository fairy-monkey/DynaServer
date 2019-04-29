package com.geeboo.dyna.server.service.course;

import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.common.page.Page;
import com.geeboo.dyna.server.client.dto.course.DynaCourseCommentReplyDTO;

public interface IDynaCourseCommentReplyService {
    /**
     * 增加动态_课程评论回复表
     *
     * @param dto
     * @return
     */
    BaseResponse add(DynaCourseCommentReplyDTO dto);

    /**
     * 修改动态_课程评论回复表
     *
     * @param dto
     * @return
     */
    BaseResponse update(DynaCourseCommentReplyDTO dto);

    /**
     * 删除动态_课程评论回复表
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
    TableResultResponse<DynaCourseCommentReplyDTO> page(DynaCourseCommentReplyDTO dto, Page<DynaCourseCommentReplyDTO> page);

    /**
     * 普通查询
     *
     * @param dto 查询条件
     * @return
     */
    TableResultResponse<DynaCourseCommentReplyDTO> query(DynaCourseCommentReplyDTO dto);

    /**
     * 通过ID获取动态_课程评论回复表实体
     *
     * @param id 主键id
     * @return
     */
    ObjectResponse findById(Integer id);

    /**
     * 通过条件获取动态_课程评论回复表实体
     *
     * @param dto 查询条件
     * @return
     */
    ObjectResponse findByCondition(DynaCourseCommentReplyDTO dto);
}
