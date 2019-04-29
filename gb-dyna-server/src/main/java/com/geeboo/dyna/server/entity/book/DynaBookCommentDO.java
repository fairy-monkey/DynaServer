package com.geeboo.dyna.server.entity.book;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigInteger;

@Data
@Table(name = "dyna_book_comment")
public class DynaBookCommentDO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    protected Integer dynaBookCommentId;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "book_user_id")
    private Integer bookUserId;
    @Column(name = "num_reply")
    private Integer numReply;
    @Column(name = "num_favor")
    private Integer numFavor;
    @Column(name = "is_del")
    private Integer isDel;
    @Column(name = "comment_content")
    private String commentContent;
    @Column(name = "index_no")
    private Integer indexNo;
    @Column(name = "book_score")
    private Byte bookScore;
    @Column(name = "create_time")
    private BigInteger createTime;
    @Column(name = "create_by")
    private Integer createBy;
    @Column(name = "modify_time")
    private BigInteger modifyTime;
    @Column(name = "modify_by")
    private Integer modifyBy;
}


