package com.flying.demo.handler.event;

import org.springframework.context.ApplicationEvent;

/**
 * @Author songfeifei
 * @Date 2023/5/12 15:03
 * @Description 事件实体类
 */
public class CustomSpringEvent extends ApplicationEvent {

    private String message;

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    CustomSpringEvent(Object source, String message) {
        super(source);
        this.message = message;
    }


    String getMessage() {
        return message;
    }
}
