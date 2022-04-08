package com.flying.demo.common.config;

import com.flying.demo.common.handler.MqListenHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Author songfeifei
 * @Date 2022/4/7 17:56
 * @Description MQ消费者配置
 */
@Component
@Slf4j
public class MqConsumerConfig {

	@Autowired
	private MqParamConfig param;

	@Autowired
	private MqListenHandler listenHandler;


	@PostConstruct
	public void start() {
		try {
			DefaultMQPushConsumer consumer = new DefaultMQPushConsumer();
			consumer.setMaxReconsumeTimes(param.getRetryTimes());
			consumer.setMessageModel(MessageModel.CLUSTERING);
			consumer.setConsumeThreadMax(Runtime.getRuntime().availableProcessors() * 10);
			consumer.setConsumeThreadMin(Runtime.getRuntime().availableProcessors());
			consumer.setMaxReconsumeTimes(param.getRetryTimes());
			consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
			consumer.setNamesrvAddr(param.getNameService());
			consumer.setVipChannelEnabled(false);
			consumer.setConsumerGroup(param.getConsumerGroup());
			consumer.subscribe(param.getTopic(), "*");
			consumer.registerMessageListener(listenHandler);
			consumer.start();
			log.info("消息服务MQ消费者启动成功...");
		} catch (MQClientException e) {
			log.info("消息服务MQ消费者启动失败...");
		}
	}

}
