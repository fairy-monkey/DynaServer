package com.geeboo.dyna.server.entity.topic;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigInteger;

@Data
@Table(name = "dyna_topic_stat")
public class DynaTopicStatDO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    protected Integer dynaTopicStatId;
    @Column(name = "dyna_topic_id")
    private Integer dynaTopicId;
    @Column(name = "num_view")
    private Integer numView;
    @Column(name = "num_comment")
    private Integer numComment;
    @Column(name = "num_reply")
    private Integer numReply;
    @Column(name = "num_total")
    private Integer numTotal;
    @Column(name = "first_user_id")
    private Integer firstUserId;
    @Column(name = "second_user_id")
    private Integer secondUserId;
    @Column(name = "third_user_id")
    private Integer thirdUserId;
    @Column(name = "create_time")
    private BigInteger createTime;
    @Column(name = "create_by")
    private Integer createBy;
    @Column(name = "modify_time")
    private BigInteger modifyTime;
    @Column(name = "modify_by")
    private Integer modifyBy;
}


