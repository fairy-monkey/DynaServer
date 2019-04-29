package com.geeboo.dyna.server.mapper.topic;

import com.geeboo.dyna.server.client.dto.topic.DynaTopicCommentReplyDTO;
import com.geeboo.dyna.server.client.dto.topic.DynaTopicReplyListDTO;
import com.geeboo.dyna.server.entity.topic.DynaTopicCommentReplyDO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface IDynaTopicCommentReplyMapper extends Mapper<DynaTopicCommentReplyDO> {
    DynaTopicCommentReplyDTO findById(Integer dynaTopicCommentReplyId);

    int add(DynaTopicCommentReplyDTO dto);

    int update(DynaTopicCommentReplyDTO dto);

    int deleteDynaTopicCommentReply(Integer id);

    int deleteByCommentId(@Param(value = "commentId") Integer commentId);

    List<DynaTopicCommentReplyDTO> query(DynaTopicCommentReplyDTO dto);

    DynaTopicCommentReplyDTO findByCondition(DynaTopicCommentReplyDTO dto);

    /**
     * app 回复列表
     * @param dto
     * @return
     */
    List<DynaTopicReplyListDTO> queryReplyList(DynaTopicReplyListDTO dto);

    /**
     * 根据评论ID统计数量
     *
     * @param dynaTopicCommentId
     * @return
     */
    Long countReplyByComment(@Param(value = "dynaTopicCommentId") Integer dynaTopicCommentId);
}