package com.geeboo.dyna.server.mapper.topic;

import com.geeboo.dyna.server.client.dto.topic.DynaTopicDTO;
import com.geeboo.dyna.server.client.dto.topic.DynaTopicListDTO;
import com.geeboo.dyna.server.entity.topic.DynaTopicDO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Set;

public interface IDynaTopicMapper extends Mapper<DynaTopicDO> {
    DynaTopicDTO findById(Integer dynaTopicId);

    int add(DynaTopicDTO dto);

    int update(DynaTopicDTO dto);

    int batchUpdateIndex(List<DynaTopicDTO> list);

    int deleteDynaTopic(Integer id);

    List<DynaTopicDTO> query(DynaTopicDTO dto);

    DynaTopicDTO findByCondition(DynaTopicDTO dto);

    /**
     * 获取已发布列表
     *
     * @param lastId
     * @param pageSize
     * @return
     */
    List<DynaTopicDTO> getTopicPublishList(@Param(value = "lastId")Integer lastId, @Param(value = "pageSize")Integer pageSize);

    /**
     * 获取定时发布列表
     *
     * @param lastId
     * @param pageSize
     * @return
     */
    List<DynaTopicDTO> getTopicTimeToPublishList(@Param(value = "lastId")Integer lastId, @Param(value = "pageSize")Integer pageSize);

    List<DynaTopicDTO> findTopicByMinSort(@Param(value = "sort") Integer sort);

    List<DynaTopicDTO> findLatestTopic();

    /**
     * 根据ID列表获取
     *
     * @param id
     * @return
     */
    List<DynaTopicListDTO> batchGetTopicListByIdSet(@Param(value = "set") Set<Integer> id);
}