package com.flying.demo.lock;

/**
 * @Author songfeifei
 * @Date 2024/2/28 9:54
 * @Description 自定义不可重入锁
 */
public class MyNotReentrantLock {

    private boolean isLocked = false;

    public synchronized void lock() throws InterruptedException {
        while (isLocked) {
            wait();
        }
        // 线程第一次进入后将isLocked置为true，第二次进入便会死循环
        isLocked = true;
    }

    public synchronized void unlock() {
        // 释放锁
        isLocked = false;
        // 结束阻塞
        notify();
    }
}
