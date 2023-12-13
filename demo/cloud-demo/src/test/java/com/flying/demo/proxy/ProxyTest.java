package com.flying.demo.proxy;

/**
 * @Author songfeifei
 * @Date 2023/12/13 11:06
 * @Description
 */
public class ProxyTest {

    public static void main(String[] args) {
        RealCalculator realCalculator = new RealCalculator();
        // 把被代理类的对象交由代理类管理
        ProxyCalculator proxyCalculator = new ProxyCalculator(realCalculator);
        proxyCalculator.add(1, 2);
        proxyCalculator.subtract(2, 1);
    }
}
