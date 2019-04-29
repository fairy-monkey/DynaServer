package com.geeboo.dyna.server.client.dto.topic;

import java.io.Serializable;
import java.math.BigInteger;

public class DynaTopicContentDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    protected Integer dynaTopicContentId;
    private Integer dynaTopicId;
    private String dynaTopicContent;
    private BigInteger createTime;
    private Integer createBy;
    private BigInteger modifyTime;
    private Integer modifyBy;

    public Integer getDynaTopicContentId() {
        return dynaTopicContentId;
    }

    public void setDynaTopicContentId(Integer dynaTopicContentId) {
        this.dynaTopicContentId = dynaTopicContentId;
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
     * 返回 dyna_topic_content,话题内容
     */
    public String getDynaTopicContent() {
        return dynaTopicContent;
    }

    /**
     * 参数 dynaTopicContent
     */
    public void setDynaTopicContent(String dynaTopicContent) {
        this.dynaTopicContent = dynaTopicContent;
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
        StringBuffer toString = new StringBuffer();
        toString.append("DynaTopicContentDTO{");
        toString.append("dynaTopicId=");
        toString.append(dynaTopicId);
        toString.append(",dynaTopicContent=");
        toString.append(dynaTopicContent);
        toString.append(",createTime=");
        toString.append(createTime);
        toString.append(",createBy=");
        toString.append(createBy);
        toString.append(",modifyTime=");
        toString.append(modifyTime);
        toString.append(",modifyBy=");
        toString.append(modifyBy);
        toString.append("}");
        return toString.toString();
    }
}


