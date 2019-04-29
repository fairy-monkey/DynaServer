package com.geeboo.dyna.server.client.dto.book;

import com.geeboo.dyna.server.client.dto.ParentDTO;

import java.math.BigInteger;

/**
 * Title: <br>
 * Description: Copyright: Copyright (c) 2018
 *
 * @author 郭明毅 guomy 创建时间:2018/9/15 18:09
 */
public class DynaBookReplyListDTO extends ParentDTO implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    protected Integer dynaBookCommentReplyId;
    private Integer dynaBookCommentId;
    private Integer bookUserId;
    private Integer toCommentReplyId;
    private String replyContent;
    private Integer userId;
    private String nickname;
    private String photo;
    private Boolean isVip;
    private Integer toUserId;
    private String toNickname;
    private String toPhoto;
    private Boolean toIsVip;
    private Integer replyType;
    private BigInteger createTime;

    public Integer getDynaBookCommentReplyId() {
        return dynaBookCommentReplyId;
    }

    public void setDynaBookCommentReplyId(Integer dynaBookCommentReplyId) {
        this.dynaBookCommentReplyId = dynaBookCommentReplyId;
    }

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

    public Integer getToCommentReplyId() {
        return toCommentReplyId;
    }

    public void setToCommentReplyId(Integer toCommentReplyId) {
        this.toCommentReplyId = toCommentReplyId;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
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

    public Integer getToUserId() {
        return toUserId;
    }

    public void setToUserId(Integer toUserId) {
        this.toUserId = toUserId;
    }

    public String getToNickname() {
        return toNickname;
    }

    public void setToNickname(String toNickname) {
        this.toNickname = toNickname;
    }

    public String getToPhoto() {
        return toPhoto;
    }

    public void setToPhoto(String toPhoto) {
        this.toPhoto = toPhoto;
    }

    public Integer getReplyType() {
        return replyType;
    }

    public void setReplyType(Integer replyType) {
        this.replyType = replyType;
    }

    public BigInteger getCreateTime() {
        return createTime;
    }

    public void setCreateTime(BigInteger createTime) {
        this.createTime = createTime;
    }

    public Boolean getVip() {
        return isVip;
    }

    public void setVip(Boolean vip) {
        isVip = vip;
    }

    public Boolean getToIsVip() {
        return toIsVip;
    }

    public void setToIsVip(Boolean toIsVip) {
        this.toIsVip = toIsVip;
    }

    @Override
    public String toString() {
        return "DynaBookReplyListDTO{" + "dynaBookCommentReplyId=" + dynaBookCommentReplyId + ", dynaBookCommentId="
                    + dynaBookCommentId + ", bookUserId=" + bookUserId + ", toCommentReplyId=" + toCommentReplyId
                    + ", replyContent='" + replyContent + '\'' + ", userId=" + userId + ", nickname='" + nickname + '\''
                    + ", photo='" + photo + '\'' + ", isVip=" + isVip + ", toUserId=" + toUserId + ", toNickname='"
                    + toNickname + '\'' + ", toPhoto='" + toPhoto + '\'' + ", toIsVip=" + toIsVip + ", replyType="
                    + replyType + ", createTime=" + createTime + '}';
    }
}
