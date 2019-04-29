package com.geeboo.dyna.server.service.topic;

import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.common.page.Page;
import com.geeboo.dyna.server.client.dto.topic.DynaTopicCommentDTO;

import java.util.List;

public interface IDynaTopicCommentService {
    /**
     * 增加动态_话题评论表
     *
     * @param dto
     * @return
     */
    BaseResponse add(DynaTopicCommentDTO dto);

    /**
     * 修改动态_话题评论表
     *
     * @param dto
     * @return
     */
    BaseResponse update(DynaTopicCommentDTO dto);

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
     * @param dto  查询条件
     * @param page 当前页,每页显示的条数
     * @return
     */
    TableResultResponse<DynaTopicCommentDTO> page(DynaTopicCommentDTO dto, Page<DynaTopicCommentDTO> page);

    /**
     * 直接query
     *
     * @param dto
     * @return
     */
    List<DynaTopicCommentDTO> queryList(DynaTopicCommentDTO dto);

    /**
     * 直接query add by tangwei
     *
     * @param dto
     * @return
     */
    ObjectResponse queryCommentList(DynaTopicCommentDTO dto);

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
    ObjectResponse findByCondition(DynaTopicCommentDTO dto);

    /**
     * 隐藏评论
     *
     * @param dto
     * @return
     */
    BaseResponse hiddenComment(DynaTopicCommentDTO dto);

    /**
     * 设置点赞数
     *
     * @param dto
     * @return
     */
    BaseResponse setFavorNum(DynaTopicCommentDTO dto);

    /**
     * 从redis更新到数据库
     *
     * @return
     */
    BaseResponse flushRedisToDb();

    /**
     * 获取图书未删除的评论总数
     */
    Integer queryToppicCommentNum(Integer dynaTopicId);

    /**
     * 获取获取最近评论的3个用户
     */
    List<DynaTopicCommentDTO> getRecentCommentUserList(Integer dynaTopicId);
}