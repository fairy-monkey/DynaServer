package com.geeboo.dyna.server.client.dto.squire;

import java.io.Serializable;
import java.math.BigInteger;

public class DynaSquireUserStatDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    protected Integer dynaSquireUserStatId;
    private Integer userId;
    private Integer numSquire;
    private BigInteger createTime;
    private Integer createBy;
    private BigInteger modifyTime;
    private Integer modifyBy;

    public Integer getDynaSquireUserStatId() {
        return dynaSquireUserStatId;
    }

    public void setDynaSquireUserStatId(Integer dynaSquireUserStatId) {
        this.dynaSquireUserStatId = dynaSquireUserStatId;
    }

    /**
     * 返回 user_id,用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 参数 userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 返回 num_squire,动态数量
     */
    public Integer getNumSquire() {
        return numSquire;
    }

    /**
     * 参数 numSquire
     */
    public void setNumSquire(Integer numSquire) {
        this.numSquire = numSquire;
    }

    /**
     * 返回 create_time,创建时间
     */
    public BigInteger getCreateTime() {
        return createTime;
    }

    /**
     * 参数 createTime
     */
    public void setCreateTime(BigInteger createTime) {
        this.createTime = createTime;
    }

    /**
     * 返回 create_by,创建人
     */
    public Integer getCreateBy() {
        return createBy;
    }

    /**
     * 参数 createBy
     */
    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    /**
     * 返回 modify_time,修改时间
     */
    public BigInteger getModifyTime() {
        return modifyTime;
    }

    /**
     * 参数 modifyTime
     */
    public void setModifyTime(BigInteger modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * 返回 modify_by,修改人
     */
    public Integer getModifyBy() {
        return modifyBy;
    }

    /**
     * 参数 modifyBy
     */
    public void setModifyBy(Integer modifyBy) {
        this.modifyBy = modifyBy;
    }

    @Override
    public String toString() {
        StringBuffer toString = new StringBuffer();
        toString.append("DynaSquireUserStatDTO{");
        toString.append("userId=");
        toString.append(userId);
        toString.append(",numSquire=");
        toString.append(numSquire);
        toString.append(",createTime=");
        toString.append(createTime);
        toString.append(",createBy=");
        toString.append(createBy);
        toString.append(",modifyTime=");
        toString.append(modifyTime);
        toString.append(",modifyBy=");
        toString.append(modifyBy);
        toString.append("}");
        return toString.toString();
    }
}


