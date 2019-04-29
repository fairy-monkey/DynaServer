package com.geeboo.dyna.server.entity.squire;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigInteger;

@Data
@Table(name = "dyna_squire_comment_reply")
public class DynaSquireCommentReplyDO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    protected Integer commentReplyId;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "dyna_squire_id")
    private Integer dynaSquireId;
    @Column(name = "dyna_squire_comment_id")
    private Integer dynaSquireCommentId;
    @Column(name = "to_comment_reply_id")
    private Integer toCommentReplyId;
    @Column(name = "is_del")
    private Integer isDel;
    @Column(name = "reply_content")
    private String replyContent;
    @Column(name = "to_user_id")
    private Integer toUserId;
    @Column(name = "reply_type")
    private Integer replyType;
    @Column(name = "create_time")
    private BigInteger createTime;
    @Column(name = "create_by")
    private Integer createBy;
    @Column(name = "modify_time")
    private BigInteger modifyTime;
    @Column(name = "modify_by")
    private Integer modifyBy;
}


