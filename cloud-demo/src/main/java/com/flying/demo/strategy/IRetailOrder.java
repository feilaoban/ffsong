package com.flying.demo.strategy;

import org.springframework.core.Ordered;

/**
 * @Author songfeifei
 * @Date 2022/11/8 20:29
 * @Description
 */
public interface IRetailOrder extends Ordered {

    // 接口中定义默认方法，可不实现，供子类重写
    default String method1() {
        return "default:method1";
    }

    default String method2() {
        return "default:method2";
    }

}
