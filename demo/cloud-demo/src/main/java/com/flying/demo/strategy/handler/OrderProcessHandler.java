package com.flying.demo.strategy.handler;

import com.flying.demo.strategy.IOrderProcess;
import com.google.common.collect.Lists;
import lombok.Getter;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @Author songfeifei
 * @Date 2022/11/8 20:49
 * @Description 策略模式的一种用法
 * <p>
 * 当一个类实现了这个接口之后，这个类就可以方便的获得ApplicationContext对象（spring上下文），
 * Spring发现某个Bean实现了ApplicationContextAware接口，Spring容器会在创建该Bean之后，
 * 自动调用该Bean的setApplicationContext（参数）方法，调用该方法时，会将容器本身ApplicationContext对象作为参数传递给该方法。
 * </p>
 */
@Component
public class OrderProcessHandler implements ApplicationContextAware {

    @Getter
    private volatile List<IOrderProcess> orderProcessList;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        orderProcessList = Collections.synchronizedList(Lists.newArrayList());
        String[] orderProcessNames = applicationContext.getBeanNamesForType(IOrderProcess.class);
        for (String orderProcessName : orderProcessNames) {
            IOrderProcess orderProcess = applicationContext.getBean(orderProcessName, IOrderProcess.class);
            orderProcessList.add(orderProcess);
        }
        // 对同一接口多个实现类的执行顺序排序，实现类不重写getOrder()的话，按加载顺序执行
        orderProcessList.sort(Comparator.comparing(IOrderProcess::getOrder));
    }

    /**
     * 生成订单流程处理
     */
    public void doOnSaveSuccessProcess() {
        for (IOrderProcess orderProcess : orderProcessList) {
            System.out.println(orderProcess.onSaveSuccess());
            System.out.println("order = " + orderProcess.getOrder());
        }
    }

    /**
     * 取消订单流程处理
     */
    public void doOnCancelProcess() {
        for (IOrderProcess orderProcess : orderProcessList) {
            System.out.println(orderProcess.onCancel());
            System.out.println("order = " + orderProcess.getOrder());
        }
    }
}
