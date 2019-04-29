package com.geeboo.dyna.server.mapper.course;

import com.geeboo.dyna.server.client.dto.course.DynaCourseCommentDTO;
import com.geeboo.dyna.server.client.dto.course.DynaCourseCommentListDTO;
import com.geeboo.dyna.server.entity.course.DynaCourseCommentDO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface IDynaCourseCommentMapper extends Mapper<DynaCourseCommentDO> {
    DynaCourseCommentDTO findById(Integer dynaCourseCommentId);

    int add(DynaCourseCommentDTO dto);

    int update(DynaCourseCommentDTO dto);

    int deleteDynaCourseComment(Integer id);

    List<DynaCourseCommentDTO> query(DynaCourseCommentDTO dto);

    DynaCourseCommentDTO findByCondition(DynaCourseCommentDTO dto);

    List<DynaCourseCommentDTO> queryCourse(DynaCourseCommentDTO dto);

    DynaCourseCommentListDTO getCommentDetail(@Param(value = "id") Integer id);

    /**
     * 根据课程ID和上个ID获取
     * ID倒序
     *
     * @param courseId
     * @param lastId   从小于这个ID的主键开始查询
     * @param pageSize
     * @returna
     */
    List<DynaCourseCommentListDTO> getCommentByCourseAndLastId(@Param(value = "courseId") Integer courseId, @Param(value = "lastId") Integer lastId, @Param(value = "userId") Integer userId, @Param(value = "pageSize") Integer pageSize);

    /**
     * 获取最近评论的3个用户
     *
     * @param courseId
     * @return
     */
    List<DynaCourseCommentDTO> getRecentCommentUserList(@Param(value = "courseId") Integer courseId);

    /**
     * 统计评论的人数
     *
     * @param courseId
     * @return
     */
    Long getCommentCountGroupByAccount(@Param(value = "courseId") Integer courseId);

    /**
     * 统计评论的条数
     *
     * @param courseId
     * @return
     */
    @Select("SELECT count(1) FROM dyna_course_comment where course_id = #{courseId} and is_del = 0 and is_sensitive = 0")
    Integer getCommentCountNumByCourseId(@Param(value = "courseId") Integer courseId);

    /**
     * 批量更新评论回复计数
     *
     * @param list
     */
    void batchUpdateReplyNum(@Param(value = "list") List<DynaCourseCommentDO> list);

    /**
     * 批量更新评论点赞计数
     *
     * @param list
     */
    void batchUpdateFavorNum(@Param(value = "list") List<DynaCourseCommentDO> list);
}
