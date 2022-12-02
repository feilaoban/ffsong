package com.flying.demo;

import java.util.concurrent.*;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

/**
 * @Author songfeifei
 * @Date 2021/8/16 16:55
 * @Description
 */
public class CompletableFutureTests {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //testSupplyAsync();
        //testFutureThread();
        //testWhenComplete();
        testWhenCompleteAsync();
    }

    private static void testSupplyAsync() {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("future1 finished!");
            return "future1 finished!";
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("future2 finished!");
            return "future2 finished!";
        });
        CompletableFuture<Void> combineFuture = CompletableFuture.allOf(future1, future2);
//        combineFuture.join();
        try {
            combineFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("future1: " + future1.isDone() + " future2: " + future2.isDone());
    }

    /**
     * 测试CompletableFuture的默认线程
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void testFutureThread() throws ExecutionException, InterruptedException {
        Supplier<String> supplier = () -> {
          return Thread.currentThread().getName();
        };
        // 不指定线程池，使用默认线程池
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(supplier);
        System.out.println("future1 thread : " + future1.get());
        // 使用自定义线程池
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 10, 10, TimeUnit.MINUTES, new LinkedBlockingQueue<>());
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(supplier, threadPool);
        System.out.println("future2 thread : " + future2.get());

        Thread.sleep(1000);
        threadPool.shutdown();
    }

    /**
     * whenComplete()测试
     * 注意在任务结束前后，后续行为使用的是哪个线程
     * @throws InterruptedException
     */
    private static void testWhenComplete() throws InterruptedException {
        System.out.println("main thread ：" + Thread.currentThread().getName());
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 10, 10, TimeUnit.MINUTES, new LinkedBlockingQueue<>());
        // 一个3秒的任务
        Supplier<String> supplier = () -> {
            try {
                Thread.sleep(3000);
                System.out.println("supplier thread : " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "";
        };
        // 任务完成后的行为
        BiConsumer<Object, Throwable> action = (result, exception) -> {
            System.out.println("action thread : " + Thread.currentThread().getName());
        };

        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(supplier, threadPool);
        // 睡1秒，此时任务还结束，调用whenComplete()方法，将会和supplier的执行使用相同的线程
        Thread.sleep(1000);
        CompletableFuture<String> future2 = future1.whenComplete(action);
        // 睡5秒，此时任务结束，调用whenComplete()方法，只能使用本方法的线程
        Thread.sleep(5000);
        CompletableFuture<String> future3 = future1.whenComplete(action);
    }

    /**
     * whenCompleteAsync()测试
     * 任务结束后，从线程池中取一个线程或新建线程执行后续行为
     * @throws InterruptedException
     */
    private static void testWhenCompleteAsync() throws InterruptedException {
        System.out.println("main thread ：" + Thread.currentThread().getName());
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 10, 10, TimeUnit.MINUTES, new LinkedBlockingQueue<>());
        // 一个3秒的任务
        Supplier<String> supplier = () -> {
            try {
                Thread.sleep(3000);
                System.out.println("supplier thread : " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "";
        };
        // 任务完成后的行为
        BiConsumer<Object, Throwable> action = (result, exception) -> {
            System.out.println("action thread : " + Thread.currentThread().getName());
        };

        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(supplier, threadPool);
        // 睡1秒，使用默认线程池
        Thread.sleep(1000);
        CompletableFuture<String> future2 = future1.whenCompleteAsync(action);
        // 睡5秒，使用指定的线程池
        Thread.sleep(5000);
        CompletableFuture<String> future3 = future1.whenCompleteAsync(action, threadPool);
    }
}
