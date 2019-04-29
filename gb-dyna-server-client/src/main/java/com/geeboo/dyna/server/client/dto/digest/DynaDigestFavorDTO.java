package com.geeboo.dyna.server.client.dto.digest;

import java.io.Serializable;
import java.math.BigInteger;

public class DynaDigestFavorDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer dynaDigestFavorId;
    private Integer dynaDigestId;
    private Integer userId;
    private Integer isFavor;
    private BigInteger createTime;
    private Integer createBy;
    private BigInteger modifyTime;
    private Integer modifyBy;

    public Integer getDynaDigestFavorId() {
        return dynaDigestFavorId;
    }

    public void setDynaDigestFavorId(Integer dynaDigestFavorId) {
        this.dynaDigestFavorId = dynaDigestFavorId;
    }

    public Integer getDynaDigestId() {
        return dynaDigestId;
    }

    public void setDynaDigestId(Integer dynaDigestId) {
        this.dynaDigestId = dynaDigestId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getIsFavor() {
        return isFavor;
    }

    public void setIsFavor(Integer isFavor) {
        this.isFavor = isFavor;
    }

    public BigInteger getCreateTime() {
        return createTime;
    }

    public void setCreateTime(BigInteger createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public BigInteger getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(BigInteger modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(Integer modifyBy) {
        this.modifyBy = modifyBy;
    }

    @Override
    public String toString() {
        return "DynaDigestFavorDTO{" + "dynaDigestFavorId=" + dynaDigestFavorId + ", dynaDigestId=" + dynaDigestId + ", userId=" + userId + ", isFavor=" + isFavor + ", createTime=" + createTime + ", createBy=" + createBy + ", modifyTime=" + modifyTime + ", modifyBy=" + modifyBy + '}';
    }
}


