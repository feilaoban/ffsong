package com.mugu.blog.oauth.server.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@EnableConfigurationProperties
@Component
@ConfigurationProperties(prefix = "oauth.server")
public class TokenConstant {
    private String signKey;
}
