package com.flying.demo.strategy;

import org.springframework.core.Ordered;

/**
 * @Author songfeifei
 * @Date 2022/11/8 20:29
 * @Description 订单流程
 */
public interface IOrderProcess extends Ordered {

    /*接口中定义默认方法，可不实现，供子类重写*/

    /**
     * 生成订单策略
     * @return
     */
    default String onSaveSuccess() {
        return "default : success";
    }

    /**
     * 取消策略
     * @return
     */
    default String onCancel() {
        return "default : cancel";
    }

}
