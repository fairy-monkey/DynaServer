package com.geeboo.dyna.server.service.book;

import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.PageRestRequest;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.dyna.server.client.dto.book.DynaBookCommentReplyDTO;
import com.geeboo.dyna.server.client.dto.book.DynaBookReplyListDTO;

/**
 * Title: <br>
 * Description: Copyright: Copyright (c) 2018
 *
 * @author 郭明毅 guomy 创建时间:2018/9/15 18:23
 */
public interface IDynaBookCommentReplyAppService {
    /**
     * 获取回复分页
     *
     * @param page
     * @return
     */
    TableResultResponse<DynaBookReplyListDTO> getReplyPage(PageRestRequest<DynaBookReplyListDTO> page);

    /**
     * 添加回复 返回新增回复主键ID，被回复者ID 如果是回复评论，被回复者ID为0，需要自行查找
     *
     * @param dto
     * @return
     */
    ObjectResponse<DynaBookCommentReplyDTO> addReply(DynaBookCommentReplyDTO dto);

    /**
     * 删除回复
     *
     * @param dto
     * @return
     */
    BaseResponse deleteReply(DynaBookCommentReplyDTO dto);

    /**
     * 根据评论ID统计数量
     * 
     * @param dynaBookCommentId
     * @return
     */
    Long countReplyByComment(Integer dynaBookCommentId);

    BaseResponse updateReply(DynaBookCommentReplyDTO dto);
}
