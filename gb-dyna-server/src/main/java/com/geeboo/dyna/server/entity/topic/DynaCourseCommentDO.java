package com.geeboo.dyna.server.entity.topic;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigInteger;

@Data
@Table(name = "dyna_course_comment")
public class DynaCourseCommentDO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    protected Integer dynaCourseCommentId;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "course_id")
    private Integer courseId;
    @Column(name = "num_reply")
    private Integer numReply;
    @Column(name = "num_favor")
    private Integer numFavor;
    @Column(name = "is_del")
    private Integer isDel;
    @Column(name = "is_sensitive")
    private Integer isSensitive;
    @Column(name = "comment_content")
    private String commentContent;
    @Column(name = "index_no")
    private Integer indexNo;
    @Column(name = "create_time")
    private BigInteger createTime;
    @Column(name = "create_by")
    private Integer createBy;
    @Column(name = "modify_time")
    private BigInteger modifyTime;
    @Column(name = "modify_by")
    private Integer modifyBy;
}