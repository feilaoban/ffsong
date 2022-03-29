package com.flying.ghost.common.annotation;

import java.lang.annotation.*;

/**
 * @Author songfeifei
 * @Date 2022/3/28 16:20
 * @Description
 */
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CaseConversion {

    /**
     * 是否转换大小写
     * @return
     */
    boolean convert() default false;
}
