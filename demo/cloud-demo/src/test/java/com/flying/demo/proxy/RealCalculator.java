package com.flying.demo.proxy;

/**
 * @Author songfeifei
 * @Date 2023/12/13 10:57
 * @Description 被代理类
 */
public class RealCalculator implements Calculator {

    @Override
    public void add(int a, int b) {
        System.out.println(a + b);
    }

    @Override
    public void subtract(int a, int b) {
        System.out.println(a - b);
    }
}
