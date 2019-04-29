package com.geeboo.dyna.server.entity.squire;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigInteger;

@Data
@Table(name = "dyna_squire_user_stat")
public class DynaSquireUserStatDO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    protected Integer dynaSquireUserStatId;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "num_squire")
    private Integer numSquire;
    @Column(name = "create_time")
    private BigInteger createTime;
    @Column(name = "create_by")
    private Integer createBy;
    @Column(name = "modify_time")
    private BigInteger modifyTime;
    @Column(name = "modify_by")
    private Integer modifyBy;
}


