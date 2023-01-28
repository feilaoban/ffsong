package com.mugu.blog.elasticsearch.start.config;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@AutoConfigureBefore(value = {ElasticsearchAutoConfiguration.class})
@Configuration
public class ElasticsearchAutoConfig {
}
