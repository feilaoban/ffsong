package com.flying.demo.lambda;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author songfeifei
 * @Date 2021/12/10 11:40
 * @Description
 */
public class LambdaTest {

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
//        test1();
//        test2();
//        test3();
        test4();
    }

    private static void test1() {
        //        List<String> l = Arrays.asList("a","b","c");
//
//        l.forEach(new MyConsumer<>());
//        MyConsumer myConsumer = new MyConsumer();
//        l.forEach(System.out::println);
//        l.forEach(s -> System.out.println(s));
//        l.forEach(s -> myConsumer.accept(s));

        // printStr为静态方法，直接传入参数即可
        Consumer<String> printStrConsumer = LambdaTest::printStr;
        printStrConsumer.accept("printStr");

        // toUpper为非静态方法，需创建一个实例作为调用对象去调用toUpper()
        Consumer<LambdaTest> toUpperConsumer = LambdaTest::toUpper;
        LambdaTest lambdaTest = new LambdaTest();
        toUpperConsumer.accept(lambdaTest);
        lambdaTest.toUpper();

        // BiConsumer第一个参数为调用对象去调用toLower()，第二个参数为入参
        BiConsumer<LambdaTest, String> toLowerConsumer = LambdaTest::toLower;
        toLowerConsumer.accept(new LambdaTest(), "toLower");

        // BiFunction第一个参数为调用对象去调用toInt()，第二个参数为入参，第三个参数为出参
        BiFunction<LambdaTest, String, Integer> toIntConsumer = LambdaTest::toInt;
        toIntConsumer.apply(new LambdaTest(), "toInt");
    }

    /**
     * map与flatMap区别：https://blog.csdn.net/qq_43842093/article/details/121803258
     *
     * 此种写法有误
     * 传递给map方法的Lambda为每个单词返回了一个String[]，需要将多个stream合并一个（即：扁平化）
     * flatMap执行了如下过程：Stream<Stream> -> Stream<String>
     */
    private static void test2() {
        List<String> words = Lists.newArrayList("Hello", "word");
        List<String[]> collect = words.stream()
                .map(word -> word.split(""))
                .distinct()
                .collect(Collectors.toList());
        collect.forEach(x -> {
            for (String s : x) {
                System.out.println(s);
            }
        });
    }

    /**
     * 等价于test4
     */
    private static void test3() {
        List<String> words = Lists.newArrayList("Hello", "word");
        List<String> collect = words.stream()
                .map(word -> word.split(""))
                .flatMap(Stream::of)
                .distinct()
                .collect(Collectors.toList());
        collect.forEach(System.out::println);
    }

    /**
     * 等价于test3
     */
    private static void test4() {
        List<String> words = Lists.newArrayList("Hello", "word");
        List<String> collect = words.stream()
                .flatMap(word -> Stream.of(word.split("")))
                .distinct()
                .collect(Collectors.toList());
        collect.forEach(System.out::println);
    }
}

