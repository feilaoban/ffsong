package com.flying.demo.strategy;

import org.springframework.stereotype.Component;

/**
 * @Author songfeifei
 * @Date 2022/11/8 20:34
 * @Description 校验库存
 */
@Component
public class CheckStoreProcess extends AbstractOrderProcess {

    public int getOrder() {
        return 1;
    }

    @Override
    public String onSaveSuccess() {
        return "CheckStore - 校验库存";
    }

}
