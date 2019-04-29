package com.geeboo.dyna.server.mapper.course;

import com.geeboo.dyna.server.client.dto.course.DynaCourseFavorDTO;
import com.geeboo.dyna.server.entity.course.DynaCourseFavorDO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Set;

public interface IDynaCourseFavorMapper extends Mapper<DynaCourseFavorDO> {
    DynaCourseFavorDTO findById(Integer dynaCourseFavorId);

    int add(DynaCourseFavorDTO dto);

    int update(DynaCourseFavorDTO dto);

    int deleteDynaCourseFavor(Integer id);

    List<DynaCourseFavorDTO> query(DynaCourseFavorDTO dto);

    DynaCourseFavorDTO findByCondition(DynaCourseFavorDTO dto);

    /**
     * 获取单个用户点赞
     *
     * @param courseId
     * @param dynaCourseCommentId
     * @param userId
     * @return
     */
    DynaCourseFavorDTO getFavorByCommentAndUser(@Param(value = "courseId") Integer courseId, @Param(value = "dynaCourseCommentId") Integer dynaCourseCommentId, @Param(value = "userId") Integer userId);

    /**
     * 用户点赞列表
     *
     * @return
     */
    List<DynaCourseFavorDTO> findCommentFavorListByUser(@Param(value = "commentIdSet") Set<Integer> commentIdSet, @Param(value = "userId") Integer userId);
}
