package com.flying.demo.proxy;

/**
 * @Author songfeifei
 * @Date 2023/12/13 10:55
 * @Description 定义一个计算的接口，代理类和被代理类需要实现相同的接口。
 */
public interface Calculator {

    void add(int a, int b);

    void subtract(int a, int b);

}
