package com.flying.demo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @Author songfeifei
 * @Date 2021/8/16 16:55
 * @Description
 */
public class CompletableFutureTest {
    public static void main(String[] args) {
        CompletableFutureTest.futureTest();
    }

    private static void futureTest() {
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
}
