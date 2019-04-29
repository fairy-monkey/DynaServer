package com.geeboo.dyna.server.client.dto.book;

/**
 * Title: 点赞<br>
 * Description: Copyright: Copyright (c) 2018
 *
 * @author 郭明毅 guomy 创建时间:2018/9/17 18:08
 */
public class DynaBookCommentFavorDTO implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    protected Integer dynaBookCommentId;
    private Integer bookUserId;
    private Integer userId;
    /**
     * 1-点赞；0-取消
     */
    private Integer operateType;

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

    public Integer getOperateType() {
        return operateType;
    }

    public void setOperateType(Integer operateType) {
        this.operateType = operateType;
    }

    @Override
    public String toString() {
        return "DynaBookCommentFavorDTO{" + "dynaBookCommentId=" + dynaBookCommentId + ", bookUserId=" + bookUserId
            + ", userId=" + userId + ", operateType=" + operateType + '}';
    }
}
