package config;

import handler.AuthAccessDeniedHandler;
import handler.AuthExceptionEntryPointHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

import javax.annotation.Resource;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Resource
    private AuthAccessDeniedHandler accessDeniedHandler;

    @Resource
    private AuthExceptionEntryPointHandler authExceptionEntryPoint;

    @Resource
    private JwtAuthenticationConverter jwtAuthenticationConverter;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.accessDeniedHandler(accessDeniedHandler).authenticationEntryPoint(authExceptionEntryPoint);
    }

    // TODO 这里配置白名单不生效
    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.oauth2ResourceServer(
                jwt -> jwt.jwt().jwtAuthenticationConverter(jwtAuthenticationConverter)
        );
        http
                .authorizeRequests(authReq -> authReq
//		      .mvcMatchers("/**").permitAll()
                        .anyRequest().authenticated()).csrf().disable()
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
    }

}