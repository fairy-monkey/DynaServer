package com.geeboo.dyna.server.entity.topic;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigInteger;

@Data
@Table(name = "dyna_topic_content")
public class DynaTopicContentDO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    protected Integer dynaTopicContentId;
    @Column(name = "dyna_topic_id")
    private Integer dynaTopicId;
    @Column(name = "dyna_topic_content")
    private String dynaTopicContent;
    @Column(name = "create_time")
    private BigInteger createTime;
    @Column(name = "create_by")
    private Integer createBy;
    @Column(name = "modify_time")
    private BigInteger modifyTime;
    @Column(name = "modify_by")
    private Integer modifyBy;
}


