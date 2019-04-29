package com.geeboo.dyna.server.client.dto.digest;

import java.io.Serializable;
import java.math.BigInteger;

public class DynaDigestListDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer dynaDigestId;
    private Integer bookUserId;
    private String digestContent;
    private String imagePath;
    private Integer numSupport;
    private BigInteger createTime;
    private String bookName;
    private String bookAuthor;
    private String wholeImagePath;
    private Integer bookPropId;
    private Integer publishStatus;

    //用户Id
    private Integer userId;
    //是否点赞 （1是，0否）
    private Integer isFavor;

    public void setPublishStatus(Integer publishStatus) {
        this.publishStatus = publishStatus;
    }

    public Integer getPublishStatus() {
        return publishStatus;
    }

    public void setBookPropId(Integer bookPropId) {
        this.bookPropId = bookPropId;
    }

    public Integer getBookPropId() {
        return bookPropId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setIsFavor(Integer isFavor) {
        this.isFavor = isFavor;
    }

    public Integer getIsFavor() {
        return isFavor;
    }

    public Integer getDynaDigestId() {
        return dynaDigestId;
    }

    public void setDynaDigestId(Integer dynaDigestId) {
        this.dynaDigestId = dynaDigestId;
    }

    public Integer getBookUserId() {
        return bookUserId;
    }

    public void setBookUserId(Integer bookUserId) {
        this.bookUserId = bookUserId;
    }

    public String getDigestContent() {
        return digestContent;
    }

    public void setDigestContent(String digestContent) {
        this.digestContent = digestContent;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Integer getNumSupport() {
        return numSupport;
    }

    public void setNumSupport(Integer numSupport) {
        this.numSupport = numSupport;
    }

    public BigInteger getCreateTime() {
        return createTime;
    }

    public void setCreateTime(BigInteger modifyTime) {
        this.createTime = modifyTime;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getWholeImagePath() {
        return wholeImagePath;
    }

    public void setWholeImagePath(String wholeImagePath) {
        this.wholeImagePath = wholeImagePath;
    }

    @Override
    public String toString() {
        return "DynaDigestListDTO{" + "dynaDigestId=" + dynaDigestId + ", bookUserId=" + bookUserId
                + ", bookPropId=" + bookPropId + ", userId=" + userId + ", isFavor=" + isFavor
                + ", digestContent='" + digestContent + ", imagePath='" + imagePath + ", numSupport=" + numSupport
                + ", createTime=" + createTime + ", bookName='" + bookName + ", bookAuthor='" + bookAuthor
                + ", wholeImagePath='" + wholeImagePath + ",publishStatus=" + publishStatus + '}';
    }
}
