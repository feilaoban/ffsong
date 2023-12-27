package com.flying.demo;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.NumberUtil;
import com.flying.demo.handler.CaseConversionHandler;
import com.flying.demo.pojo.dto.StudentDeepCloneDTO;
import com.flying.demo.pojo.entity.Student;
import com.google.common.collect.Lists;
import org.apache.commons.lang.text.StrSubstitutor;
import org.apache.commons.lang3.SerializationUtils;

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
//        textStrSubstitutor();
//        calculateWeeksInMonth2(8);
//        calculateWeekOfYear();
//        calculateWeeksInMonth1(1);
//        printWeeksOfYear(1);
//        testDeepCloneObject();
        //testDeepCloneLong();
        intDivideRound();

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

    /**
     * 计算该周在全年是第几周
     *
     * @param month 月份，1-12
     * @return 该月份的周数
     */
    private static void calculateWeeksInMonth(int month) {
        Calendar calendar = Calendar.getInstance();
        // 设置年份为当前年份
        calendar.set(Calendar.YEAR, Calendar.getInstance().get(Calendar.YEAR));
        // 设置月份
        calendar.set(Calendar.MONTH, month - 1);
        // 设置日期为该月份的第一天
        calendar.set(Calendar.DATE, 1);
        // 获取该月份的第一天是星期几
        int firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        // 计算该月份的天数
        int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        // 计算该月份的周数
        int weeksInMonth = (daysInMonth + firstDayOfWeek - 2) / 7 + 1;
        // 输出该月份各个周，并结算每个周在全年是第几周
        for (int i = 1; i <= weeksInMonth; i++) {
            // 设置日期为该周的第一天
            calendar.set(Calendar.DATE, (i - 1) * 7 + 1);
            // 获取该周的第一天是全年的第几天
            int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
            // 计算该周在全年是第几周
            int weekOfYear = (dayOfYear + 6 - calendar.get(Calendar.DAY_OF_WEEK)) / 7;
            System.out.println("第" + i + "周，全年第" + weekOfYear + "周");
        }
    }

    /**
     * 计算指定月份的周数
     *
     * @param month 月份，1-12
     * @return 该月份的周数
     */
    private static void calculateWeeksInMonth2(int month) {
        Calendar calendar = Calendar.getInstance();
        // 设置年份为当前年份
        calendar.set(Calendar.YEAR, Calendar.getInstance().get(Calendar.YEAR));
        // 设置月份
        calendar.set(Calendar.MONTH, month - 1);
        // 设置日期为该月份的第一天
        calendar.set(Calendar.DATE, 1);
        // 获取该月份的第一天是星期几
        int firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        // 计算该月份的天数
        int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        // 计算该月份的周数
        int weeksInMonth = (daysInMonth + firstDayOfWeek - 2) / 7 + 1;
        System.out.println(weeksInMonth);
    }

    /**
     * 计算当前周是今年的第几周
     *
     * @return 当前周是今年的第几周
     */
    private static void calculateWeekOfYear() {
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.get(Calendar.WEEK_OF_YEAR));
    }

    private static List<Integer> calculateWeeksInMonth1(int month) {
        List<Integer> weeksOfYear = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        // 设置年份为当前年份
        calendar.set(Calendar.YEAR, Calendar.getInstance().get(Calendar.YEAR));
        // 设置月份
        calendar.set(Calendar.MONTH, month - 1);
        // 设置日期为该月份的第一天
        calendar.set(Calendar.DATE, 1);
        // 获取该月份的第一天是星期几
        int firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        // 计算该月份的天数
        int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        // 计算该月份的周数
        int weeksInMonth = (daysInMonth + firstDayOfWeek - 2) / 7 + 1;
        // 输出该月份各个周，并结算每个周在全年是第几周
        for (int i = 1; i <= weeksInMonth; i++) {
            // 设置日期为该周的第一天
            calendar.set(Calendar.DATE, (i - 1) * 7 + 1);
            // 获取该周的第一天是全年的第几天
            int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
            // 计算该周在全年是第几周
            int weekOfYear = (dayOfYear + 6 - calendar.get(Calendar.DAY_OF_WEEK)) / 7 + 1;
            weeksOfYear.add(weekOfYear);
        }
        System.out.println(weeksOfYear);
        return weeksOfYear;
    }

    public static void printWeeksOfYear(int month) {
        Calendar calendar = Calendar.getInstance();
        // 设置周一为每周的第一天
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        int weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
        int maxWeeks = calendar.getActualMaximum(Calendar.WEEK_OF_MONTH);
        for (int i = 1; i <= maxWeeks; i++) {
            System.out.println("Month " + month + ", Week " + i + " is Week " + weekOfYear + " of the year");
            weekOfYear++;
        }
    }

    public static void testDeepCloneObject() {
        Student student = new Student();
        student.setGuid(1L);
        student.setCnName("小明");
        List<Student> list = Lists.newArrayList(student);

        StudentDeepCloneDTO dto = new StudentDeepCloneDTO();
        dto.setStudents(list);

        List<Student> list1 = SerializationUtils.clone(dto).getStudents();
        for (Student student1 : list1) {
            student1.setCnName("小王");
        }

        //List<Student> list2 = new ArrayList<>(list);
        //for (Student student1 : list2) {
        //    student1.setCnName("小李");
        //}

        System.out.println(list);
        System.out.println(list1);
        //System.out.println(list2);
    }

    public static void testDeepCloneLong() {
        List<Long> list = new ArrayList<>();
        list.add(1L);

        List<Long> list1 = new ArrayList<>(list);
        list1.add(2L);

        System.out.println(list);
        System.out.println(list1);
        //System.out.println(list2);
    }

    private static void intDivideRound() {
        int dividend = 4;
        int divisor = 3;

        double result = (double) dividend / divisor; // 将dividend转换为double类型
        result = Math.round(result * 100.0) / 100.0; // 进行四舍五入并保留两位小数
        System.out.println(result);
    }
}
