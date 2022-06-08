package com.flying.demo.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author songfeifei
 * @Date 2022/6/8 14:16
 * @Description 多数据源配置类
 */
@Component
@Configuration
public class DataSourceConfig {

    /**
     * 创建数据源master
     * @return 数据源master
     */
    @Bean(name = "master")
    @ConfigurationProperties("spring.datasource.dynamic.datasource.master")
    public DataSource masterDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 创建数据源slave
     * @return 数据源slave
     */
    @Bean(name = "slave")
    @ConfigurationProperties("spring.datasource.dynamic.datasource.slave")
    public DataSource slaveDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 数据源配置
     * @param master 数据源master
     * @param slave 数据源slave
     * @return 动态数据源切换对象
     */
    @Bean
    @Primary
    public DynamicDataSource dataSource(@Qualifier("master") DataSource master,
                                        @Qualifier("slave") DataSource slave) {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        Map<Object, Object> dsMap = new HashMap<>(2);
        dsMap.put("master", master);
        dsMap.put("slave", slave);
        dynamicDataSource.setDefaultTargetDataSource(master);
        dynamicDataSource.setTargetDataSources(dsMap);
        return dynamicDataSource;
    }
}
