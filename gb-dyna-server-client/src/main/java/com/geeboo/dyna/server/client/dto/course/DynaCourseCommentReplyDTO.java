package com.geeboo.dyna.server.client.dto.course;

import com.geeboo.dyna.server.client.dto.ParentDTO;

import java.io.Serializable;
import java.math.BigInteger;

public class DynaCourseCommentReplyDTO extends ParentDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    protected Integer dynaCourseCommentReplyId;
    private Integer userId;
    private Integer courseId;
    protected Integer dynaCourseCommentId;
    private Integer toCommentReplyId;
    private Integer isDel;
    private String replyContent;
    private Integer toUserId;
    private Integer replyType;
    private BigInteger createTime;
    private Integer createBy;
    private BigInteger modifyTime;
    private Integer modifyBy;

    public Integer getDynaCourseCommentReplyId() {
        return dynaCourseCommentReplyId;
    }

    public void setDynaCourseCommentReplyId(Integer dynaCourseCommentReplyId) {
        this.dynaCourseCommentReplyId = dynaCourseCommentReplyId;
    }

    /**
     * 返回 user_id,回复者
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

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getDynaCourseCommentId() {
        return dynaCourseCommentId;
    }

    public void setDynaCourseCommentId(Integer dynaCourseCommentId) {
        this.dynaCourseCommentId = dynaCourseCommentId;
    }

    /**
     * 返回 to_comment_reply_id,被回复的回复id
     */
    public Integer getToCommentReplyId() {
        return toCommentReplyId;
    }

    /**
     * 参数 toCommentReplyId
     */
    public void setToCommentReplyId(Integer toCommentReplyId) {
        this.toCommentReplyId = toCommentReplyId;
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
     * 返回 reply_content,回复内容
     */
    public String getReplyContent() {
        return replyContent;
    }

    /**
     * 参数 replyContent
     */
    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    /**
     * 返回 to_user_id,被回复者id
     */
    public Integer getToUserId() {
        return toUserId;
    }

    /**
     * 参数 toUserId
     */
    public void setToUserId(Integer toUserId) {
        this.toUserId = toUserId;
    }

    /**
     * 返回 reply_type,回复类型( 字典:1评论,2回复.默认为1)
     */
    public Integer getReplyType() {
        return replyType;
    }

    /**
     * 参数 replyType
     */
    public void setReplyType(Integer replyType) {
        this.replyType = replyType;
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
        return "DynaCourseCommentReplyDTO{" + "dynaCourseCommentReplyId=" + dynaCourseCommentReplyId + ", userId="
                    + userId + ", courseId=" + courseId + ", dynaCourseCommentId=" + dynaCourseCommentId
                    + ", toCommentReplyId=" + toCommentReplyId + ", isDel=" + isDel + ", replyContent='" + replyContent
                    + '\'' + ", toUserId=" + toUserId + ", replyType=" + replyType + ", createTime=" + createTime
                    + ", createBy=" + createBy + ", modifyTime=" + modifyTime + ", modifyBy=" + modifyBy + '}';
    }
}
