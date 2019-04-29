package com.geeboo.dyna.server.client.dto.topic;

/**
 * Title: 点赞<br>
 * Description: Copyright: Copyright (c) 2018
 *
 * @author 郭明毅 guomy 创建时间:2018/9/17 18:08
 */
public class DynaTopicCommentFavorDTO implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    protected Integer dynaTopicCommentId;
    private Integer dynaTopicId;
    private Integer userId;
    /**
     * 1-点赞；0-取消
     */
    private Integer operateType;

    public Integer getDynaTopicCommentId() {
        return dynaTopicCommentId;
    }

    public void setDynaTopicCommentId(Integer dynaTopicCommentId) {
        this.dynaTopicCommentId = dynaTopicCommentId;
    }

    public Integer getDynaTopicId() {
        return dynaTopicId;
    }

    public void setDynaTopicId(Integer dynaTopicId) {
        this.dynaTopicId = dynaTopicId;
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
        return "DynaTopicCommentFavorDTO{" + "dynaTopicCommentId=" + dynaTopicCommentId + ", dynaTopicId=" + dynaTopicId
            + ", userId=" + userId + ", operateType=" + operateType + '}';
    }
}
