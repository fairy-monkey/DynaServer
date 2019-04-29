package com.geeboo.dyna.server.client.dto.squire;

import com.geeboo.dyna.server.client.dto.ParentDTO;

import java.io.Serializable;
import java.math.BigInteger;

public class DynaSquireDTO extends ParentDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    protected Integer dynaSquireId;
    private Integer bookUserId;
    private Integer squireBusType;
    private Integer businessId;
    private String bookName;
    private String coverPath;
    private String bookAuthor;
    private String selectContent;
    private String commentContent;
    private Integer bookScore;
    private Integer numFavor;
    private Integer numReply;
    private Integer isDisp;
    private Integer isDel;
    private Integer userId;
    private String nickname;
    private String photo;
    private Boolean hasFavor;
    private BigInteger createTime;
    private Integer createBy;
    private BigInteger modifyTime;
    private Integer modifyBy;
    private BigInteger createTimeStart;
    private BigInteger createTimeEnd;

    public Integer getDynaSquireId() {
        return dynaSquireId;
    }

    public void setDynaSquireId(Integer dynaSquireId) {
        this.dynaSquireId = dynaSquireId;
    }

    public Integer getBookUserId() {
        return bookUserId;
    }

    public void setBookUserId(Integer bookUserId) {
        this.bookUserId = bookUserId;
    }

    public Integer getSquireBusType() {
        return squireBusType;
    }

    public void setSquireBusType(Integer squireBusType) {
        this.squireBusType = squireBusType;
    }

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getCoverPath() {
        return coverPath;
    }

    public void setCoverPath(String coverPath) {
        this.coverPath = coverPath;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getSelectContent() {
        return selectContent;
    }

    public void setSelectContent(String selectContent) {
        this.selectContent = selectContent;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Integer getBookScore() {
        return bookScore;
    }

    public void setBookScore(Integer bookScore) {
        this.bookScore = bookScore;
    }

    public Integer getNumFavor() {
        return numFavor;
    }

    public void setNumFavor(Integer numFavor) {
        this.numFavor = numFavor;
    }

    public Integer getNumReply() {
        return numReply;
    }

    public void setNumReply(Integer numReply) {
        this.numReply = numReply;
    }

    public Integer getIsDisp() {
        return isDisp;
    }

    public void setIsDisp(Integer isDisp) {
        this.isDisp = isDisp;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Boolean getHasFavor() {
        return hasFavor;
    }

    public void setHasFavor(Boolean hasFavor) {
        this.hasFavor = hasFavor;
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

    public BigInteger getCreateTimeStart() {
        return createTimeStart;
    }

    public void setCreateTimeStart(BigInteger createTimeStart) {
        this.createTimeStart = createTimeStart;
    }

    public BigInteger getCreateTimeEnd() {
        return createTimeEnd;
    }

    public void setCreateTimeEnd(BigInteger createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }

    @Override
    public String toString() {
        return "DynaSquireDTO{"
                + "dynaSquireId=" + dynaSquireId
                + ", bookUserId=" + bookUserId
                + ", squireBusType=" + squireBusType
                + ", businessId=" + businessId
                + ", bookName='" + bookName + '\''
                + ", coverPath='" + coverPath + '\''
                + ", bookAuthor='" + bookAuthor + '\''
                + ", selectContent='" + selectContent + '\''
                + ", commentContent='" + commentContent + '\''
                + ", bookScore=" + bookScore
                + ", numFavor=" + numFavor
                + ", numReply=" + numReply
                + ", isDisp=" + isDisp
                + ", isDel=" + isDel
                + ", userId=" + userId
                + ", nickname='" + nickname + '\''
                + ", photo='" + photo + '\''
                + ", hasFavor=" + hasFavor
                + ", createTime=" + createTime
                + ", createBy=" + createBy
                + ", modifyTime=" + modifyTime
                + ", modifyBy=" + modifyBy
                + ", createTimeStart=" + createTimeStart
                + ", createTimeEnd=" + createTimeEnd
                + '}';
    }
}
