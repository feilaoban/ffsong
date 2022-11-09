package com.flying.demo;

import com.flying.demo.strategy.IRetailOrder;
import com.flying.demo.strategy.handler.RetailOrderHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    RetailOrderHandler retailOrderHandler;

    @Test
    void contextLoads() {
    }

    @Test
    void RetailOrderTest() {
        List<IRetailOrder> retailOrderList = retailOrderHandler.getRetailOrderList();
        for (IRetailOrder retailOrder : retailOrderList) {
            System.out.println(retailOrder.method1());
            System.out.println(retailOrder.method2());
            System.out.println("order = " + retailOrder.getOrder());
        }
        /*  orange:method1
            orange:method2
            order = -1
            apple:method1
            apple:method2
            order = 1
            banana:method1
            banana:method2
            order = 2       */
    }
}
