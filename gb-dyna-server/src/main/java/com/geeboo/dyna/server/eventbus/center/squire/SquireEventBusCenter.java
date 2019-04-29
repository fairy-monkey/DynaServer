package com.geeboo.dyna.server.eventbus.center.squire;

import com.geeboo.dyna.server.eventbus.dto.squire.SquireEventDTO;
import com.google.common.eventbus.AsyncEventBus;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Title: <br>
 * Description: Copyright: Copyright (c) 2018
 *
 * @author 郭明毅 guomy 创建时间:2018/10/8 15:26
 */
public class SquireEventBusCenter {
    private static AsyncEventBus eventBus;

    static {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(100);
        executor.setQueueCapacity(1000);
        executor.setThreadNamePrefix("async-squire-event-bus-");
        executor.initialize();
        eventBus = new AsyncEventBus("squireEventBus", executor);
    }

    private SquireEventBusCenter() {
    }

    public static AsyncEventBus getInstance() {
        return eventBus;
    }

    public static void register(Object obj) {
        eventBus.register(obj);
    }

    public static void unregister(Object obj) {
        eventBus.unregister(obj);
    }

    public static void post(SquireEventDTO dto) {
        eventBus.post(dto);
    }
}
