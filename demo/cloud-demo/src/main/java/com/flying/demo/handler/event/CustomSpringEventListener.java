package com.flying.demo.handler.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @Author songfeifei
 * @Date 2023/5/12 15:11
 * @Description 事件监听器，观察者模式解耦的一种实现
 */
@Component
public class CustomSpringEventListener implements ApplicationListener<CustomSpringEvent> {

    @Override
    public void onApplicationEvent(CustomSpringEvent event) {
        System.out.println("Received spring custom event - " + event.getMessage());
    }
}
