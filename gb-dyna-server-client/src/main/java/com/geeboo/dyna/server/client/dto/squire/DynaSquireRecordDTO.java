package com.geeboo.dyna.server.client.dto.squire;

import com.geeboo.dyna.server.client.dto.ParentDTO;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * Title: <br>
 * Description: Copyright: Copyright (c) 2018
 *
 * @author 郭明毅 guomy 创建时间:2018/9/28 19:39
 */
public class DynaSquireRecordDTO extends ParentDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    protected Integer dynaSquireId;
    private Integer userId;
    private Integer bookUserId;
    // 档案ID
    private Integer businessId;
    private String bookName;
    private String coverPath;
    private String bookAuthor;
    private String selectContent;
    private String commentContent;
    private BigInteger createTime;
    private Integer createBy;

    public DynaSquireRecordDTO() {
    }

    public DynaSquireRecordDTO(Integer bookUserId, Integer businessId) {
        this.bookUserId = bookUserId;
        this.businessId = businessId;
    }

    public Integer getDynaSquireId() {
        return dynaSquireId;
    }

    public void setDynaSquireId(Integer dynaSquireId) {
        this.dynaSquireId = dynaSquireId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBookUserId() {
        return bookUserId;
    }

    public void setBookUserId(Integer bookUserId) {
        this.bookUserId = bookUserId;
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

    @Override
    public String toString() {
        return "DynaSquireRecordDTO{" + "dynaSquireId=" + dynaSquireId + ", userId=" + userId + ", bookUserId="
            + bookUserId + ", businessId=" + businessId + ", bookName='" + bookName + '\'' + ", coverPath='" + coverPath
            + '\'' + ", bookAuthor='" + bookAuthor + '\'' + ", selectContent='" + selectContent + '\''
            + ", commentContent='" + commentContent + '\'' + ", createTime=" + createTime + ", createBy=" + createBy
            + '}';
    }
}
