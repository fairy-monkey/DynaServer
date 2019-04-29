package com.geeboo.dyna.server.mapper.topic;

import com.geeboo.dyna.server.client.dto.topic.DynaTopicFavorDTO;
import com.geeboo.dyna.server.entity.topic.DynaTopicFavorDO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Set;

public interface IDynaTopicFavorMapper extends Mapper<DynaTopicFavorDO> {
    DynaTopicFavorDTO findById(Integer dynaTopicFavorId);

    int add(DynaTopicFavorDTO dto);

    int update(DynaTopicFavorDTO dto);

    int deleteDynaTopicFavor(Integer id);

    List<DynaTopicFavorDTO> query(DynaTopicFavorDTO dto);

    DynaTopicFavorDTO findByCondition(DynaTopicFavorDTO dto);

    DynaTopicFavorDTO getFavorByCommentAndUser(@Param(value = "dynaTopicId") Integer dynaTopicId, @Param(value = "dynaTopicCommentId") Integer dynaTopicCommentId, @Param(value = "userId") Integer userId);

    /**
     * 用户点赞列表
     *
     * @return
     */
    List<DynaTopicFavorDTO> findCommentFavorListByUser(@Param(value = "commentIdSet") Set<Integer> commentIdSet, @Param(value = "userId") Integer userId);


}