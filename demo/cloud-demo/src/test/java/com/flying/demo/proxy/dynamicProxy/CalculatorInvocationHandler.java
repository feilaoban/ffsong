package com.flying.demo.proxy.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author songfeifei
 * @Date 2023/12/13 13:29
 * @Description 动态代理
 */
public class CalculatorInvocationHandler implements InvocationHandler {

    private final Object target;

    CalculatorInvocationHandler(Object target) {
        this.target = target;
    }

    /**
     * 当代理对象的方法被调用时，invoke方法将会被执行
     *
     * @param proxy  代理对象的引用，可以用来调用被代理的方法
     * @param method 被调用的方法对象，通过该对象可以获取被调用方法的相关信息，如方法名、参数类型等
     * @param args   被调用方法的参数数组
     * @return 被代理方法的返回值
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before " + method.getName() + " operation");
        Object result = method.invoke(target, args);
        System.out.println("after " + method.getName() + " operation");
        return result;
    }
}
