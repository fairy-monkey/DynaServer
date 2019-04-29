package com.geeboo.dyna.server.entity.squire;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigInteger;
@Data
@Table(name = "dyna_squire")
public class DynaSquireDO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    protected Integer dynaSquireId;
    @Column(name = "book_user_id")
    private Integer bookUserId;
    @Column(name = "squire_bus_type")
    private Integer squireBusType;
    @Column(name = "business_id")
    private Integer businessId;
    @Column(name = "book_name")
    private String bookName;
    @Column(name = "cover_path")
    private String coverPath;
    @Column(name = "book_author")
    private String bookAuthor;
    @Column(name = "select_content")
    private String selectContent;
    @Column(name = "comment_content")
    private String commentContent;
    @Column(name = "book_score")
    private Integer bookScore;
    @Column(name = "num_favor")
    private Integer numFavor;
    @Column(name = "num_reply")
    private Integer numReply;
    @Column(name = "is_disp")
    private Integer isDisp;
    @Column(name = "is_del")
    private Integer isDel;
    @Column(name = "create_time")
    private BigInteger createTime;
    @Column(name = "create_by")
    private Integer createBy;
    @Column(name = "modify_time")
    private BigInteger modifyTime;
    @Column(name = "modify_by")
    private Integer modifyBy;
}


