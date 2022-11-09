package com.flying.demo.strategy;

import org.springframework.stereotype.Component;

/**
 * @Author songfeifei
 * @Date 2022/11/8 20:34
 * @Description
 */
@Component
public class RetailOrange extends AbstractRetailOrder {

    public int getOrder() {
        return -1;
    }

    @Override
    public String method1() {
        return "orange:method1";
    }

    @Override
    public String method2() {
        return "orange:method2";
    }

}
