package com.flying.demo;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.NumberUtil;
import com.flying.demo.handler.CaseConversionHandler;
import com.flying.demo.pojo.entity.Student;
import org.apache.commons.lang.text.StrSubstitutor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @Author songfeifei
 * @Date 2022/1/26 14:47
 * @Description
 */
class CommonTests {

    private static int b = 0;

    public static void main(String[] args) throws IllegalAccessException, InterruptedException {
        //testDateUtil();
//        testRegular();
//        test2ListToMap();
//        testString2Integer();
//        testAnnotation();
//        testStreamMin();
//        testBigDecimal();
//        testSnowflake();
//        testPredicate();
//        testAtomicInteger();
//        testDiffLocalDateTime();
        textStrSubstitutor();
    }

    private static void testDateUtil() {
        //List<String> list = Lists.newArrayList("str");
        //System.out.println(list.contains(null));

        System.out.println(DateUtil.yesterday());
    }

    private static void testRegular() {
        Pattern CHINESE_CHARACTER_PATTERN = Pattern.compile("[^(a-zA-Z0-9\\u4e00-\\u9fa5)]");
        Matcher matcher = CHINESE_CHARACTER_PATTERN.matcher("汉字123：哈哈、abc,.?!@34#$");
        System.out.println(matcher.replaceAll("").trim());
    }

    private static void test2ListToMap() {
        List<Long> list1 = new ArrayList<>();
        list1.add(1L);
        list1.add(2L);
        list1.add(3L);
        List<String> list2 = new ArrayList<>();
        list2.add("A");
        list2.add("B");
        list2.add("C");
        Map<Long, String> map = list1.stream().collect(Collectors.toMap(k -> k, k -> list2.get(list1.indexOf(k))));
        System.out.println(map.toString());
    }

    private static void testString2Integer() {
        String num = "1";
        System.out.println(Integer.valueOf(num));
//        System.out.println(Integer.valueOf(""));        // java.lang.NumberFormatException: For input string: ""
//        System.out.println(Integer.valueOf(null));      // java.lang.NumberFormatException: null
    }

    private static void testAnnotation() throws IllegalAccessException {
        Student student = new Student();
        student.setCnName("小明");
        student.setEnName("xiaoming");
        student.setGender("男");
        CaseConversionHandler.caseConvert(student);
        System.out.println(student.toString());
    }

    private static void testStreamMin() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(1);
        list.add(2);
        list.add(3);
        int min = list.stream().min(Integer::compareTo).get();
        System.out.println(min);
    }

    private static void testBigDecimal() {
        BigDecimal b1 = new BigDecimal(1);
        try {
            BigDecimal r1 = b1.add(null);
        } finally {
            System.out.println("finally:" + NumberUtil.add(null, b1));
        }
    }

    private static void testSnowflake() {
        System.out.println(IdUtil.getSnowflake(0, 1).nextId());
    }

    private static void testPredicate() {
        System.out.println(executorPredicate().test(new Student()));
        System.out.println(executorPredicate().test(null));
    }

    private static Predicate<Student> executorPredicate() {
        return Objects::isNull;
    }

    private static void testAtomicInteger() throws InterruptedException {
        AtomicInteger a = new AtomicInteger(0);
        AtomicInteger c = new AtomicInteger();
        // 循环创建10个线程，每个线程执行1000次自增操作
        for (int x = 0; x < 10; x++) {
            Thread thread = new Thread(() -> {
                // y设置大一点，确保在当前线程跑完前，能够进下个线程，从而出现读取b时出现线程安全问题
                for (int y = 0; y < 1000; y++) {
                    a.getAndIncrement();
                    b++;
                    c.getAndAdd(cIncrement());
                }
            });
            thread.start();
        }
        // sleep 3秒，等待所有线程执行完毕
        Thread.sleep(3000);
        System.out.println("a=" + a.get() + ", b=" + b + ", c=" + c);
    }

    private static int cIncrement() {
        int c = 0;
        return ++c;
    }

    private static void testDiffLocalDateTime() {
        LocalDateTime start = LocalDateTime.parse("2022-11-30T11:42:00");
        LocalDateTime end = LocalDateTime.now();
        System.out.println("相差天数：" + LocalDateTimeUtil.between(start, end, ChronoUnit.DAYS));
    }

    private static void textStrSubstitutor() {
        String content1 = "验证码${code}，您正在注册，若非本人操作，请勿泄露。";
        String content2 = "验证码{1}，您正在注册，若非本人操作，请勿泄露。";

        Map<String, String> param1 = new HashMap<>();
        param1.put("code", "123");
        Map<String, String> param2 = new HashMap<>();
        param2.put("1", "456");

        StrSubstitutor strSubstitutor1 = new StrSubstitutor(param1);
        StrSubstitutor strSubstitutor2 = new StrSubstitutor(param2);

        System.out.println(strSubstitutor1.replace(content1));
        content2 = content2.replaceAll("\\{", "\\${");
        System.out.println(strSubstitutor2.replace(content2));
    }
}
