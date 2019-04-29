package com.geeboo.dyna.server.client.dto.topic;

import java.io.Serializable;
import java.math.BigInteger;

public class DynaTopicComplaintDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    protected Integer dynaTopicComplaintId;
    private Integer dynaTopicId;
    private String complaintContent;
    private Integer complaintType;
    private Integer userId;
    private Integer toUserId;
    private Integer complaintStatus;
    private Integer contentId;
    private Integer complaintObj;
    private BigInteger createTime;
    private Integer createBy;
    private BigInteger modifyTime;
    private Integer modifyBy;

    public Integer getDynaTopicComplaintId() {
        return dynaTopicComplaintId;
    }

    public void setDynaTopicComplaintId(Integer dynaTopicComplaintId) {
        this.dynaTopicComplaintId = dynaTopicComplaintId;
    }

    /**
     * 返回 dyna_topic_id,话题id
     */
    public Integer getDynaTopicId() {
        return dynaTopicId;
    }

    /**
     * 参数 dynaTopicId
     */
    public void setDynaTopicId(Integer dynaTopicId) {
        this.dynaTopicId = dynaTopicId;
    }

    /**
     * 返回 complaint_content,举报内容
     */
    public String getComplaintContent() {
        return complaintContent;
    }

    /**
     * 参数 complaintContent
     */
    public void setComplaintContent(String complaintContent) {
        this.complaintContent = complaintContent;
    }

    /**
     * 返回 complaint_type,举报类型(字典:1,色情相关;2,不友善行为;3,广告推销;4,其他, 默认为4 )
     */
    public Integer getComplaintType() {
        return complaintType;
    }

    /**
     * 参数 complaintType
     */
    public void setComplaintType(Integer complaintType) {
        this.complaintType = complaintType;
    }

    /**
     * 返回 user_id,举报人id
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
     * 返回 to_user_id,被举报人id
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
     * 返回 complaint_status,举报状态(字典:0待处理;1已驳回,2已隐藏.默认为0)
     */
    public Integer getComplaintStatus() {
        return complaintStatus;
    }

    /**
     * 参数 complaintStatus
     */
    public void setComplaintStatus(Integer complaintStatus) {
        this.complaintStatus = complaintStatus;
    }

    /**
     * 返回 content_id,评论或回复id
     */
    public Integer getContentId() {
        return contentId;
    }

    /**
     * 参数 contentId
     */
    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    /**
     * 返回 complaint_obj,举报对象(字典:1评论,2回复,默认为1)
     */
    public Integer getComplaintObj() {
        return complaintObj;
    }

    /**
     * 参数 complaintObj
     */
    public void setComplaintObj(Integer complaintObj) {
        this.complaintObj = complaintObj;
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
        return "DynaTopicComplaintDTO{" + "dynaTopicComplaintId=" + dynaTopicComplaintId + ", dynaTopicId="
            + dynaTopicId + ", complaintContent='" + complaintContent + '\'' + ", complaintType=" + complaintType
            + ", userId=" + userId + ", toUserId=" + toUserId + ", complaintStatus=" + complaintStatus + ", contentId="
            + contentId + ", complaintObj=" + complaintObj + ", createTime=" + createTime + ", createBy=" + createBy
            + ", modifyTime=" + modifyTime + ", modifyBy=" + modifyBy + '}';
    }
}


