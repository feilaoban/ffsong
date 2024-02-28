package com.flying.demo.lock;

/**
 * @Author songfeifei
 * @Date 2024/2/28 9:56
 * @Description
 */
public class LockTest {

    private MyNotReentrantLock notReentrantLock = new MyNotReentrantLock();
    private MyReentrantLock reentrantLock = new MyReentrantLock();

    private void doJob() throws InterruptedException {
        // 第一次上锁
        notReentrantLock.lock();
        System.out.println("执行doJob方法");
        // 方法内会再次上锁
        doJobInner();
        // 释放第一次上的锁
        notReentrantLock.unlock();
    }

    private void doJobInner() throws InterruptedException {
        // 第二次上锁，进入死循环一直等待，现象为一直获取不到锁，因此不会继续往下执行
        notReentrantLock.lock();
        System.out.println("执行doJobInner方法");
    }

    private void doJobRL() throws InterruptedException {
        // 第一次上锁
        reentrantLock.lock();
        System.out.println("执行doJobRL方法");
        // 方法内会再次上锁
        doJobInner();
        // 释放第一次上的锁
        reentrantLock.unlock();
    }

    private void doJobRLInner() throws InterruptedException {
        // 第二次上锁，同一线程可再次获取锁
        reentrantLock.lock();
        System.out.println("执行doJobRLInner方法");
    }


    public static void main(String[] args) throws InterruptedException {
        //new LockTest().doJob();
        new LockTest().doJobRL();
    }
}
