package com.geeboo.dyna.server.client.dto.book;

import com.geeboo.dyna.server.client.dto.ParentDTO;

import java.math.BigInteger;

/**
 * Title: <br>
 * Description: Copyright: Copyright (c) 2018
 *
 * @author 郭明毅 guomy 创建时间:2018/9/13 21:46
 */
public class DynaBookCommentListDTO extends ParentDTO implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    protected Integer dynaBookCommentId;
    private Integer bookUserId;
    private Integer userId;
    private String nickname;
    private String photo;
    private String commentContent;
    private Integer numReply;
    private Integer numFavor;
    private Boolean hasFavor;
    private Byte bookScore;
    private BigInteger createTime;
    private String bookName;
    private String bookAuthor;
    private String coverPath;

    public Integer getDynaBookCommentId() {
        return dynaBookCommentId;
    }

    public void setDynaBookCommentId(Integer dynaBookCommentId) {
        this.dynaBookCommentId = dynaBookCommentId;
    }

    public Integer getBookUserId() {
        return bookUserId;
    }

    public void setBookUserId(Integer bookUserId) {
        this.bookUserId = bookUserId;
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

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
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

    public Byte getBookScore() {
        return bookScore;
    }

    public void setBookScore(Byte bookScore) {
        this.bookScore = bookScore;
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

    public String getCoverPath() {
        return coverPath;
    }

    public void setCoverPath(String coverPath) {
        this.coverPath = coverPath;
    }

    @Override
    public String toString() {
        return "DynaBookCommentListDTO{" + "dynaBookCommentId=" + dynaBookCommentId + ", bookUserId=" + bookUserId
                    + ", userId=" + userId + ", nickname='" + nickname + '\'' + ", photo='" + photo + '\''
                    + ", commentContent='" + commentContent + '\'' + ", numReply=" + numReply + ", numFavor=" + numFavor
                    + ", hasFavor=" + hasFavor + ", bookScore=" + bookScore + ", createTime=" + createTime + '}';
    }
}
