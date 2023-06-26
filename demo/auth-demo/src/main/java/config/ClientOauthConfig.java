package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import pojo.entity.OauthClient;
import service.IOauthClientService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Configuration
public class ClientOauthConfig {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private IOauthClientService oauthClientService;

    /**
     * 从数据库加载客户端信息
     * 加载Bean到Spring容器中，自定义ClientDetailsService
     * 指定的clientID从数据库中查询是否存在，加载客户端的一些配置信息
     */
    @Bean
    public ClientDetailsService openClientDetailsService() {
    	return clientId -> {
            //通过clientId查询客户端信息 从数据库中获取
    		OauthClient authority = oauthClientService.findById(clientId);
            if (Objects.isNull(authority)) {
                //返回的错误信息
                throw new ClientRegistrationException("clientId无效");
            }
            BaseClientDetails clientDetails = new BaseClientDetails();
            //设置clientID
            clientDetails.setClientId(authority.getClientId());
            //设置clientSecret
            clientDetails.setClientSecret(passwordEncoder.encode(authority.getClientSecret()));
            //设置token有效期
            clientDetails.setAccessTokenValiditySeconds(authority.getAccessTokenValidity());
            clientDetails.setRefreshTokenValiditySeconds(authority.getRefreshTokenValidity());
            //设置授权类型
            clientDetails.setAuthorizedGrantTypes(Arrays.asList(authority.getAuthorizedGrantTypes().split("\\,")));
            List<String> list = new ArrayList<>();
            list.add("all");
            //设置scope范围列表
            clientDetails.setScope(list);
            return clientDetails;
        };
    }

}
