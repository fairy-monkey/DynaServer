package com.geeboo.dyna.server.client.dto.squire;

/**
 * Title: <br>
 * Description: Copyright: Copyright (c) 2018
 *
 * @author 郭明毅 guomy 创建时间:2018/10/9 16:57
 */
public class DynaSquireFavorRequestDTO implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    protected Integer squireFavorId;
    private Integer dynaSquireId;
    private Integer userId;
    /**
     * 1-点赞；0-取消
     */
    private Integer operateType;

    public Integer getSquireFavorId() {
        return squireFavorId;
    }

    public void setSquireFavorId(Integer squireFavorId) {
        this.squireFavorId = squireFavorId;
    }

    public Integer getDynaSquireId() {
        return dynaSquireId;
    }

    public void setDynaSquireId(Integer dynaSquireId) {
        this.dynaSquireId = dynaSquireId;
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
        return "DynaSquireFavorRequestDTO{" + "squireFavorId=" + squireFavorId + ", dynaSquireId=" + dynaSquireId
            + ", userId=" + userId + ", operateType=" + operateType + '}';
    }
}
