package com.geeboo.dyna.server.client.dto.squire;

import java.io.Serializable;
import java.math.BigInteger;

public class DynaSquireStatDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    protected Integer dynaSquireStatId;
    private Integer dynaSquireId;
    private Integer numView;
    private Integer numComment;
    private Integer numReply;
    private Integer numTotal;
    private BigInteger createTime;
    private Integer createBy;
    private BigInteger modifyTime;
    private Integer modifyBy;

    public Integer getDynaSquireStatId() {
        return dynaSquireStatId;
    }

    public void setDynaSquireStatId(Integer dynaSquireStatId) {
        this.dynaSquireStatId = dynaSquireStatId;
    }

    /**
     * 返回 dyna_squire_id,广场信息id
     */
    public Integer getDynaSquireId() {
        return dynaSquireId;
    }

    /**
     * 参数 dynaSquireId
     */
    public void setDynaSquireId(Integer dynaSquireId) {
        this.dynaSquireId = dynaSquireId;
    }

    /**
     * 返回 num_view,浏览数
     */
    public Integer getNumView() {
        return numView;
    }

    /**
     * 参数 numView
     */
    public void setNumView(Integer numView) {
        this.numView = numView;
    }

    /**
     * 返回 num_comment,参与评论数
     */
    public Integer getNumComment() {
        return numComment;
    }

    /**
     * 参数 numComment
     */
    public void setNumComment(Integer numComment) {
        this.numComment = numComment;
    }

    /**
     * 返回 num_reply,总回复数
     */
    public Integer getNumReply() {
        return numReply;
    }

    /**
     * 参数 numReply
     */
    public void setNumReply(Integer numReply) {
        this.numReply = numReply;
    }

    /**
     * 返回 num_total,总参与数
     */
    public Integer getNumTotal() {
        return numTotal;
    }

    /**
     * 参数 numTotal
     */
    public void setNumTotal(Integer numTotal) {
        this.numTotal = numTotal;
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
        toString.append("DynaSquireStatDTO{");
        toString.append("dynaSquireId=");
        toString.append(dynaSquireId);
        toString.append(",numView=");
        toString.append(numView);
        toString.append(",numComment=");
        toString.append(numComment);
        toString.append(",numReply=");
        toString.append(numReply);
        toString.append(",numTotal=");
        toString.append(numTotal);
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


