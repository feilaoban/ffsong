package config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import pojo.constant.AuthConstants;
import pojo.constant.CommonConstants;
import util.KeyStoreKeyFactory;

import java.security.KeyPair;
import java.util.HashMap;
import java.util.Map;

/**
 * 令牌的配置
 */
@Slf4j
@Configuration
public class JwtTokenStoreConfig {

	/*IOpenOauthClientService openOauthClientService;*/

    @Bean
    public TokenStore jwtTokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }
    
    @Bean
    public KeyPair keyPair() {
        ClassPathResource ksFile = new ClassPathResource("ynby_platform_jwt.jks");
        KeyStoreKeyFactory ksFactory = new KeyStoreKeyFactory(ksFile, CommonConstants.JWKS_STORE_PWD.toCharArray());
        return ksFactory.getKeyPair("oauth_pair");
    }

    
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
//        accessTokenConverter.setSigningKey(AuthConstants.SIGN_KEY);
        accessTokenConverter.setKeyPair(keyPair());
        return accessTokenConverter;
    }
    
    /**
	 * token增强处理，支持扩展信息
	 * @return TokenEnhancer
	 */
	@Bean
	public TokenEnhancer tokenEnhancer() {
		return (accessToken, authentication) -> {
		    try {
                if (AuthConstants.GRANT_TYPE
                        .equals(authentication.getOAuth2Request().getGrantType())) {
                	final Map<String, Object> additionalInfo = new HashMap<>(16);
                	/*OpenOauthClient openOauthClient = openOauthClientService.findById(authentication.getOAuth2Request().getClientId());*/
                	additionalInfo.put("tenantId", "tenantId");
                	additionalInfo.put("companyId", "companyId");
                	additionalInfo.put("orgLevel", "orgLevel");
                	additionalInfo.put("userType", "-1");
                	((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
                    return accessToken;
                }
            }catch(Exception e) {
		        log.error(e.getMessage(), e);
            }
			return accessToken;
		};
	}

    /*public static void main(String[] args) {
    	// {"scope":["all"],"tenantId":888,"exp":1676966152,"jti":"4e4192db-ded7-4e11-b5d4-53070e42d7b2","client_id":"client_1"}
    	String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzY29wZSI6WyJhbGwiXSwidGVuYW50SWQiOjg4OCwiZXhwIjoxNjc2OTY2MTUyLCJqdGkiOiI0ZTQxOTJkYi1kZWQ3LTRlMTEtYjVkNC01MzA3MGU0MmQ3YjIiLCJjbGllbnRfaWQiOiJjbGllbnRfMSJ9.ZHJfQDHhWa5xEWmiRGOxxsz0RCuDHMmXehSVeFlsbwY";
    	
    	Claims body = Jwts.parser().setSigningKey(AuthConstants.SIGN_KEY.getBytes()).parseClaimsJws(token).getBody();
    	System.out.println(JSON.toJSONString(body));
	}*/
}
