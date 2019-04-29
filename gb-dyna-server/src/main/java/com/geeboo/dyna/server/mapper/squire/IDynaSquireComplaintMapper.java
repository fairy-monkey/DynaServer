package com.geeboo.dyna.server.mapper.squire;

import java.util.List;

import com.geeboo.dyna.server.client.dto.squire.DynaSquireComplaintDTO;
import com.geeboo.dyna.server.entity.squire.DynaSquireComplaintDO;

import tk.mybatis.mapper.common.Mapper;

public interface IDynaSquireComplaintMapper extends Mapper<DynaSquireComplaintDO> {
    DynaSquireComplaintDTO findById(Integer dynaSquireComplaintId);

    int add(DynaSquireComplaintDTO dto);

    int update(DynaSquireComplaintDTO dto);

    int deleteDynaSquireComplaint(Integer id);

    List<DynaSquireComplaintDTO> query(DynaSquireComplaintDTO dto);

    DynaSquireComplaintDTO findByCondition(DynaSquireComplaintDTO dto);

    /**
     * 统计待处理、已隐藏的举报数量
     *
     * @param dto
     * @return
     */
    Long countComplaint(DynaSquireComplaintDTO dto);
}