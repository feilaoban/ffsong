package com.flying.demo.common.annotation;

import java.lang.annotation.*;

/**
 * @Author songfeifei
 * @Date 2022/6/6 17:44
 * @Description 在方法上使用，用于指定使用哪个数据源
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {
    String name() default "";
}
