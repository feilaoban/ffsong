package com.flying.demo.lambda;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;

/**
 * @Author songfeifei
 * @Date 2021/12/10 11:40
 * @Description
 */
public class TestLambda {

    private static void printStr(String str) {
        System.out.println("printStr : " + str);
    }

    private void toUpper(){
        System.out.println("toUpper : " + this.toString());
    }

    private void toLower(String str){
        System.out.println("toLower : " + str);
    }

    private int toInt(String str){
        System.out.println("toInt : " + str);
        return 1;
    }

    /**
     * main
     * @param args
     */
    public static void main(String[] args) {
//        List<String> l = Arrays.asList("a","b","c");
//
//        l.forEach(new MyConsumer<>());
//        MyConsumer myConsumer = new MyConsumer();
//        l.forEach(System.out::println);
//        l.forEach(s -> System.out.println(s));
//        l.forEach(s -> myConsumer.accept(s));

        // printStr为静态方法，直接传入参数即可
        Consumer<String> printStrConsumer = TestLambda::printStr;
        printStrConsumer.accept("printStr");

        // toUpper为非静态方法，需创建一个实例作为调用对象去调用toUpper()
        Consumer<TestLambda> toUpperConsumer = TestLambda::toUpper;
        TestLambda testLambda = new TestLambda();
        toUpperConsumer.accept(testLambda);
        testLambda.toUpper();

        // BiConsumer第一个参数为调用对象去调用toLower()，第二个参数为入参
        BiConsumer<TestLambda, String> toLowerConsumer = TestLambda::toLower;
        toLowerConsumer.accept(new TestLambda(), "toLower");

        // BiFunction第一个参数为调用对象去调用toInt()，第二个参数为入参，第三个参数为出参
        BiFunction<TestLambda, String, Integer> toIntConsumer = TestLambda::toInt;
        toIntConsumer.apply(new TestLambda(), "toInt");
    }
}

