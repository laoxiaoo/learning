package com.xiao.redis.sub.cache;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

/**
 * @author xiao jie
 * @date 2023-10-02 10:41
 */
@Setter
@Getter
public class RedisSubApplicationEvent extends ApplicationContextEvent {


    private String command;

    /**
     * Create a new ContextStartedEvent.
     *
     * @param source the {@code ApplicationContext} that the event is raised for
     *               (must not be {@code null})
     */
    public RedisSubApplicationEvent(ApplicationContext source) {
        super(source);
    }
}
