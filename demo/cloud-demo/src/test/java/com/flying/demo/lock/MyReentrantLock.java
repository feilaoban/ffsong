package com.flying.demo.lock;

/**
 * @Author songfeifei
 * @Date 2024/2/28 9:54
 * @Description 自定义可重入锁（同一线程可重复获取某一个锁）
 */
public class MyReentrantLock {

    private boolean isLocked = false;
    private Thread lockedBy = null;
    private int lockedCount = 0;

    public synchronized void lock() throws InterruptedException {
        Thread thread = Thread.currentThread();
        // 上锁了 且 当前线程不是获得锁的线程则阻塞；同一线程放行，标识可重入
        while (isLocked && lockedBy != thread) {
            wait();
        }
        // 第一次进入就上锁
        isLocked = true;
        // 当前获得锁的线程
        lockedBy = thread;
        // 上锁次数计数
        lockedCount++;
    }

    public synchronized void unlock() {
        if (lockedBy == Thread.currentThread()) {
            // 上锁次数减一
            lockedCount--;
            if (lockedCount == 0 ) {
                // 线程获得的所有锁都释放完毕，才算真正的释放锁，其他线程才能获取锁
                isLocked = false;
                notify();
            }
        }
    }

}
