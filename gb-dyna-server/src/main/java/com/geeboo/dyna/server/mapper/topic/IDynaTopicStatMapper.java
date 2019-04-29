package com.geeboo.dyna.server.mapper.topic;

import com.geeboo.dyna.server.client.dto.topic.DynaTopicStatDTO;
import com.geeboo.dyna.server.entity.topic.DynaTopicStatDO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Set;

public interface IDynaTopicStatMapper extends Mapper<DynaTopicStatDO> {
    DynaTopicStatDTO findById(Integer dynaTopicStatId);

    int add(DynaTopicStatDTO dto);

    int update(DynaTopicStatDTO dto);

    int deleteDynaTopicStat(Integer id);

    List<DynaTopicStatDTO> query(DynaTopicStatDTO dto);

    DynaTopicStatDTO findByCondition(DynaTopicStatDTO dto);

    DynaTopicStatDTO findByTopicId(Integer topicId);

    List<DynaTopicStatDTO> batchGetStatByTopicId(@Param("topicIdSet") Set<Integer> topicIdSet);
}