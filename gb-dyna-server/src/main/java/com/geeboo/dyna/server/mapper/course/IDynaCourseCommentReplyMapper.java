package com.geeboo.dyna.server.mapper.course;

import com.geeboo.dyna.server.client.dto.course.DynaCourseCommentReplyDTO;
import com.geeboo.dyna.server.client.dto.course.DynaCourseReplyListDTO;
import com.geeboo.dyna.server.entity.course.DynaCourseCommentReplyDO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface IDynaCourseCommentReplyMapper extends Mapper<DynaCourseCommentReplyDO> {
    DynaCourseCommentReplyDTO findById(Integer dynaCourseCommentReplyId);

    int add(DynaCourseCommentReplyDTO dto);

    int update(DynaCourseCommentReplyDTO dto);

    int deleteDynaCourseCommentReply(Integer id);

    int deleteByCommentId(@Param(value = "commentId") Integer commentId);

    List<DynaCourseCommentReplyDTO> query(DynaCourseCommentReplyDTO dto);

    DynaCourseCommentReplyDTO findByCondition(DynaCourseCommentReplyDTO dto);

    /**
     * app 回复列表
     * @param dto
     * @return
     */
    List<DynaCourseReplyListDTO> queryReplyList(DynaCourseReplyListDTO dto);

    /**
     * 根据评论ID统计数量
     *
     * @param dynaCourseCommentId
     * @return
     */
    Long countReplyByComment(@Param(value = "dynaCourseCommentId") Integer dynaCourseCommentId);
}
