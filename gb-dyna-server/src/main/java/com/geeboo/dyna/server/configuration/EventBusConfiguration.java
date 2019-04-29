package com.geeboo.dyna.server.configuration;

import com.geeboo.dyna.server.eventbus.center.squire.SquireEventBusCenter;
import com.geeboo.dyna.server.eventbus.observer.squire.SquireObserver;
import com.google.common.eventbus.AsyncEventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Title: <br>
 * Description: Copyright: Copyright (c) 2018
 *
 * @author 郭明毅 guomy 创建时间:2018/10/9 10:17
 */
@Configuration
public class EventBusConfiguration {
    @Bean
    SquireObserver squireObserver() {
        return new SquireObserver();
    }

    @Bean
    AsyncEventBus squireEventBus(@Autowired SquireObserver squireObserver) {
        AsyncEventBus eventBus = SquireEventBusCenter.getInstance();
        eventBus.register(squireObserver);
        return eventBus;
    }
}
