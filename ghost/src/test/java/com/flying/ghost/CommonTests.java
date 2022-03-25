package com.flying.ghost;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @Author songfeifei
 * @Date 2022/1/26 14:47
 * @Description
 */
@SpringBootTest
class CommonTests {

    @Test
    void testRegular() {
        Pattern CHINESE_CHARACTER_PATTERN = Pattern.compile("[^(a-zA-Z0-9\\u4e00-\\u9fa5)]");
        Matcher matcher = CHINESE_CHARACTER_PATTERN.matcher("汉字123：哈哈、abc,.?!@34#$");
        System.out.println(matcher.replaceAll("").trim());
    }

    @Test
    void test2ListToMap() {
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

    @Test
    void testString2Integer(String[] args) {
        String num = "1";
        System.out.println(Integer.valueOf(num));
        System.out.println(Integer.valueOf(""));
        System.out.println(Integer.valueOf(null));
    }
}
