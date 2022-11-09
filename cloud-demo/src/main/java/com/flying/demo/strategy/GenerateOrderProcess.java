package com.flying.demo.strategy;

import org.springframework.stereotype.Component;

/**
 * @Author songfeifei
 * @Date 2022/11/8 20:34
 * @Description
 */
@Component
public class GenerateOrderProcess extends AbstractOrderProcess {

    public int getOrder() {
        return 3;
    }

    @Override
    public String onSaveSuccess() {
        return "GenerateOrder - 生成订单";
    }

    @Override
    public String onCancel() {
        return "GenerateOrder - 取消订单";
    }
}
