package com.geeboo.dyna.server.eventbus.dto.squire;

import com.geeboo.dyna.server.constant.OperateEnum;

/**
 * Title: <br>
 * Description: Copyright: Copyright (c) 2018
 *
 * @author 郭明毅 guomy 创建时间:2018/10/8 15:37
 */
public class SquireEventDTO implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private Integer businessId;
    private Boolean squireCount;
    private Boolean favorNum;
    private Boolean commentNum;
    private OperateEnum operate;

    public SquireEventDTO() {
    }

    public SquireEventDTO bussinessId(Integer businessId) {
        this.businessId = businessId;
        return this;
    }

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public SquireEventDTO squireCount(boolean squireCount) {
        this.squireCount = squireCount;
        return this;
    }

    public SquireEventDTO favorNum(boolean favorNum) {
        this.favorNum = favorNum;
        return this;
    }

    public Boolean getSquireCount() {
        return squireCount;
    }

    public void setSquireCount(Boolean squireCount) {
        this.squireCount = squireCount;
    }

    public Boolean getFavorNum() {
        return favorNum;
    }

    public void setFavorNum(Boolean favorNum) {
        this.favorNum = favorNum;
    }

    public SquireEventDTO commentNum(Boolean commentNum) {
        this.commentNum = commentNum;
        return this;
    }

    public Boolean getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Boolean commentNum) {
        this.commentNum = commentNum;
    }

    public SquireEventDTO operate(OperateEnum operate) {
        this.operate = operate;
        return this;
    }

    public OperateEnum getOperate() {
        return operate;
    }

    public void setOperate(OperateEnum operate) {
        this.operate = operate;
    }

    @Override
    public String toString() {
        return "SquireEventDTO{" + "businessId=" + businessId + ", squireCount=" + squireCount + ", favorNum="
            + favorNum + ", operate=" + operate + '}';
    }
}
