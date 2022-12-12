package com.flying.demo;

import java.util.Objects;

/**
 * @Author songfeifei
 * @Date 2022/12/12 14:37
 * @Description
 */
public class ThreadLocalTests {

    /* 【原理】：线程类Thread内部维护了一份局部变量，存放在ThreadLocal.ThreadLocalMap，是线程私有的 */

    //private static ThreadLocal<Integer> LOCAL_NUM = new ThreadLocal<>();
    // 创建ThreadLocal时便设置初始值
    private static ThreadLocal<Integer> LOCAL_NUM = ThreadLocal.withInitial(() -> 0);
    // 非线程安全变量
    private static Integer NUM = 0;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("初始值 = " + LOCAL_NUM.get());

        // 循环创建3个线程，操作 num
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(() -> {
                if (Objects.isNull(LOCAL_NUM.get())) {
                    // 使用时设置初始值
                    LOCAL_NUM.set(0);
                }
                int num = LOCAL_NUM.get();
                for (int j = 0; j < 1000; j++) {
                    num++;
                    NUM++;
                }
                // 3个线程num相互隔离，每个线程的num都自增1000次，输出结果都打印 num = 1000
                System.out.println("thread:" + Thread.currentThread().getName() + ", num = " + num);
            });
            thread.start();
        }
        Thread.sleep(3000);
        System.out.println("NUM = " + NUM); // 小于3000
    }
}
