package com.geeboo.dyna.server.client.dto.squire;

import com.geeboo.dyna.server.client.dto.ParentDTO;

import java.math.BigInteger;

/**
 * Title: <br>
 * Description: Copyright: Copyright (c) 2018
 *
 * @author 郭明毅 guomy 创建时间:2018/10/9 14:58
 */
public class DynaSquireCommentListDTO extends ParentDTO implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    protected Integer dynaSquireCommentId;
    private String commentContent;
    private Integer commentType;
    private Integer userId;
    private String nickname;
    private String photo;
    private Integer dynaSquireId;
    private Integer numReply;
    private Integer numFavor;
    private Boolean hasFavor;
    private Integer toUserId;
    private String toNickname;
    private BigInteger createTime;

    public Integer getDynaSquireCommentId() {
        return dynaSquireCommentId;
    }

    public void setDynaSquireCommentId(Integer dynaSquireCommentId) {
        this.dynaSquireCommentId = dynaSquireCommentId;
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
        return "DynaSquireCommentListDTO{" + "dynaSquireCommentId=" + dynaSquireCommentId + ", commentContent='"
                    + commentContent + '\'' + ", commentType=" + commentType + ", userId=" + userId + ", nickname='"
                    + nickname + '\'' + ", photo='" + photo + '\'' + ", dynaSquireId=" + dynaSquireId + ", numReply="
                    + numReply + ", numFavor=" + numFavor + ", hasFavor=" + hasFavor + ", toUserId=" + toUserId
                    + ", toNickname='" + toNickname + '\'' + ", createTime=" + createTime + '}';
    }
}
