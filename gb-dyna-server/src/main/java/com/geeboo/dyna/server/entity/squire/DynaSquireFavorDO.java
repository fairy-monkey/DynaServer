package com.geeboo.dyna.server.entity.squire;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigInteger;

@Data
@Table(name = "dyna_squire_favor")
public class DynaSquireFavorDO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    protected Integer squireFavorId;
    @Column(name = "dyna_squire_id")
    private Integer dynaSquireId;
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


