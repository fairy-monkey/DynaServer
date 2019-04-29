package com.geeboo.dyna.server.client.dto.course;

import java.io.Serializable;
import java.math.BigInteger;

public class DynaCourseFavorDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    protected Integer dynaCourseFavorId;
    private Integer courseId;
    private Integer dynaCourseCommentId;
    private Integer userId;
    private Integer isFavor;
    private BigInteger createTime;
    private Integer createBy;
    private BigInteger modifyTime;
    private Integer modifyBy;

    public Integer getDynaCourseFavorId() {
        return dynaCourseFavorId;
    }

    public void setDynaCourseFavorId(Integer dynaCourseFavorId) {
        this.dynaCourseFavorId = dynaCourseFavorId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    /**
     * 返回 dyna_topic_comment_id,评论id
     */
    public Integer getDynaCourseCommentId() {
        return dynaCourseCommentId;
    }

    /**
     * 参数 dynaCourseCommentId
     */
    public void setDynaCourseCommentId(Integer dynaCourseCommentId) {
        this.dynaCourseCommentId = dynaCourseCommentId;
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
        return "DynaCourseFavorDTO{" + "dynaCourseFavorId=" + dynaCourseFavorId + ", courseId=" + courseId
            + ", dynaCourseCommentId=" + dynaCourseCommentId + ", userId=" + userId + ", isFavor=" + isFavor
            + ", createTime=" + createTime + ", createBy=" + createBy + ", modifyTime=" + modifyTime + ", modifyBy="
            + modifyBy + '}';
    }
}


