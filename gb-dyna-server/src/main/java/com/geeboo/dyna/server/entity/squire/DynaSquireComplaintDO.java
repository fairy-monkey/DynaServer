package com.geeboo.dyna.server.entity.squire;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name = "dyna_squire_complaint")
public class DynaSquireComplaintDO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    protected Integer squireComplaintId;
    @Column(name = "dyna_squire_id")
    private Integer dynaSquireId;
    @Column(name = "complaint_content")
    private String complaintContent;
    @Column(name = "complaint_type")
    private Integer complaintType;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "to_user_id")
    private Integer toUserId;
    @Column(name = "complaint_status")
    private Integer complaintStatus;
    @Column(name = "content_id")
    private Integer contentId;
    @Column(name = "complaint_obj")
    private Integer complaintObj;
    @Column(name = "create_time")
    private BigInteger createTime;
    @Column(name = "create_by")
    private Integer createBy;
    @Column(name = "modify_time")
    private BigInteger modifyTime;
    @Column(name = "modify_by")
    private Integer modifyBy;
}


