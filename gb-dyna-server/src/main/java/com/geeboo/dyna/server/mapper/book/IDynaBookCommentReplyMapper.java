package com.geeboo.dyna.server.mapper.book;

import com.geeboo.dyna.server.client.dto.book.DynaBookCommentReplyDTO;
import com.geeboo.dyna.server.client.dto.book.DynaBookReplyListDTO;
import com.geeboo.dyna.server.entity.book.DynaBookCommentReplyDO;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface IDynaBookCommentReplyMapper extends Mapper<DynaBookCommentReplyDO> {
    DynaBookCommentReplyDTO findById(Integer dynaBookCommentReplyId);

    int add(DynaBookCommentReplyDTO dto);

    int update(DynaBookCommentReplyDTO dto);

    int deleteDynaBookCommentReply(Integer id);

    int deleteByCommentId(@Param(value = "commentId") Integer commentId);

    List<DynaBookCommentReplyDTO> query(DynaBookCommentReplyDTO dto);

    DynaBookCommentReplyDTO findByCondition(DynaBookCommentReplyDTO dto);

    /**
     * app 回复列表
     * @param dto
     * @return
     */
    Page<DynaBookReplyListDTO> queryReplyList(DynaBookReplyListDTO dto);

    /**
     * 根据评论ID统计数量
     *
     * @param dynaBookCommentId
     * @return
     */
    Long countReplyByComment(@Param(value = "dynaBookCommentId") Integer dynaBookCommentId);
}