package com.geeboo.dyna.server.entity.course;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigInteger;

@Data
@Table(name = "dyna_course_favor")
public class DynaCourseFavorDO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    protected Integer dynaCourseFavorId;
    @Column(name = "course_id")
    private Integer courseId;
    @Column(name = "dyna_course_comment_id")
    private Integer dynaCourseCommentId;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "is_favor")
    private Integer isFavor;
    @Column(name = "create_time")
    private BigInteger createTime;
    @Column(name = "create_by")
    private Integer createBy;
    @Column(name = "modify_time")
    private BigInteger modifyTime;
    @Column(name = "modify_by")
    private Integer modifyBy;
}


