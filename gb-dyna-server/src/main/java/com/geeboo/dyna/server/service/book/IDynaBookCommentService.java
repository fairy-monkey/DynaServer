package com.geeboo.dyna.server.service.book;

import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.common.page.Page;
import com.geeboo.dyna.server.client.dto.book.DynaBookCommentDTO;

import java.util.List;

public interface IDynaBookCommentService {
    /**
     * 增加动态_话题评论表
     *
     * @param dto
     * @return
     */
    BaseResponse add(DynaBookCommentDTO dto);

    /**
     * 修改动态_话题评论表
     *
     * @param dto
     * @return
     */
    BaseResponse update(DynaBookCommentDTO dto);

    /**
     * 删除动态_话题评论表
     *
     * @param id 系统主键
     * @return
     */
    BaseResponse delete(Integer id);

    /**
     * 分页查询
     *
     * @param dto 查询条件
     * @param page 当前页,每页显示的条数
     * @return
     */
    TableResultResponse<DynaBookCommentDTO> page(DynaBookCommentDTO dto, Page<DynaBookCommentDTO> page);

    /**
     * 普通查询
     *
     * @param dto 查询条件
     * @return
     */
    TableResultResponse<DynaBookCommentDTO> query(DynaBookCommentDTO dto);

    /**
     * 直接query
     *
     * @param dto
     * @return
     */
    List<DynaBookCommentDTO> queryList(DynaBookCommentDTO dto);

    /**
     * 通过ID获取动态_话题评论表实体
     *
     * @param id 主键id
     * @return
     */
    ObjectResponse findById(Integer id);

    /**
     * 通过条件获取动态_话题评论表实体
     *
     * @param dto 查询条件
     * @return
     */
    ObjectResponse findByCondition(DynaBookCommentDTO dto);

    /**
     * 隐藏评论
     *
     * @param dto
     * @return
     */
    BaseResponse hiddenComment(DynaBookCommentDTO dto);

    /**
     * 设置点赞数
     *
     * @param dto
     * @return
     */
    BaseResponse setFavorNum(DynaBookCommentDTO dto);

    /**
     * 从redis更新到数据库
     * 
     * @return
     */
    BaseResponse flushRedisToDb();

    BaseResponse updateComment(DynaBookCommentDTO dto);
}
