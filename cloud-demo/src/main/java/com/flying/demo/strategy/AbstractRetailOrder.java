package com.flying.demo.strategy;

/**
 * @Author songfeifei
 * @Date 2022/11/8 20:31
 * @Description
 */
public abstract class AbstractRetailOrder implements IRetailOrder {

    // 只需要在抽象类中实现一次 Ordered，不用在每个子类中多次实现
    @Override
    public int getOrder() {
        return 0;
    }
}
