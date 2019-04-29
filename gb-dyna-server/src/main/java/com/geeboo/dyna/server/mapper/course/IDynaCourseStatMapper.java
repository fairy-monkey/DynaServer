package com.geeboo.dyna.server.mapper.course;

import com.geeboo.dyna.server.client.dto.course.DynaCourseStatDTO;
import com.geeboo.dyna.server.entity.course.DynaCourseStatDO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Set;

public interface IDynaCourseStatMapper extends Mapper<DynaCourseStatDO> {
    DynaCourseStatDTO findById(Integer dynaCourseStatId);

    int add(DynaCourseStatDTO dto);

    int update(DynaCourseStatDTO dto);

    int deleteDynaCourseStat(Integer id);

    List<DynaCourseStatDTO> query(DynaCourseStatDTO dto);

    DynaCourseStatDTO findByCondition(DynaCourseStatDTO dto);

    DynaCourseStatDTO findByCourseId(Integer courseId);

    List<DynaCourseStatDTO> batchGetStatByCourseId(@Param("courseIdSet") Set<Integer> courseIdSet);

    void batchSaveOrUpdateCommentNum(@Param("list") List<DynaCourseStatDO> list);
}
