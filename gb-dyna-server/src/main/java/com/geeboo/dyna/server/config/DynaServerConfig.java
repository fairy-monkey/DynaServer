package com.geeboo.dyna.server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Title: <br>
 * Description: Copyright: Copyright (c) 2018
 *
 * @author 郭明毅 guomy 创建时间:2018/9/21 9:53
 */
@Configuration
public class DynaServerConfig {
    @Value("${dyna.server.book-idiot-id}")
    private Integer bookIdiotId;

    @Value("${dyna.server.dyna-special-id}")
    private Integer dynaSpecialId;

    /**
     * 书大痴ID
     *
     * @return
     */
    public Integer getBookIdiotId() {
        return bookIdiotId;
    }

    public void setBookIdiotId(Integer bookIdiotId) {
        this.bookIdiotId = bookIdiotId;
    }

    /**
     * 动态特殊帐号ID，暂时为空
     *
     * @return
     */
    public Integer getDynaSpecialId() {
        return dynaSpecialId;
    }

    public void setDynaSpecialId(Integer dynaSpecialId) {
        this.dynaSpecialId = dynaSpecialId;
    }
}
