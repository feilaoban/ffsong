package com.flying.demo;

import com.flying.demo.handler.event.CustomSpringEventPublisher;
import com.flying.demo.strategy.AbstractOrderProcess;
import com.flying.demo.strategy.CheckStoreProcess;
import com.flying.demo.strategy.GenerateOrderProcess;
import com.flying.demo.strategy.ReduceStoreProcess;
import com.flying.demo.strategy.handler.OrderProcessHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@SpringBootTest
@ActiveProfiles(profiles = "local")
class DemoApplicationTests {

    @Autowired
    OrderProcessHandler orderProcessHandler;
    @Autowired
    CustomSpringEventPublisher customSpringEventPublisher;

    @Test
    void contextLoads() {
    }

    @Test
    void OrderProcessSuccessTest() {
        orderProcessHandler.doOnSaveSuccessProcess();
        /*  CheckStore - 校验库存
            order = 1
            ReduceStore - 减库存
            order = 2
            GenerateOrder - 生成订单
            order = 3      */
    }

    @Test
    void OrderProcessCancelTest() {
        orderProcessHandler.doOnCancelProcess();
        /*  default : cancel
            order = 1
            cancel - 加库存
            order = 2
            GenerateOrder - 取消订单
            order = 3      */
    }

    @Test
    void OrderProcessTest1() {
        Map<String, AbstractOrderProcess> map = new HashMap<>();
        map.put("1", new CheckStoreProcess());
        map.put("2", new GenerateOrderProcess());
        map.put("3", new ReduceStoreProcess());

        String key = "1";
        // 可通过给每种策略预定义编码的方式，通过编码调用对应的方法，从而消除 if-else
        AbstractOrderProcess process = map.get(key);
        if (Objects.isNull(process)) {
            throw new RuntimeException("策略不符合");
        }
        System.out.println(process.onSaveSuccess());  // CheckStore - 校验库存
    }

    @Test
    void springEventTest() {
        customSpringEventPublisher.publishCustomEvent("我发布了事件");
    }
}
