package com.geeboo.dyna.server.mapper.topic;

import com.geeboo.dyna.server.client.dto.topic.DynaTopicComplaintDTO;
import com.geeboo.dyna.server.entity.topic.DynaTopicComplaintDO;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface IDynaTopicComplaintMapper extends Mapper<DynaTopicComplaintDO> {
    DynaTopicComplaintDTO findById(Integer dynaTopicComplaintId);

    int add(DynaTopicComplaintDTO dto);

    int update(DynaTopicComplaintDTO dto);

    int deleteDynaTopicComplaint(Integer id);

    List<DynaTopicComplaintDTO> query(DynaTopicComplaintDTO dto);

    DynaTopicComplaintDTO findByCondition(DynaTopicComplaintDTO dto);

    /**
     * 统计待处理、已隐藏的举报数量
     *
     * @param dto
     * @return
     */
    Long countComplaint(DynaTopicComplaintDTO dto);
}