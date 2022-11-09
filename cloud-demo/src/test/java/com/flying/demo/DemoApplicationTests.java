package com.flying.demo;

import com.flying.demo.strategy.CheckStoreProcess;
import com.flying.demo.strategy.handler.OrderProcessHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    OrderProcessHandler orderProcessHandler;

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
        CheckStoreProcess checkStore = new CheckStoreProcess();
        System.out.println(checkStore.onSaveSuccess());  // CheckStore - 校验库存
    }
}
