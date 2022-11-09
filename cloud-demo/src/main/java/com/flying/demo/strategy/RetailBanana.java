package com.flying.demo.strategy;

import org.springframework.stereotype.Component;

/**
 * @Author songfeifei
 * @Date 2022/11/8 20:34
 * @Description
 */
@Component
public class RetailBanana extends AbstractRetailOrder {

    public int getOrder() {
        return 2;
    }

    @Override
    public String method1() {
        return "banana:method1";
    }

    @Override
    public String method2() {
        return "banana:method2";
    }

}
