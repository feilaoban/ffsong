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

    /**
     * 1.此处<T>相当于修饰符，表示这是一个泛型方法
     * 2.T表示方法的入参是T类型
     * 3.注意<T>不是返回值，此处的返回值是void
     * @param <T>
     */
    default <T> void test(T t) {}

}
