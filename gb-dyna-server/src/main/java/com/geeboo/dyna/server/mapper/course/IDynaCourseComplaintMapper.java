package com.geeboo.dyna.server.mapper.course;

import com.geeboo.dyna.server.client.dto.course.DynaCourseComplaintDTO;
import com.geeboo.dyna.server.entity.course.DynaCourseComplaintDO;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface IDynaCourseComplaintMapper extends Mapper<DynaCourseComplaintDO> {
    DynaCourseComplaintDTO findById(Integer dynaCourseComplaintId);

    int add(DynaCourseComplaintDTO dto);

    int update(DynaCourseComplaintDTO dto);

    int deleteDynaCourseComplaint(Integer id);

    List<DynaCourseComplaintDTO> query(DynaCourseComplaintDTO dto);

    DynaCourseComplaintDTO findByCondition(DynaCourseComplaintDTO dto);

    /**
     * 统计待处理、已隐藏的举报数量
     *
     * @param dto
     * @return
     */
    Long countComplaint(DynaCourseComplaintDTO dto);
}
