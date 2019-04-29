package com.geeboo.dyna.server.configuration;

import com.geeboo.auth.client.interceptor.ServiceFeignInterceptor;
import feign.RequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Title: <br>
 * Description: Copyright: Copyright (c) 2018
 *
 * @author 郭明毅 guomy 创建时间:2018/6/22 19:08
 */
@Slf4j
@Configuration
public class ServiceFeignConfiguration {
    @Bean
    public RequestInterceptor getRequestInterceptor() {
        return new ServiceFeignInterceptor();
    }
}
