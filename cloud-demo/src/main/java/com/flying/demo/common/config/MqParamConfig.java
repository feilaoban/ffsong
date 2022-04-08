package com.flying.demo.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author songfeifei
 * @Date 2022/4/7 17:56
 * @Description MQ配置
 */
@Configuration
@ConfigurationProperties(prefix = "rocketmq")
@Data
public class MqParamConfig {

	/**
	 * 轻量化注册中心
	 */
	private String nameService;

	/**
	 * 生产者组
	 */
	private String producerGroup;

	/**
	 * 消费者组
	 */
	private String consumerGroup;

	/**
	 * 重试次数
	 */
	private Integer retryTimes;

	/**
	 * topic
	 */
	private String topic;


}
