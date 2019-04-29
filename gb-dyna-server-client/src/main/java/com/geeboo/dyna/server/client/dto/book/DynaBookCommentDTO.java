package com.geeboo.dyna.server.client.dto.book;

import com.geeboo.dyna.server.client.dto.ParentDTO;

import java.io.Serializable;
import java.math.BigInteger;

public class DynaBookCommentDTO extends ParentDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    protected Integer dynaBookCommentId;
    private Integer userId;
    private Integer bookUserId;
    private Integer numReply;
    private Integer numFavor;
    private Integer isDel;
    private String commentContent;
    private Integer indexNo;
    private Byte bookScore;
    private BigInteger createTime;
    private Integer createBy;
    private BigInteger modifyTime;
    private Integer modifyBy;

    public DynaBookCommentDTO() {
    }

    public DynaBookCommentDTO(Integer bookUserId) {
        this.bookUserId = bookUserId;
    }

    public Integer getDynaBookCommentId() {
        return dynaBookCommentId;
    }

    public void setDynaBookCommentId(Integer dynaBookCommentId) {
        this.dynaBookCommentId = dynaBookCommentId;
    }

    /**
     * 返回 user_id,用户id
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

    public Integer getBookUserId() {
        return bookUserId;
    }

    public void setBookUserId(Integer bookUserId) {
        this.bookUserId = bookUserId;
    }


    /**
     * 返回 num_reply,回复数
     */
    public Integer getNumReply() {
        return numReply;
    }

    /**
     * 参数 numReply
     */
    public void setNumReply(Integer numReply) {
        this.numReply = numReply;
    }

    /**
     * 返回 num_favor,点赞数
     */
    public Integer getNumFavor() {
        return numFavor;
    }

    /**
     * 参数 numFavor
     */
    public void setNumFavor(Integer numFavor) {
        this.numFavor = numFavor;
    }

    /**
     * 返回 is_del,是否删除(1是,0否,默认为0）
     */
    public Integer getIsDel() {
        return isDel;
    }

    /**
     * 参数 isDel
     */
    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    /**
     * 返回 comment_content,评论内容
     */
    public String getCommentContent() {
        return commentContent;
    }

    /**
     * 参数 commentContent
     */
    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    /**
     * 返回 index_no,排序
     */
    public Integer getIndexNo() {
        return indexNo;
    }

    /**
     * 参数 indexNo
     */
    public void setIndexNo(Integer indexNo) {
        this.indexNo = indexNo;
    }

    public Byte getBookScore() {
        return bookScore;
    }

    public void setBookScore(Byte bookScore) {
        this.bookScore = bookScore;
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
        return "DynaBookCommentDTO{"
                + "dynaBookCommentId=" + dynaBookCommentId
                + ", userId=" + userId
                + ", bookUserId=" + bookUserId
                + ", numReply=" + numReply
                + ", numFavor=" + numFavor
                + ", isDel=" + isDel
                + ", commentContent='" + commentContent
                + ", indexNo=" + indexNo
                + ", bookScore=" + bookScore
                + ", createTime=" + createTime
                + ", createBy=" + createBy
                + ", modifyTime=" + modifyTime
                + ", modifyBy=" + modifyBy
                + '}';
    }
}
