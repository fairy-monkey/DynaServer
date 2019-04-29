package com.geeboo.dyna.server.client.dto.course;

/**
 * Title: 点赞<br>
 * Description: Copyright: Copyright (c) 2018
 *
 * @author 郭明毅 guomy 创建时间:2018/9/17 18:08
 */
public class DynaCourseCommentFavorDTO implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    protected Integer dynaCourseCommentId;
    private Integer courseId;
    private Integer userId;
    /**
     * 1-点赞；0-取消
     */
    private Integer operateType;

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

    public Integer getOperateType() {
        return operateType;
    }

    public void setOperateType(Integer operateType) {
        this.operateType = operateType;
    }

    @Override
    public String toString() {
        return "DynaCourseCommentFavorDTO{" + "dynaCourseCommentId=" + dynaCourseCommentId + ", courseId=" + courseId
            + ", userId=" + userId + ", operateType=" + operateType + '}';
    }
}
