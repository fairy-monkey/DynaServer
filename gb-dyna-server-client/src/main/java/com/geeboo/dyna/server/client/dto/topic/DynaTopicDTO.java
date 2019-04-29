package com.geeboo.dyna.server.client.dto.topic;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class DynaTopicDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    protected Integer dynaTopicId;
    private String topicTitle;
    private Integer topicType;
    private String backgroundColor;
    private Integer publishStatus;
    private BigInteger publishTime;
    private Integer indexNo;
    private Integer isDel;
    private Integer businessId;
    private BigInteger createTime;
    private Integer createBy;
    private BigInteger modifyTime;
    private Integer modifyBy;
    private String dynaTopicContent;
    private Integer numView;
    private Integer numComment;
    private Integer numReply;
    private BigInteger publishTimeStart;
    private BigInteger publishTimeEnd;
    private List<DynaTopicCommentDTO> commentList = new ArrayList<>();
    private List<String> photoList = new ArrayList<>();
    private String topicIndexPublisher; // 找书首页话题 书大痴 add by tangwei
    private String topicIndexPhoto; // 找书首页书大痴头像 add by tangwei
    private Integer numTotal; // 评论去重人数

    public Integer getDynaTopicId() {
        return dynaTopicId;
    }

    public void setDynaTopicId(Integer dynaTopicId) {
        this.dynaTopicId = dynaTopicId;
    }

    /**
     * 返回 topic_title,话题标题
     */
    public String getTopicTitle() {
        return topicTitle;
    }

    /**
     * 参数 topicTitle
     */
    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    /**
     * 返回 topic_type,话题类型(字典:1话题,2活动,3投票 默认为1)
     */
    public Integer getTopicType() {
        return topicType;
    }

    /**
     * 参数 topicType
     */
    public void setTopicType(Integer topicType) {
        this.topicType = topicType;
    }

    /**
     * 返回 background_color,背景色
     */
    public String getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * 参数 backgroundColor
     */
    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    /**
     * 返回 publish_status,发布状态(字典: 0未发布,1已发布, 2定时发布.默认为0)
     */
    public Integer getPublishStatus() {
        return publishStatus;
    }

    /**
     * 参数 publishStatus
     */
    public void setPublishStatus(Integer publishStatus) {
        this.publishStatus = publishStatus;
    }

    public BigInteger getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(BigInteger publishTime) {
        this.publishTime = publishTime;
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
     * 返回 business_id,业务id
     */
    public Integer getBusinessId() {
        return businessId;
    }

    /**
     * 参数 businessId
     */
    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
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

    public String getDynaTopicContent() {
        return dynaTopicContent;
    }

    public void setDynaTopicContent(String dynaTopicContent) {
        this.dynaTopicContent = dynaTopicContent;
    }

    public Integer getNumView() {
        return numView;
    }

    public void setNumView(Integer numView) {
        this.numView = numView;
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

    public BigInteger getPublishTimeStart() {
        return publishTimeStart;
    }

    public void setPublishTimeStart(BigInteger publishTimeStart) {
        this.publishTimeStart = publishTimeStart;
    }

    public BigInteger getPublishTimeEnd() {
        return publishTimeEnd;
    }

    public void setPublishTimeEnd(BigInteger publishTimeEnd) {
        this.publishTimeEnd = publishTimeEnd;
    }

    public List<DynaTopicCommentDTO> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<DynaTopicCommentDTO> commentList) {
        this.commentList = commentList;
    }

    public List<String> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(List<String> photoList) {
        this.photoList = photoList;
    }

    public String getTopicIndexPublisher() {
        return topicIndexPublisher;
    }

    public void setTopicIndexPublisher(String topicIndexPublisher) {
        this.topicIndexPublisher = topicIndexPublisher;
    }

    public String getTopicIndexPhoto() {
        return topicIndexPhoto;
    }

    public void setTopicIndexPhoto(String topicIndexPhoto) {
        this.topicIndexPhoto = topicIndexPhoto;
    }

    public Integer getNumTotal() {
        return numTotal;
    }

    public void setNumTotal(Integer numTotal) {
        this.numTotal = numTotal;
    }

    @Override
    public String toString() {
        return "DynaTopicDTO{" + "dynaTopicId=" + dynaTopicId + ", topicTitle='" + topicTitle + '\'' + ", topicType="
                    + topicType + ", backgroundColor='" + backgroundColor + '\'' + ", publishStatus=" + publishStatus
                    + ", publishTime=" + publishTime + ", indexNo=" + indexNo + ", isDel=" + isDel + ", businessId="
                    + businessId + ", createTime=" + createTime + ", createBy=" + createBy + ", modifyTime="
                    + modifyTime + ", modifyBy=" + modifyBy + ", dynaTopicContent='" + dynaTopicContent + '\''
                    + ", numView=" + numView + ", numComment=" + numComment + ", numReply=" + numReply
                    + ", publishTimeStart=" + publishTimeStart + ", publishTimeEnd=" + publishTimeEnd + ", commentList="
                    + commentList + ", photoList=" + photoList + '}';
    }
}
