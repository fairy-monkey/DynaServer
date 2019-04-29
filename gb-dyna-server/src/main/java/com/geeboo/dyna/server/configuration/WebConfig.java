package com.geeboo.dyna.server.configuration;

import com.geeboo.auth.client.interceptor.ServiceAuthRestInterceptor;
import com.geeboo.auth.client.interceptor.UserAuthRestInterceptor;
import com.geeboo.dyna.server.intercepter.GlobalExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by ace on 2017/9/8.
 */
@Configuration("adminWebConfig")
@Primary
public class WebConfig extends WebMvcConfigurerAdapter {
    @Bean
    GlobalExceptionHandler getGlobalExceptionHandler() {
        return new GlobalExceptionHandler();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        ArrayList<String> commonPathPatterns = getExcludeCommonPathPatterns();
        registry.addInterceptor(getServiceAuthRestInterceptor()).addPathPatterns("/**").excludePathPatterns(commonPathPatterns.toArray(new String[] {}));
        //  this.addUserAuthExcludePatterns(commonPathPatterns);
        registry.addInterceptor(getUserAuthRestInterceptor()).addPathPatterns("/**").excludePathPatterns(commonPathPatterns.toArray(new String[] {}));
        super.addInterceptors(registry);
    }

    @Bean
    ServiceAuthRestInterceptor getServiceAuthRestInterceptor() {
        return new ServiceAuthRestInterceptor();
    }

    @Bean
    UserAuthRestInterceptor getUserAuthRestInterceptor() {
        return new UserAuthRestInterceptor();
    }

    private ArrayList<String> getExcludeCommonPathPatterns() {
        ArrayList<String> list = new ArrayList<>();
        String[] urls = {"/error/**", "/v2/api-docs", "/swagger-resources/**", "/cache/**", "/api/**", "/menu/**"};
        Collections.addAll(list, urls);
        return list;
    }
   /*
    private void addUserAuthExcludePatterns(ArrayList<String> commonPathPatterns) {
       // commonPathPatterns.add("/facade/**");
    }
    */

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/META-INF/version/");
        super.addResourceHandlers(registry);
    }
}
