package com.flying.demo.proxy.dynamicProxy;

import com.flying.demo.proxy.Calculator;
import com.flying.demo.proxy.RealCalculator;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Author songfeifei
 * @Date 2023/12/13 14:14
 * @Description 动态代理：方案二，使用CGLib
 * CGLib是一个强大的、高性能的代码生成库。其被广泛应用于AOP框架（Spring、dynaop）中，代理对象会拦截目标方法的调用，并在调用前后执行额外的操作，比如打印日志、执行额外的逻辑等。
 * CGLib代理主要通过对字节码的操作，为对象引入间接级别，以控制对象的访问
 */
public class ProxyByCGlibTest {

    public static void main(String[] args) {
        RealCalculator realCalculator = new RealCalculator();
        // 创建 Enhancer 对象，用于生成代理类的实例
        Enhancer enhancer = new Enhancer();
        // 指定要代理的目标类，并将其设置为代理类的父类
        enhancer.setSuperclass(realCalculator.getClass());
        // 设置代理对象的回调方法，代理对象调用方法时执行额外的操作
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                System.out.println("before " + method.getName() + " operation");
                Object result = methodProxy.invokeSuper(obj, args);
                System.out.println("after " + method.getName() + " operation");
                return result;
            }
        });
        Calculator proxyCalculator = (Calculator) enhancer.create();
        proxyCalculator.add(1, 2);
        proxyCalculator.subtract(2, 1);
    }
}
