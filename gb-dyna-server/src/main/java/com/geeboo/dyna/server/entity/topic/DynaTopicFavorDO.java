package com.geeboo.dyna.server.entity.topic;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigInteger;

@Data
@Table(name = "dyna_topic_favor")
public class DynaTopicFavorDO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    protected Integer dynaTopicFavorId;
    @Column(name = "dyna_topic_id")
    private Integer dynaTopicId;
    @Column(name = "dyna_topic_comment_id")
    private Integer dynaTopicCommentId;
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


