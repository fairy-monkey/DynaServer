package com.geeboo.dyna.server.service.topic;

import com.geeboo.common.exception.runtime.ServiceException;
import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.PageRestRequest;
import com.geeboo.dyna.server.client.dto.topic.*;
import com.geeboo.dyna.server.constant.OperateEnum;

import java.util.List;

/**
 * Title: <br>
 * Description: Copyright: Copyright (c) 2018
 *
 * @author 郭明毅 guomy 创建时间:2018/9/13 22:29
 */
public interface IDynaTopicCommentAppService {
    /**
     * 评论分页
     *
     * @param page
     * @return
     */
    ObjectResponse<DynaTopicCommentListResponseDTO> getCommentPage(PageRestRequest<DynaTopicCommentListDTO> page);

    /**
     * 根据ID获取详情
     * 不包含点赞、评论数
     *
     * @param id
     * @return
     */
    DynaTopicCommentListDTO getCommentDetail(Integer id);

    /**
     * 根据id获取详情
     * 包含点赞数、评论数、点赞状态
     *
     * @param page
     * @return
     */
    DynaTopicCommentListDTO getCommentDetail(PageRestRequest<DynaTopicReplyListDTO> page);

    /**
     * 获取最近评论的3个用户
     *
     * @param dynaTopicId
     * @return
     */
    List<DynaTopicCommentDTO> getRecentCommentUserList(Integer dynaTopicId);

    /**
     * 统计评论的人数
     *
     * @param dynaTopicId
     * @return
     */
    long getCommentCountGroupByAccount(Integer dynaTopicId);

    /**
     * 添加评论
     *
     * @param dto
     * @return
     */
    ObjectResponse<DynaTopicCommentDTO> addComment(DynaTopicCommentDTO dto);

    /**
     * 删除评论
     *
     * @param dto
     * @return
     */
    BaseResponse deleteComment(DynaTopicCommentDTO dto);

    /**
     * 评论相关缓存计数自增
     *
     * @param dto
     * @param operate
     * @throws ServiceException
     */
    void incrementCommentCount(DynaTopicCommentReplyDTO dto, OperateEnum operate) throws ServiceException;

    /**
     * 评论相关缓存计数自增
     *
     * @param dto
     * @param operate
     * @throws ServiceException
     */
    void incrementCommentCount(DynaTopicCommentFavorDTO dto, OperateEnum operate) throws ServiceException;
}