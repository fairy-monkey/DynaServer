package com.geeboo.dyna.server.client.dto.topic;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

/**
 * Title: APP列表DTO<br>
 * Description: Copyright: Copyright (c) 2018
 *
 * @author 郭明毅 guomy 创建时间:2018/9/12 13:42
 */
public class DynaTopicListDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    protected Integer dynaTopicId;
    private String topicTitle;
    private Integer topicType;
    private String backgroundColor;
    private BigInteger publishTime;
    private Integer numComment;
    private Integer numReply;
    private Integer numTotal;
    private String content;
    private List<String> accountPhotoList;

    public Integer getDynaTopicId() {
        return dynaTopicId;
    }

    public void setDynaTopicId(Integer dynaTopicId) {
        this.dynaTopicId = dynaTopicId;
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    public Integer getTopicType() {
        return topicType;
    }

    public void setTopicType(Integer topicType) {
        this.topicType = topicType;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public BigInteger getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(BigInteger publishTime) {
        this.publishTime = publishTime;
    }

    public Integer getNumComment() {
        return numComment;
    }

    public void setNumComment(Integer numComment) {
        this.numComment = numComment;
    }

    public Integer getNumReply() {
        return numReply;
    }

    public void setNumReply(Integer numReply) {
        this.numReply = numReply;
    }

    public Integer getNumTotal() {
        return numTotal;
    }

    public void setNumTotal(Integer numTotal) {
        this.numTotal = numTotal;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getAccountPhotoList() {
        return accountPhotoList;
    }

    public void setAccountPhotoList(List<String> accountPhotoList) {
        this.accountPhotoList = accountPhotoList;
    }

    @Override
    public String toString() {
        return "DynaTopicListDTO{" + "dynaTopicId=" + dynaTopicId + ", topicTitle='" + topicTitle + '\''
            + ", topicType=" + topicType + ", backgroundColor='" + backgroundColor + '\'' + ", publishTime="
            + publishTime + ", numComment=" + numComment + ", numReply=" + numReply + ", numTotal=" + numTotal
            + ", content='" + content + '\'' + ", accountPhotoList=" + accountPhotoList + '}';
    }
}
