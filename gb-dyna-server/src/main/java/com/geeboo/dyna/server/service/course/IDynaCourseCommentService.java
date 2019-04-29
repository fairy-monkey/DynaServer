package com.geeboo.dyna.server.service.course;

import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.common.page.Page;
import com.geeboo.dyna.server.client.dto.course.DynaCourseCommentDTO;

import java.util.List;

public interface IDynaCourseCommentService {
    /**
     * 增加动态_课程评论表
     *
     * @param dto
     * @return
     */
    BaseResponse add(DynaCourseCommentDTO dto);

    /**
     * 修改动态_课程评论表
     *
     * @param dto
     * @return
     */
    BaseResponse update(DynaCourseCommentDTO dto);

    /**
     * 删除动态_课程评论表
     *
     * @param id 系统主键
     * @return
     */
    BaseResponse delete(Integer id);

    /**
     * 分页查询
     *
     * @param dto  查询条件
     * @param page 当前页,每页显示的条数
     * @return
     */
    TableResultResponse<DynaCourseCommentDTO> page(DynaCourseCommentDTO dto, Page<DynaCourseCommentDTO> page);

    /**
     * 普通查询
     *
     * @param dto 查询条件
     * @return
     */
    TableResultResponse<DynaCourseCommentDTO> query(DynaCourseCommentDTO dto);

    /**
     * 直接query
     *
     * @param dto
     * @return
     */
    List<DynaCourseCommentDTO> queryList(DynaCourseCommentDTO dto);

    /**
     * 通过ID获取动态_课程评论表实体
     *
     * @param id 主键id
     * @return
     */
    ObjectResponse findById(Integer id);

    /**
     * 通过条件获取动态_课程评论表实体
     *
     * @param dto 查询条件
     * @return
     */
    ObjectResponse findByCondition(DynaCourseCommentDTO dto);

    /**
     * 隐藏评论
     *
     * @param dto
     * @return
     */
    BaseResponse hiddenComment(DynaCourseCommentDTO dto);

    /**
     * 设置点赞数
     *
     * @param dto
     * @return
     */
    BaseResponse setFavorNum(DynaCourseCommentDTO dto);

    /**
     * 从redis更新到数据库
     * @return
     */
    BaseResponse flushRedisToDb();
}
