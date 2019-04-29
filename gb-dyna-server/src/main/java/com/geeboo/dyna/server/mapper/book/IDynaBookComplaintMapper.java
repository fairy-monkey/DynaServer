package com.geeboo.dyna.server.mapper.book;

import com.geeboo.dyna.server.client.dto.book.DynaBookComplaintDTO;
import com.geeboo.dyna.server.entity.book.DynaBookComplaintDO;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface IDynaBookComplaintMapper extends Mapper<DynaBookComplaintDO> {
    DynaBookComplaintDTO findById(Integer dynaBookComplaintId);

    int add(DynaBookComplaintDTO dto);

    int update(DynaBookComplaintDTO dto);

    int deleteDynaBookComplaint(Integer id);

    List<DynaBookComplaintDTO> query(DynaBookComplaintDTO dto);

    DynaBookComplaintDTO findByCondition(DynaBookComplaintDTO dto);

    /**
     * 统计待处理、已隐藏的举报数量
     *
     * @param dto
     * @return
     */
    Long countComplaint(DynaBookComplaintDTO dto);
}