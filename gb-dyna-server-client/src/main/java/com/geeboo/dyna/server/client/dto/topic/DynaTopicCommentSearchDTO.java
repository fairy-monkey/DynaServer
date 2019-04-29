package com.geeboo.dyna.server.client.dto.topic;

import java.util.List;

/**
 * 话题列表查询参数
 */
public class DynaTopicCommentSearchDTO {
    private Integer dynaTopicId;
    private Integer idiotId;
    private Integer userId;
    private List<Integer> userCacheCommentIds;
    private Integer startNo;
    private Integer pageSize;

    public Integer getDynaTopicId() {
        return dynaTopicId;
    }

    public void setDynaTopicId(Integer dynaTopicId) {
        this.dynaTopicId = dynaTopicId;
    }

    public Integer getIdiotId() {
        return idiotId;
    }

    public void setIdiotId(Integer idiotId) {
        this.idiotId = idiotId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<Integer> getUserCacheCommentIds() {
        return userCacheCommentIds;
    }

    public void setUserCacheCommentIds(List<Integer> userCacheCommentIds) {
        this.userCacheCommentIds = userCacheCommentIds;
    }

    public Integer getStartNo() {
        return startNo;
    }

    public void setStartNo(Integer startNo) {
        this.startNo = startNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "DynaTopicCommentSearchDTO{"
                + "dynaTopicId=" + dynaTopicId
                + ", idiotId=" + idiotId
                + ", userId=" + userId
                + ", userCacheCommentIds=" + userCacheCommentIds
                + ", startNo=" + startNo
                + ", pageSize=" + pageSize
                + '}';
    }
}
