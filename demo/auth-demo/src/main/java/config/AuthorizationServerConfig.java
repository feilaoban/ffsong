package config;

import com.google.common.collect.Lists;
import filter.CustomClientCredentialsTokenEndpointFilter;
import handler.OpenOAuth2WebResponseExceptionTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * 认证授权中心的配置，配置授权中心的工作方式
 * `@EnableAuthorizationServer`：这个注解标注这是一个认证授权中心
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
	 
	/**
	 * 自定义客户端，支持自定义权限等
	 */
	@Autowired
	@Qualifier("openClientDetailsService")
	ClientDetailsService clientDetailsService;
	
	@Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired
    @Qualifier("jwtTokenStore")
    private TokenStore tokenStore;

    @Autowired
    private TokenEnhancer tokenEnhancer;
    
    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;
    
    private OpenOAuth2WebResponseExceptionTranslator exceptionTranslator = new OpenOAuth2WebResponseExceptionTranslator();

    /**
     * 配置端点的安全访问规则、过滤器
     */
	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        // 自定义 ClientCredentialsTokenEndpointFilter，用于处理客户端id，密码错误的异常
		CustomClientCredentialsTokenEndpointFilter endpointFilter = new CustomClientCredentialsTokenEndpointFilter(oauthServer);
        endpointFilter.afterPropertiesSet();
        endpointFilter.setAuthenticationEntryPoint(authenticationEntryPoint);

        oauthServer.addTokenEndpointAuthenticationFilter(endpointFilter);
		oauthServer
                // 开启/oauth/token_key 验证端点权限访问
                .tokenKeyAccess("permitAll()")
                // 开启/oauth/check_token 验证端点认证权限访问
                .checkTokenAccess("permitAll()");
                // 允许表单认证，一定不要加allowFormAuthenticationForClients，否则自定义的OAuthServerClientCredentialsTokenEndpointFilter不生效
                //.allowFormAuthenticationForClients();
	}

    /**
     * 配置客户端详情，并不是所有的客户端都能接入授权服务
     */
    @Override
    public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
    	clients.withClientDetails(clientDetailsService);
    }

    /**
     * 配置令牌访问的端点
     * AuthorizationServerEndpointsConfigurer其实是一个装载类，装载Endpoints所有相关的类配置，
     * 如AuthorizationServer、TokenServices、TokenStore、ClientDetailsService、UserDetailsService，
     * 也就是说进行密码验证的一些工具类或服务类，均在这个地方进行注入。
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
    	
        //配置存储令牌策略
        endpoints
        		.pathMapping("/oauth/token", "/api/auth/token")
                //支持密码授权
                .authenticationManager(authenticationManager)
                .tokenServices(createDefaultTokenServices())
                //使用jwt
//                .tokenStore(tokenStore)
//                .tokenEnhancer(tokenEnhancerChain)
//                .accessTokenConverter(jwtAccessTokenConverter)
                //刷新令牌授权是否包含对用户信息的检查
//                .userDetailsService(userService)
                //支持get post delete请求
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST,HttpMethod.DELETE)
                .exceptionTranslator(exceptionTranslator);
    }
    
    private DefaultTokenServices createDefaultTokenServices() {
    	// jwt 增强原始token
		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		tokenEnhancerChain.setTokenEnhancers(Lists.newArrayList(tokenEnhancer, jwtAccessTokenConverter));
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore);
        tokenServices.setTokenEnhancer(tokenEnhancerChain);
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setReuseRefreshToken(true);
        tokenServices.setClientDetailsService(clientDetailsService);
        return tokenServices;
    }
    
    
}
