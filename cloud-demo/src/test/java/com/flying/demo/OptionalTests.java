package com.flying.demo;

import lombok.Data;

import java.util.Optional;

/**
 * @Author songfeifei
 * @Date 2022/4/11 10:28
 * @Description
 */
public class OptionalTests {

    public static void main(String[] args) {
//        test1();
//        System.out.println(test2());
        test3();
    }

    private static void test1() {
        Object obj = getObj();
        System.out.println(Optional.ofNullable(obj).orElse(new Object()));
        System.out.println(Optional.ofNullable(obj).orElseGet(Object::new));
        System.out.println(Optional.ofNullable(obj).map(Object::toString).orElse("orElse：obj为空"));
        System.out.println(Optional.ofNullable(obj).map(Object::toString).orElseGet(() -> {
            return "orElse：obj为空";
        }));
    }

    /**
     * 避免判断风暴
     * @return
     */
    private static String test2() {
//        School school = null;
        School school = new School();
        Clazz clazz = new Clazz();
        school.setClazz(clazz);
        return Optional.ofNullable(school)
                // 任何中间操作为空，都会走到orElse
                .map(School::getClazz)
                .map(Clazz::getStudent)
                .map(Student::getName)
                .orElse("学生信息为空");
    }

    private static void test3() {
        Student student = null;
//        Student student = new Student();
//        student.setId("1");
//        student.setName("小明");
        Optional.ofNullable(student).ifPresent(s -> System.out.println(s.getName()));
    }

    private static Object getObj() {
        return null;
    }

    @Data
    static class School {
        Clazz clazz;
    }

    @Data
    static class Clazz {
        Student student;
    }

    @Data
    static class Student {
        String id;
        String name;
    }
}
