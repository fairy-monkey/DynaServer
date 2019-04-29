package com.geeboo.dyna.server.service.book;

import com.geeboo.common.exception.runtime.ServiceException;
import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.PageRestRequest;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.dyna.server.client.dto.book.*;
import com.geeboo.dyna.server.client.dto.squire.DynaSquireReadBookDTO;
import com.geeboo.dyna.server.constant.OperateEnum;

import java.util.List;

/**
 * Title: <br>
 * Description: Copyright: Copyright (c) 2018
 *
 * @author 郭明毅 guomy 创建时间:2018/9/13 22:29
 */
public interface IDynaBookCommentAppService {
    /**
     * 评论分页
     *
     * @param page
     * @return
     */
    TableResultResponse<DynaBookCommentListDTO> getCommentPage(PageRestRequest<DynaBookCommentListDTO> page);

    /**
     * 根据ID获取详情
     *
     * @param id
     * @return
     */
    DynaBookCommentListDTO getCommentDetail(Integer id);

    /**
     * 获取最近评论的3个用户
     *
     * @param dynaBookId
     * @return
     */
    List<DynaBookCommentDTO> getRecentCommentUserList(Integer dynaBookId);

    /**
     * 统计评论的人数
     *
     * @param dynaBookId
     * @return
     */
    long getCommentCountGroupByAccount(Integer dynaBookId);

    /**
     * 添加评论
     *
     * @param dto
     * @return
     */
    ObjectResponse<DynaBookCommentDTO> addComment(DynaBookCommentDTO dto);

    /**
     * 添加读后评论，同时发布到广场
     *
     * @param dto
     * @return
     */
    ObjectResponse<DynaSquireReadBookDTO> addReadComment(DynaSquireReadBookDTO dto);

    /**
     * 删除评论
     *
     * @param dto
     * @return
     */
    BaseResponse deleteComment(DynaBookCommentDTO dto);

    /**
     * 评论相关缓存计数自增
     *
     * @param dto
     * @param operate
     * @throws ServiceException
     */
    void incrementCommentCount(DynaBookCommentReplyDTO dto, OperateEnum operate) throws ServiceException;

    /**
     * 评论相关缓存计数自增
     *
     * @param dto
     * @param operate
     * @throws ServiceException
     */
    void incrementCommentCount(DynaBookCommentFavorDTO dto, OperateEnum operate) throws ServiceException;

    /**
     * 自增评论本身统计
     *
     * @param dto
     * @param operate
     * @throws ServiceException
     */
    void incrementCommentCount(DynaBookCommentDTO dto, OperateEnum operate) throws ServiceException;

    /**
     * 获取评论统计
     *
     * @param bookUserId
     * @return
     */
    ObjectResponse<DynaBookStatDTO> getBookStat(Integer bookUserId);
}
