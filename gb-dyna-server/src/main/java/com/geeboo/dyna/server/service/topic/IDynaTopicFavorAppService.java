package com.geeboo.dyna.server.service.topic;

import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.dyna.server.client.dto.topic.DynaTopicCommentFavorDTO;

import java.util.Set;

/**
 * Title: <br>
 * Description: Copyright: Copyright (c) 2018
 *
 * @author 郭明毅 guomy 创建时间:2018/9/17 19:53
 */
public interface IDynaTopicFavorAppService {
    /**
     * 点赞
     *
     * @param dto
     * @return 是否是新增的且为点赞
     */
    ObjectResponse<Boolean> doFavor(DynaTopicCommentFavorDTO dto);

    /**
     * 从评论ID集合中查找出用户点赞的评论ID
     *
     * @param commentIdSet
     * @param userId
     * @return
     */
    Set<Integer> findCommentFavorListByUser(Set<Integer> commentIdSet, Integer userId);
}
