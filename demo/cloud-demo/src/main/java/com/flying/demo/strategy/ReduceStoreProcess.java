package com.flying.demo.strategy;

import org.springframework.stereotype.Component;

/**
 * @Author songfeifei
 * @Date 2022/11/8 20:34
 * @Description 减库存
 */
@Component
public class ReduceStoreProcess extends AbstractOrderProcess {

    public int getOrder() {
        return 2;
    }

    @Override
    public String onSaveSuccess() {
        return "ReduceStore - 减库存";
    }

    @Override
    public String onCancel() {
        return "cancel - 加库存";
    }
}
