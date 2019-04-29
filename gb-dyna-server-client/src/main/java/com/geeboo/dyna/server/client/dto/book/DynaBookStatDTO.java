package com.geeboo.dyna.server.client.dto.book;

import java.io.Serializable;
import java.math.BigInteger;

public class DynaBookStatDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    protected Integer dynaBookStatId;
    private Integer bookUserId;
    private Integer numView;
    private Integer numComment;
    private Integer numReply;
    private Integer numTotal;
    private Integer firstUserId;
    private Integer secondUserId;
    private Integer thirdUserId;
    private BigInteger createTime;
    private Integer createBy;
    private BigInteger modifyTime;
    private Integer modifyBy;

    public static DynaBookStatDTO getNullInstance() {
        DynaBookStatDTO
            dto = new DynaBookStatDTO();
        dto.setNumView(0);
        dto.setNumComment(0);
        dto.setNumReply(0);
        dto.setNumTotal(0);
        dto.setFirstUserId(0);
        dto.setSecondUserId(0);
        dto.setThirdUserId(0);
        dto.setFirstUserId(0);
        dto.setSecondUserId(0);
        dto.setThirdUserId(0);
        dto.setCreateBy(0);
        dto.setCreateTime(BigInteger.ZERO);
        dto.setModifyBy(0);
        dto.setModifyTime(BigInteger.ZERO);
        return dto;
    }

    public Integer getDynaBookStatId() {
        return dynaBookStatId;
    }

    public void setDynaBookStatId(Integer dynaBookStatId) {
        this.dynaBookStatId = dynaBookStatId;
    }

    public Integer getBookUserId() {
        return bookUserId;
    }

    public void setBookUserId(Integer bookUserId) {
        this.bookUserId = bookUserId;
    }

    /**
     * 返回 num_view,浏览数
     */
    public Integer getNumView() {
        return numView;
    }

    /**
     * 参数 numView
     */
    public void setNumView(Integer numView) {
        this.numView = numView;
    }

    /**
     * 返回 num_comment,参与评论数
     */
    public Integer getNumComment() {
        return numComment;
    }

    /**
     * 参数 numComment
     */
    public void setNumComment(Integer numComment) {
        this.numComment = numComment;
    }

    /**
     * 返回 num_reply,总回复数
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
     * 返回 num_total,总参与数
     */
    public Integer getNumTotal() {
        return numTotal;
    }

    /**
     * 参数 numTotal
     */
    public void setNumTotal(Integer numTotal) {
        this.numTotal = numTotal;
    }

    /**
     * 返回 first_user_id,最近第一个用户
     */
    public Integer getFirstUserId() {
        return firstUserId;
    }

    /**
     * 参数 firstUserId
     */
    public void setFirstUserId(Integer firstUserId) {
        this.firstUserId = firstUserId;
    }

    /**
     * 返回 second_user_id,最近第二个用户
     */
    public Integer getSecondUserId() {
        return secondUserId;
    }

    /**
     * 参数 secondUserId
     */
    public void setSecondUserId(Integer secondUserId) {
        this.secondUserId = secondUserId;
    }

    /**
     * 返回 third_user_id,最近第三个用户
     */
    public Integer getThirdUserId() {
        return thirdUserId;
    }

    /**
     * 参数 thirdUserId
     */
    public void setThirdUserId(Integer thirdUserId) {
        this.thirdUserId = thirdUserId;
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
        return "DynaBookStatDTO{" + "dynaBookStatId=" + dynaBookStatId + ", bookUserId=" + bookUserId + ", numView="
            + numView + ", numComment=" + numComment + ", numReply=" + numReply + ", numTotal=" + numTotal
            + ", firstUserId=" + firstUserId + ", secondUserId=" + secondUserId + ", thirdUserId=" + thirdUserId
            + ", createTime=" + createTime + ", createBy=" + createBy + ", modifyTime=" + modifyTime + ", modifyBy="
            + modifyBy + '}';
    }
}


