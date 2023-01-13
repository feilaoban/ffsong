package com.flying.demo.common.aop;

import com.flying.demo.common.annotation.DataSource;
import com.flying.demo.config.DynamicDataSource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @Author songfeifei
 * @Date 2022/6/8 17:10
 * @Description 多数据源切换AOP，@Order(-100)是为了保证AOP在事务注解之前生效,Order的值越小,优先级越高
 */
@Aspect
@Component
@Order(-100)
@Slf4j
public class DataSourceAspect {

    @Pointcut("execution(* com.flying.demo.service..*.*(..))")
    private void dsPointCut() {
    }

    @Around("dsPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取当前指定的数据源
        MethodSignature ms = (MethodSignature) joinPoint.getSignature();
        Method method = ms.getMethod();
        DataSource dataSource = method.getAnnotation(DataSource.class);

        if (Objects.isNull(dataSource)) {
            // 使用默认数据源
            DynamicDataSource.setDataSource("master");
            log.info("未匹配到数据源，使用默认数据源");
        } else {
            // 匹配到的话，设置到动态数据源上下文中
            DynamicDataSource.setDataSource(dataSource.name());
            log.info("匹配到数据源：{}", dataSource.name());
        }

        try {
            // 执行目标方法，返回值即为当前方法的返回值
            return joinPoint.proceed();
        } finally {
            // 方法执行完毕之后，销毁当前数据源信息，进行垃圾回收
            DynamicDataSource.clearDataSource();
            log.info("当前数据源已清空");
        }
    }
}
