package com.geeboo.dyna.server.client.dto.book;

import java.io.Serializable;
import java.math.BigInteger;

public class DynaBookFavorDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    protected Integer dynaBookFavorId;
    private Integer bookUserId;
    private Integer dynaBookCommentId;
    private Integer userId;
    private Integer isFavor;
    private BigInteger createTime;
    private Integer createBy;
    private BigInteger modifyTime;
    private Integer modifyBy;

    public Integer getDynaBookFavorId() {
        return dynaBookFavorId;
    }

    public void setDynaBookFavorId(Integer dynaBookFavorId) {
        this.dynaBookFavorId = dynaBookFavorId;
    }

    public Integer getBookUserId() {
        return bookUserId;
    }

    public void setBookUserId(Integer bookUserId) {
        this.bookUserId = bookUserId;
    }

    /**
     * 返回 dyna_topic_comment_id,评论id
     */
    public Integer getDynaBookCommentId() {
        return dynaBookCommentId;
    }

    /**
     * 参数 dynaBookCommentId
     */
    public void setDynaBookCommentId(Integer dynaBookCommentId) {
        this.dynaBookCommentId = dynaBookCommentId;
    }

    /**
     * 返回 user_id,点赞者
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 参数 userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 返回 is_favor,是否点赞(1是,0否,即点赞取消,,默认为1)
     */
    public Integer getIsFavor() {
        return isFavor;
    }

    /**
     * 参数 isFavor
     */
    public void setIsFavor(Integer isFavor) {
        this.isFavor = isFavor;
    }

    /**
     * 返回 create_time,创建时间
     */
    public BigInteger getCreateTime() {
        return createTime;
    }

    /**
     * 参数 createTime
     */
    public void setCreateTime(BigInteger createTime) {
        this.createTime = createTime;
    }

    /**
     * 返回 create_by,创建人
     */
    public Integer getCreateBy() {
        return createBy;
    }

    /**
     * 参数 createBy
     */
    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    /**
     * 返回 modify_time,修改时间
     */
    public BigInteger getModifyTime() {
        return modifyTime;
    }

    /**
     * 参数 modifyTime
     */
    public void setModifyTime(BigInteger modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * 返回 modify_by,修改人
     */
    public Integer getModifyBy() {
        return modifyBy;
    }

    /**
     * 参数 modifyBy
     */
    public void setModifyBy(Integer modifyBy) {
        this.modifyBy = modifyBy;
    }

    @Override
    public String toString() {
        return "DynaBookFavorDTO{" + "dynaBookFavorId=" + dynaBookFavorId + ", bookUserId=" + bookUserId
            + ", dynaBookCommentId=" + dynaBookCommentId + ", userId=" + userId + ", isFavor=" + isFavor
            + ", createTime=" + createTime + ", createBy=" + createBy + ", modifyTime=" + modifyTime + ", modifyBy="
            + modifyBy + '}';
    }
}


