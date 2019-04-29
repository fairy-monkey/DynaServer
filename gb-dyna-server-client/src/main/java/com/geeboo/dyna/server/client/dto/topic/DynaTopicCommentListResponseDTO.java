package com.geeboo.dyna.server.client.dto.topic;

import java.util.List;

public class DynaTopicCommentListResponseDTO {

    private List<DynaTopicCommentListDTO> specialList;
    private List<DynaTopicCommentListDTO> commentList;
    private Integer dynaTopicCommentId;
    private Integer total;
    private List<String> excludeIds;

    public List<String> getExcludeIds() {
        return excludeIds;
    }

    public void setExcludeIds(List<String> excludeIds) {
        this.excludeIds = excludeIds;
    }

    public List<DynaTopicCommentListDTO> getSpecialList() {
        return specialList;
    }

    public void setSpecialList(List<DynaTopicCommentListDTO> specialList) {
        this.specialList = specialList;
    }

    public List<DynaTopicCommentListDTO> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<DynaTopicCommentListDTO> commentList) {
        this.commentList = commentList;
    }

    public Integer getDynaTopicCommentId() {
        return dynaTopicCommentId;
    }

    public void setDynaTopicCommentId(Integer dynaTopicCommentId) {
        this.dynaTopicCommentId = dynaTopicCommentId;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "DynaTopicCommentListResponseDTO{"
                + "specialList=" + specialList
                + ", commentList=" + commentList
                + ", dynaTopicCommentId=" + dynaTopicCommentId
                + ", total=" + total
                + ", excludeIds=" + excludeIds
                + '}';
    }
}
