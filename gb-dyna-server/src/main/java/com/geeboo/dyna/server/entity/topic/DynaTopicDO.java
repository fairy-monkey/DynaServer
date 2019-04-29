package com.geeboo.dyna.server.entity.topic;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigInteger;

@Data
@Table(name = "dyna_topic")
public class DynaTopicDO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    protected Integer dynaTopicId;
    @Column(name = "topic_title")
    private String topicTitle;
    @Column(name = "topic_type")
    private Integer topicType;
    @Column(name = "background_color")
    private String backgroundColor;
    @Column(name = "publish_status")
    private Integer publishStatus;
    @Column(name = "publish_time")
    private BigInteger publishTime;
    @Column(name = "index_no")
    private Integer indexNo;
    @Column(name = "is_del")
    private Integer isDel;
    @Column(name = "business_id")
    private Integer businessId;
    @Column(name = "create_time")
    private BigInteger createTime;
    @Column(name = "create_by")
    private Integer createBy;
    @Column(name = "modify_time")
    private BigInteger modifyTime;
    @Column(name = "modify_by")
    private Integer modifyBy;
}


