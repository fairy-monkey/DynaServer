package com.geeboo.dyna.server.service.course;

import com.geeboo.common.exception.runtime.ServiceException;
import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.PageRestRequest;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.dyna.server.client.dto.course.*;
import com.geeboo.dyna.server.constant.OperateEnum;

import java.util.List;

/**
 * Title: <br>
 * Description: Copyright: Copyright (c) 2018
 *
 * @author 郭明毅 guomy 创建时间:2018/9/13 22:29
 */
public interface IDynaCourseCommentAppService {
    /**
     * 评论分页
     *
     * @param page
     * @return
     */
    TableResultResponse<DynaCourseCommentListDTO> getCommentPage(PageRestRequest<DynaCourseCommentListDTO> page);

    /**
     * 根据ID获取详情
     *
     * @param id
     * @return
     */
    DynaCourseCommentListDTO getCommentDetail(Integer id);

    /**
     * 获取最近评论的3个用户
     *
     * @param dynaCourseId
     * @return
     */
    List<DynaCourseCommentDTO> getRecentCommentUserList(Integer dynaCourseId);

    /**
     * 统计评论的人数
     *
     * @param dynaCourseId
     * @return
     */
    long getCommentCountGroupByAccount(Integer dynaCourseId);

    /**
     * 添加评论
     *
     * @param dto
     * @return
     */
    ObjectResponse<DynaCourseCommentDTO> addComment(DynaCourseCommentDTO dto);

    /**
     * 删除评论
     *
     * @param dto
     * @return
     */
    BaseResponse deleteComment(DynaCourseCommentDTO dto);

    /**
     * 评论相关缓存计数自增
     *
     * @param dto
     * @param operate
     * @throws ServiceException
     */
    void incrementCommentCount(DynaCourseCommentReplyDTO dto, OperateEnum operate) throws ServiceException;

    /**
     * 评论相关缓存计数自增
     *
     * @param dto
     * @param operate
     * @throws ServiceException
     */
    void incrementCommentCount(DynaCourseCommentFavorDTO dto, OperateEnum operate) throws ServiceException;

    /**
     * 自增评论本身统计
     *
     * @param dto
     * @param operate
     * @throws ServiceException
     */
    void incrementCommentCount(DynaCourseCommentDTO dto, OperateEnum operate) throws ServiceException;

    /**
     * 获取评论统计
     *
     * @param courseId
     * @return
     */
    ObjectResponse<DynaCourseStatDTO> getCourseStat(Integer courseId);

    BaseResponse updateComment(DynaCourseCommentDTO dto);
}
