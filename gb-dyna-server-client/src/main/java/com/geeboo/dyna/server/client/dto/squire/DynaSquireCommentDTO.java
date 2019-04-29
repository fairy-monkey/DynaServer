package com.geeboo.dyna.server.client.dto.squire;

import com.geeboo.dyna.server.client.dto.ParentDTO;

import java.io.Serializable;
import java.math.BigInteger;

public class DynaSquireCommentDTO extends ParentDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    protected Integer dynaSquireCommentId;
    private Integer userId;
    private Integer dynaSquireId;
    private Integer numReply;
    private Integer numFavor;
    private Integer isDel;
    private String commentContent;
    private Integer commentType;
    private Integer toUserId;
    private Integer indexNo;
    private Integer bookScore;
    private BigInteger createTime;
    private Integer createBy;
    private BigInteger modifyTime;
    private Integer modifyBy;

    public Integer getDynaSquireCommentId() {
        return dynaSquireCommentId;
    }

    public void setDynaSquireCommentId(Integer dynaSquireCommentId) {
        this.dynaSquireCommentId = dynaSquireCommentId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getDynaSquireId() {
        return dynaSquireId;
    }

    public void setDynaSquireId(Integer dynaSquireId) {
        this.dynaSquireId = dynaSquireId;
    }

    public Integer getNumReply() {
        return numReply;
    }

    public void setNumReply(Integer numReply) {
        this.numReply = numReply;
    }

    public Integer getNumFavor() {
        return numFavor;
    }

    public void setNumFavor(Integer numFavor) {
        this.numFavor = numFavor;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Integer getCommentType() {
        return commentType;
    }

    public void setCommentType(Integer commentType) {
        this.commentType = commentType;
    }

    public Integer getToUserId() {
        return toUserId;
    }

    public void setToUserId(Integer toUserId) {
        this.toUserId = toUserId;
    }

    public Integer getIndexNo() {
        return indexNo;
    }

    public void setIndexNo(Integer indexNo) {
        this.indexNo = indexNo;
    }

    public Integer getBookScore() {
        return bookScore;
    }

    public void setBookScore(Integer bookScore) {
        this.bookScore = bookScore;
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
        return "DynaSquireCommentDTO{" + "dynaSquireCommentId=" + dynaSquireCommentId + ", userId=" + userId
                    + ", dynaSquireId=" + dynaSquireId + ", numReply=" + numReply + ", numFavor=" + numFavor
                    + ", isDel=" + isDel + ", commentContent='" + commentContent + '\'' + ", commentType=" + commentType
                    + ", toUserId='" + toUserId + '\'' + ", indexNo=" + indexNo + ", bookScore=" + bookScore
                    + ", createTime=" + createTime + ", createBy=" + createBy + ", modifyTime=" + modifyTime
                    + ", modifyBy=" + modifyBy + '}';
    }
}
