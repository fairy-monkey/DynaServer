package com.geeboo.dyna.server.client.dto.course;

import com.geeboo.dyna.server.client.dto.ParentDTO;

import java.math.BigInteger;

/**
 * Title: <br>
 * Description: Copyright: Copyright (c) 2018
 *
 * @author 郭明毅 guomy 创建时间:2018/9/13 21:46
 */
public class DynaCourseCommentListDTO extends ParentDTO implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    protected Integer dynaCourseCommentId;
    private Integer courseId;
    private Integer userId;
    private String nickname;
    private String photo;
    private String commentContent;
    private Integer numReply;
    private Integer numFavor;
    private Boolean hasFavor;
    private BigInteger createTime;

    public Integer getDynaCourseCommentId() {
        return dynaCourseCommentId;
    }

    public void setDynaCourseCommentId(Integer dynaCourseCommentId) {
        this.dynaCourseCommentId = dynaCourseCommentId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
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

    @Override
    public String toString() {
        return "DynaCourseCommentListDTO{" + "dynaCourseCommentId=" + dynaCourseCommentId + ", courseId=" + courseId
                    + ", userId=" + userId + ", nickname='" + nickname + '\'' + ", photo='" + photo + '\''
                    + ", commentContent='" + commentContent + '\'' + ", numReply=" + numReply + ", numFavor=" + numFavor
                    + ", hasFavor=" + hasFavor + ", createTime=" + createTime + '}';
    }
}
