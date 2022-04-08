package com.flying.demo.common.handler;

import com.flying.demo.common.annotation.CaseConversion;

import java.lang.reflect.Field;
import java.util.Objects;

/**
 * @Author songfeifei
 * @Date 2022/3/28 16:55
 * @Description
 */
public class CaseConversionHandler {

    public static void caseConvert(Object obj) throws IllegalAccessException {
        Class clazz = obj.getClass();
        // 获取目标类属性列表
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            // 获取属性上的注解
            CaseConversion caseConversion = field.getAnnotation(CaseConversion.class);
            if (Objects.nonNull(caseConversion)) {
                // 获取注解设置的值
                if (caseConversion.convert()) {
                    // 当前字段表示的值
                    String value = field.get(obj).toString();
                    // 修改当前属性的值
                    field.set(obj, value.toUpperCase());
                }
            }
        }
    }
}
