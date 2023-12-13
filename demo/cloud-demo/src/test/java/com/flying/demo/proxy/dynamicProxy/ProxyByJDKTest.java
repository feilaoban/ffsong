package com.flying.demo.proxy.dynamicProxy;

import com.flying.demo.proxy.Calculator;
import com.flying.demo.proxy.RealCalculator;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @Author songfeifei
 * @Date 2023/12/13 11:06
 * @Description 动态代理：方案一，基于Java原生的java.lang.reflect.Proxy类和java.lang.reflect.InvocationHandler接口实现动态代理。
 */
public class ProxyByJDKTest {

    public static void main(String[] args) {
        RealCalculator realCalculator = new RealCalculator();
        InvocationHandler invocationHandler = new CalculatorInvocationHandler(realCalculator);

        // 根据被代理类的信息动态生成代理对象 proxyCalculator
        Calculator proxyCalculator = (Calculator) Proxy.newProxyInstance(
                realCalculator.getClass().getClassLoader(), // ClassLoader对象，用于加载代理类
                realCalculator.getClass().getInterfaces(),  // Class对象数组，表示要为哪些接口创建代理对象
                invocationHandler);                         // InvocationHandler对象，用于处理代理对象的方法调用

        // 当调用以下方法时，代理对象会将该方法调用转发给CalculatorInvocationHandler的invoke方法进行处理
        proxyCalculator.add(1, 2);
        proxyCalculator.subtract(2, 1);
    }
}
