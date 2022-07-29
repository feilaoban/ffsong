package com.flying.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;


@Slf4j
@EnableAsync
@Configuration
public class TheadPoolConfig {

	@Value("${executor.corePoolSize}")
	private int corePoolSize;
	@Value("${executor.maxPoolSize}")
	private int maxPoolSize;
	@Value("${executor.keepAliveTime}")
	private int keepAliveTime;
	@Value("${executor.capacity}")
	private int capacity;

	@Bean("threadPoolExecutor")
	public Executor syncInquiryPool(){
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(corePoolSize);
		executor.setMaxPoolSize(maxPoolSize);
		executor.setKeepAliveSeconds(keepAliveTime);
		executor.setQueueCapacity(capacity);
		executor.setThreadNamePrefix("sync-inquiry-");
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
		return executor;
	}
}
