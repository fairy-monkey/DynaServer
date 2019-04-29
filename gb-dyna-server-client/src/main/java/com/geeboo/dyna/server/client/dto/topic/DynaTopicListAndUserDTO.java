package com.geeboo.dyna.server.client.dto.topic;

import java.util.List;
import java.util.Set;

/**
 * Title: <br>
 * Description: Copyright: Copyright (c) 2018
 *
 * @author 郭明毅 guomy 创建时间:2018/9/13 14:53
 */
public class DynaTopicListAndUserDTO implements java.io.Serializable {
    private List<DynaTopicListDTO> list;
    private Set<Integer> accountIdSet;
    private Long total;
    private Integer pages;

    public DynaTopicListAndUserDTO() {
    }

    public DynaTopicListAndUserDTO(List<DynaTopicListDTO> list, Set<Integer> accountIdSet, Long total, Integer pages) {
        this.list = list;
        this.accountIdSet = accountIdSet;
        this.total = total;
        this.pages = pages;
    }

    public List<DynaTopicListDTO> getList() {
        return list;
    }

    public void setList(List<DynaTopicListDTO> list) {
        this.list = list;
    }

    public Set<Integer> getAccountIdSet() {
        return accountIdSet;
    }

    public void setAccountIdSet(Set<Integer> accountIdSet) {
        this.accountIdSet = accountIdSet;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "DynaTopicListAndUserDTO{" + "list=" + list + ", accountIdSet=" + accountIdSet + ", total=" + total
            + ", pages=" + pages + '}';
    }
}
