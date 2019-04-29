package com.geeboo.dyna.server.mapper.topic;

import com.geeboo.dyna.server.client.dto.topic.DynaTopicContentDTO;
import com.geeboo.dyna.server.entity.topic.DynaTopicContentDO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IDynaTopicContentMapper extends Mapper<DynaTopicContentDO> {
    DynaTopicContentDTO findById(Integer dynaTopicContentId);

    int add(DynaTopicContentDTO dto);

    int update(DynaTopicContentDTO dto);

    int deleteDynaTopicContent(Integer id);

    List<DynaTopicContentDTO> query(DynaTopicContentDTO dto);

    DynaTopicContentDTO findByCondition(DynaTopicContentDTO dto);

    DynaTopicContentDTO findByTopicId(Integer topicId);

    /**
     * 批量通过话题ID获取
     *
     * @param topicIdSet
     * @return
     */
    List<DynaTopicContentDTO> batchGetContentByTopicIdSet(@Param("set") Set<Integer> topicIdSet);

    /**
     * 批量通过话题ID获取
     *
     * @param topicIdMap key代表查询的ID
     * @return
     */
    List<DynaTopicContentDTO> batchGetContentByTopicIdMap(@Param("map") Map<Integer, ?> topicIdMap);
}