package com.flying.demo;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.NumberUtil;
import com.flying.demo.handler.CaseConversionHandler;
import com.flying.demo.pojo.entity.Student;
import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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

    public static void main(String[] args) throws IllegalAccessException {
        //commonTest();
//        testRegular();
//        test2ListToMap();
//        testString2Integer();
//        testAnnotation();
//        testStreamMin();
//        testBigDecimal();
//        testSnowflake();
        testPredicate();
    }

    private static void commonTest() {
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
}
