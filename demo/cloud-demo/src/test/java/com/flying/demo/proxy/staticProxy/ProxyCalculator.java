package com.flying.demo.proxy.staticProxy;

import com.flying.demo.proxy.Calculator;

/**
 * @Author songfeifei
 * @Date 2023/12/13 10:57
 * @Description 代理类
 */
public class ProxyCalculator implements Calculator {

    /**
     * 静态代理：显示的定义一个代理类
     * 代理类持有一个被代理类的实例，用于执行被代理类的方法。
     * 代理对象与被代理对象具有相同的接口，因此可以在任何时候替换原始对象，并且可以通过代理对象来实现对原始对象的访问控制和管理。
     */
    private final Calculator realCalculator;

    ProxyCalculator(Calculator calculator) {
        this.realCalculator = calculator;
    }

    @Override
    public void add(int a, int b) {
        System.out.println("before add operation");
        realCalculator.add(a, b);
        System.out.println("after add operation");
    }

    @Override
    public void subtract(int a, int b) {
        System.out.println("before subtract operation");
        realCalculator.subtract(a, b);
        System.out.println("after subtract operation");
    }
}
