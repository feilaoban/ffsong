package com.flying.demo.config;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @Author songfeifei
 * @Date 2022/4/7 17:56
 * @Description MQ生产者配置
 */
@Configuration
@Slf4j
public class MqProducerConfig {

	@Autowired
	private MqParamConfig param;

	/**
	 * 生产者
	 */
	@Getter
	private final DefaultMQProducer producer = new DefaultMQProducer();

	@PostConstruct
	public void start() {
		try {
			producer.setNamesrvAddr(param.getNameService());
			producer.setVipChannelEnabled(false);
			producer.setRetryTimesWhenSendAsyncFailed(param.getRetryTimes());
			producer.setProducerGroup(param.getProducerGroup());
			producer.start();
			log.info("消息服务MQ生产者启动成功...");
		} catch (MQClientException e) {
			log.info("消息服务MQ生产者启动失败...");
		}
	}
}
