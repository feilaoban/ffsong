package com.flying.demo;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @Author songfeifei
 * @Date 2021/8/16 16:55
 * @Description
 */
public class CompletableFutureTests {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //supplyAsync();
        //testFutureThread();
        //testRunAndSupplyAsync();
        //whenComplete();
        //whenCompleteAsync();
        //thenApply();
        //handle();
        //thenAccept();
        //thenRun();
        thenCombine();
    }

    private static void supplyAsync() throws ExecutionException, InterruptedException {
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
        combineFuture.get();
        System.out.println("future1: " + future1.isDone() + " future2: " + future2.isDone());
    }

    /**
     * 测试CompletableFuture的默认线程
     * 没有指定Executor的方法会使用ForkJoinPool.commonPool() 作为它的线程池执行异步代码;
     * 如果指定线程池，则使用指定的线程池运行。
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
     * runAsync() & supplyAsync()测试
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void testRunAndSupplyAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> {
            System.out.println("runAsync默认线程池");
        });
        future1.get();
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            return "supplyAsync默认线程池";
        });
        System.out.println(future2.get());
    }

    /**
     * whenComplete()测试
     * 注意在任务结束前后，后续行为使用的是哪个线程
     *
     * @throws InterruptedException
     */
    private static void whenComplete() throws InterruptedException {
        System.out.println("main thread ：" + Thread.currentThread().getName());
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 10, 10, TimeUnit.MINUTES, new LinkedBlockingQueue<>());
        // 一个3秒的任务
        Supplier<String> supplier = () -> {
            try {
                Thread.sleep(3000);
                System.out.println(System.currentTimeMillis() + " - supplier thread : " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "";
        };
        // 任务完成后的行为
        BiConsumer<Object, Throwable> action = (result, exception) -> {
            System.out.println(System.currentTimeMillis() + " - action thread : " + Thread.currentThread().getName());
        };

        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(supplier, threadPool);
        // 睡1秒，此时任务还未结束，任务结束后会立即调用whenComplete()方法，并且和supplier的执行使用相同的线程
        Thread.sleep(1000);
        CompletableFuture<String> future2 = future1.whenComplete(action);
        // 睡5秒，此时任务结束，调用whenComplete()方法，只能使用本方法的线程（从异步开始执行算起，加上之前睡1秒，任务执行要3秒，也就是任务完成后5+1-3=3秒后打印feature3）
        Thread.sleep(5000);
        CompletableFuture<String> future3 = future1.whenComplete(action);
    }

    /**
     * whenCompleteAsync()测试
     * 任务结束后，从线程池中取一个线程或新建线程执行后续行为
     *
     * @throws InterruptedException
     */
    private static void whenCompleteAsync() throws InterruptedException {
        System.out.println(System.currentTimeMillis() + " - main thread ：" + Thread.currentThread().getName());
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

    /**
     * thenApply()测试
     * 当一个线程依赖另一个线程时，可以使用 thenApply 方法来把这两个线程串行化。
     * 第二个任务依赖第一个任务的结果。
     * 第一个任务出现异常，则不执行 thenApply 方法
     *
     * @throws InterruptedException
     * @throws ExecutionException
     */
    private static void thenApply() throws InterruptedException, ExecutionException {
        System.out.println(System.currentTimeMillis() + " - main thread ：" + Thread.currentThread().getName());
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 10, 10, TimeUnit.MINUTES, new LinkedBlockingQueue<>());
        // 一个3秒的任务
        Supplier<Integer> supplier = () -> {
            try {
                Thread.sleep(3000);
                System.out.println(System.currentTimeMillis() + " - supplier thread : " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 5;
        };
        // 一个后继任务
        Function<Integer, String> function = (result -> {
            try {
                Thread.sleep(3000);
                System.out.println(System.currentTimeMillis() + " - function thread : " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "结果是：" + result;
        });

        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(supplier, threadPool);
        // 睡1秒，此时任务还未结束，任务结束后会立即调用thenApply()方法，并且和supplier的执行使用相同的线程
        Thread.sleep(1000);
        CompletableFuture<String> future2 = future1.thenApply(function);
        System.out.println(future2.get());
        // 睡5秒，此时任务结束，调用thenApply()方法，只能使用本方法的线程（根据执行时间得出thenApply是同步的）
        Thread.sleep(5000);
        CompletableFuture<String> future3 = future1.thenApply(function);
        System.out.println(future3.get());
    }

    /**
     * handle()测试
     * handle 是执行任务完成时对结果的处理。
     * handle 方法和 thenApply 方法处理方式基本一样。不同的是 handle 是在任务完成后再执行，还可以处理异常的任务。
     * thenApply 只可以执行正常的任务，任务出现异常则不执行 thenApply 方法
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void handle() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            //throw new  RuntimeException("Supplier运行异常");
            return 0;
        }).handle((param, throwable) -> {
            String result;
            if (Objects.isNull(throwable)) {
                result = "无异常，param = " + param;
            } else {
                result = "异常信息：" + throwable.getMessage();
            }
            return result;
        });
        System.out.println(future.get());
    }

    /**
     * thenAccept()测试
     * 接收任务的处理结果，并消费处理，无返回结果。
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void thenAccept() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> new Random().nextInt(10)).thenAccept(System.out::println);
        future.get();
    }

    /**
     * thenRun()测试
     * 跟 thenAccept 方法不一样的是，不关心任务的处理结果。只要上面的任务执行完成，就开始执行 thenRun
     * 【注】主要看函数式接口的方法出入参数
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void thenRun() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> new Random().nextInt(10)).thenRun(() -> {
            System.out.println("thenRun ...");
        });
        future.get();
    }

    /**
     * thenCombine()测试
     * thenCombine 会把两个 CompletionStage 的任务都执行完成后，把两个任务的结果一块交给 thenCombine 来处理。
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void thenCombine() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "hello1");
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "hello2");
        CompletableFuture<String> result = future1.thenCombine(future2, (t, u) -> t + " " + u);
        System.out.println(result.get());   // hello1 hello2
    }
}
