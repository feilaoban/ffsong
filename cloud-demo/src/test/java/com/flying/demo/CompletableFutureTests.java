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
        //testSupplyAsync();
        //testFutureThread();
        //testRunAndSupplyAsync();
        //whenComplete();
        //whenCompleteAsync();
        //thenApply();
        //handle();
        //thenAccept();
        //thenRun();
        //thenCombine();
        //thenAcceptBoth();
        //applyToEither();
        //acceptEither();
        //runAfterEither();
        runAfterBoth();
    }

    private static void testSupplyAsync() throws ExecutionException, InterruptedException {
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
     *
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
     * 1、runAsync() & supplyAsync()测试
     * （Runnable & Supplier）
     *
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
     * 2、whenComplete()测试
     * 注意在任务结束前后，后续行为使用的是哪个线程（BiConsumer）
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
        // 睡5秒，此时任务结束，调用whenComplete()方法，只能使用本方法的线程（从异步开始执行算起，加上之前睡1秒，任务执行要3秒，也就是任务完成后5+1-3=3秒后打印future3）
        Thread.sleep(5000);
        CompletableFuture<String> future3 = future1.whenComplete(action);
    }

    /**
     * 3、whenCompleteAsync()测试
     * 任务结束后，从线程池中取一个线程或新建线程执行后续行为（BiConsumer）
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
     * 4、thenApply()测试
     * 当一个线程依赖另一个线程时，可以使用 thenApply 方法来把这两个线程串行化（Function）
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
     * 5、handle()测试
     * handle 是执行任务完成时对结果的处理（BiFunction）
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
     * 6、thenAccept()测试
     * 接收任务的处理结果，并消费处理，无返回结果（Consumer）
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void thenAccept() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> new Random().nextInt(10)).thenAccept(System.out::println);
        future.get();
    }

    /**
     * 7、thenRun()测试
     * 跟 thenAccept 方法不一样的是，不关心任务的处理结果。只要上面的任务执行完成，就开始执行 thenRun (Runnable)
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
     * 8、thenCombine()测试
     * thenCombine 会把两个 CompletionStage 的任务都执行完成后，把两个任务的结果一块交给 thenCombine 来处理（BiFunction）
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void thenCombine() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello1";
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello2";
        });
        CompletableFuture<String> result = future1.thenCombine(future2, (t, u) -> t + " " + u);
        System.out.println(result.get());   // hello1 hello2
    }

    /**
     * 9、thenAcceptBoth()测试
     * 当两个CompletionStage都执行完成后，把结果一块交给thenAcceptBoth来进行消费（BiConsumer）
     *
     * @throws InterruptedException
     */
    private static void thenAcceptBoth() throws InterruptedException, ExecutionException {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("future1=" + 3);
            return 3;
        });

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("future2=" + 3);
            return 3;
        });
        //future1.thenAcceptBoth(future2, (t, u) -> System.out.println("future1=" + t + ",future2=" + u));
        /*
         【注意】最后一定要get()，否则获取不到 CompletableFuture<T> 的结果
         查看get()源码：Waits if necessary for this future to complete, and then returns its result.
         翻译：如果需要，等待此future完成，然后返回其结果。
         */
        //CompletableFuture<Void> result = future1.thenAcceptBoth(future2, (t, u) -> System.out.println("future1=" + t + ",future2=" + u));
        //result.get();
        // 等价于
        future1.thenAcceptBoth(future2, (t, u) -> System.out.println("future1=" + t + ",future2=" + u)).get();
    }

    /**
     * 10、applyToEither()测试
     * 两个CompletionStage，谁执行返回的结果快，就用那个CompletionStage的结果进行下一步的处理（Function）
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void applyToEither() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("future1=" + 1);
            return 1;
        });

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("future2=" + 3);
            return 3;
        });

        CompletableFuture<Integer> result = future1.applyToEither(future2, t -> t * 2);
        System.out.println(result.get());
    }

    /**
     * 11、acceptEither()测试
     * 两个CompletionStage，谁执行返回的结果快，就用那个CompletionStage的结果进行下一步的消费（Consumer）
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void acceptEither() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("future1=" + 1);
            return 1;
        });

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("future2=" + 3);
            return 3;
        });

        future1.acceptEither(future2, t -> System.out.println("result=" + t)).get();
    }

    /**
     * 12、runAfterEither()测试
     * 两个CompletionStage，任何一个完成了都会执行下一步的操作（Runnable）
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void runAfterEither() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("future1=" + 1);
            return 1;
        });

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("future2=" + 3);
            return 3;
        });

        future1.runAfterEither(future2, () -> System.out.println("上面两个任务已经有一个完成了···")).get();
    }

    /**
     * 12、runAfterEither()测试
     * 两个CompletionStage，都完成了计算才会执行下一步的操作（Runnable）
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void runAfterBoth() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("future1=" + 1);
            return 1;
        });

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("future2=" + 3);
            return 3;
        });

        future1.runAfterBoth(future2, () -> System.out.println("上面两个任务都完成了")).get();
    }
}
