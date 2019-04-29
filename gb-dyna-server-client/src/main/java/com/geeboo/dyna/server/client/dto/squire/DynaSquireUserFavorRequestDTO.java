package com.geeboo.dyna.server.client.dto.squire;

import java.util.Set;

/**
 * Title: <br>
 * Description: Copyright: Copyright (c) 2018
 *
 * @author 郭明毅 guomy 创建时间:2018/10/10 16:24
 */
public class DynaSquireUserFavorRequestDTO implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private Set<Integer> squireIdSet;
    private Integer userId;

    public DynaSquireUserFavorRequestDTO(Set<Integer> squireIdSet, Integer userId) {
        this.squireIdSet = squireIdSet;
        this.userId = userId;
    }

    public DynaSquireUserFavorRequestDTO() {
    }

    public Set<Integer> getSquireIdSet() {
        return squireIdSet;
    }

    public void setSquireIdSet(Set<Integer> squireIdSet) {
        this.squireIdSet = squireIdSet;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "DynaSquireUserFavorRequestDTO{" + "squireIdSet=" + squireIdSet + ", userId=" + userId + '}';
    }
}
