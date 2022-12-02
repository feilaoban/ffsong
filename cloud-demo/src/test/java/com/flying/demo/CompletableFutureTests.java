package com.flying.demo;

import java.util.concurrent.*;
import java.util.function.Supplier;

/**
 * @Author songfeifei
 * @Date 2021/8/16 16:55
 * @Description
 */
public class CompletableFutureTests {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //testSupplyAsync();
        testFutureThread();
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
}
