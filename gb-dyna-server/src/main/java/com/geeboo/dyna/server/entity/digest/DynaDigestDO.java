package com.geeboo.dyna.server.entity.digest;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigInteger;

@Data
@Table(name = "dyna_digest")
public class DynaDigestDO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    protected Integer dynaDigestId;
    @Column(name = "book_user_id")
    private Integer bookUserId;
    @Column(name = "digest_content")
    private String digestContent;
    @Column(name = "book_prop_id")
    private Integer bookPropId;
    @Column(name = "image_path")
    private String imagePath;
    @Column(name = "publish_status")
    private Integer publishStatus;
    @Column(name = "publish_date")
    private Integer publishDate;
    @Column(name = "num_support")
    private Integer numSupport;
    @Column(name = "create_time")
    private BigInteger createTime;
    @Column(name = "create_by")
    private Integer createBy;
    @Column(name = "modify_time")
    private BigInteger modifyTime;
    @Column(name = "modify_by")
    private Integer modifyBy;
}
