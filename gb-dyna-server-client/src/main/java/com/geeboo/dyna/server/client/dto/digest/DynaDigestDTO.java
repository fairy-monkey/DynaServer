package com.geeboo.dyna.server.client.dto.digest;

import java.io.Serializable;
import java.math.BigInteger;

public class DynaDigestDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    protected Integer dynaDigestId;
    private Integer bookUserId;
    private String digestContent;
    private Integer bookPropId;
    private String imagePath;
    private Integer publishStatus;
    private Integer publishDate;
    private Integer numSupport;
    private BigInteger createTime;
    private Integer createBy;
    private BigInteger modifyTime;
    private Integer modifyBy;

    private String bookName;
    private String wholeImagePath;

    public Integer getDynaDigestId() {
        return dynaDigestId;
    }

    public void setDynaDigestId(Integer dynaDigestId) {
        this.dynaDigestId = dynaDigestId;
    }

    /**
     * 返回 book_user_id,图书id
     */
    public Integer getBookUserId() {
        return bookUserId;
    }

    /**
     * 参数 bookUserId
     */
    public void setBookUserId(Integer bookUserId) {
        this.bookUserId = bookUserId;
    }

    /**
     * 返回 digest_content,书摘内容
     */
    public String getDigestContent() {
        return digestContent;
    }

    /**
     * 参数 digestContent
     */
    public void setDigestContent(String digestContent) {
        this.digestContent = digestContent;
    }

    /**
     * 返回 book_prop_id,图书属性(1:上传书 2：购买书)
     */
    public Integer getBookPropId() {
        return bookPropId;
    }

    /**
     * 参数 bookPropId
     */
    public void setBookPropId(Integer bookPropId) {
        this.bookPropId = bookPropId;
    }

    /**
     * 返回 image_path,分享图书
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * 参数 imagePath
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
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

    /**
     * 返回 publish_date,发布日期
     */
    public Integer getPublishDate() {
        return publishDate;
    }

    /**
     * 参数 publishDate
     */
    public void setPublishDate(Integer publishDate) {
        this.publishDate = publishDate;
    }

    /**
     * 返回 num_support,点赞数量
     */
    public Integer getNumSupport() {
        return numSupport;
    }

    /**
     * 参数 numSupport
     */
    public void setNumSupport(Integer numSupport) {
        this.numSupport = numSupport;
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

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getWholeImagePath() {
        return wholeImagePath;
    }

    public void setWholeImagePath(String wholeImagePath) {
        this.wholeImagePath = wholeImagePath;
    }

    @Override
    public String toString() {
        StringBuffer toString = new StringBuffer();
        toString.append("DynaDigestDTO{");
        toString.append("bookUserId=");
        toString.append(bookUserId);
        toString.append(",digestContent=");
        toString.append(digestContent);
        toString.append(",bookPropId=");
        toString.append(bookPropId);
        toString.append(",imagePath=");
        toString.append(imagePath);
        toString.append(",publishStatus=");
        toString.append(publishStatus);
        toString.append(",publishDate=");
        toString.append(publishDate);
        toString.append(",numSupport=");
        toString.append(numSupport);
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
