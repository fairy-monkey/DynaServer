package com.geeboo.dyna.server.mapper.squire;

import com.geeboo.dyna.server.client.dto.squire.DynaSquireCommentReplyDTO;
import com.geeboo.dyna.server.client.dto.squire.DynaSquireReplyListDTO;
import com.geeboo.dyna.server.entity.squire.DynaSquireCommentReplyDO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface IDynaSquireCommentReplyMapper extends Mapper<DynaSquireCommentReplyDO> {
    DynaSquireCommentReplyDO findById(Integer dynaSquireCommentReplyId);

    int add(DynaSquireCommentReplyDTO dto);

    int update(DynaSquireCommentReplyDTO dto);

    int deleteDynaSquireCommentReply(Integer id);

    int deleteByCommentId(@Param(value = "commentId") Integer commentId);

    List<DynaSquireCommentReplyDTO> query(DynaSquireCommentReplyDTO dto);

    DynaSquireCommentReplyDTO findByCondition(DynaSquireCommentReplyDTO dto);

    /**
     * app 回复列表
     * @param dto
     * @return
     */
    List<DynaSquireReplyListDTO> queryReplyList(DynaSquireReplyListDTO dto);

    /**
     * 根据评论ID统计数量
     *
     * @param dynaSquireCommentId
     * @return
     */
    Long countReplyByComment(@Param(value = "dynaSquireCommentId") Integer dynaSquireCommentId);
}