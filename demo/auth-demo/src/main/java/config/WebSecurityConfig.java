package config;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pojo.constant.CommonConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * spring security的安全配置
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * api服务的白名单
	 */
	@Value("${api_white_paths}")
	private List<String> apiWhitePaths;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * 暴露AuthenticationManager
	 * @return
	 * @throws Exception
	 */
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	// TODO 这里白名单是生效的
	@Override
    public void configure(WebSecurity web) throws Exception {
		ArrayList<String> newArrayList = Lists.newArrayList();
		newArrayList.addAll(apiWhitePaths);
		newArrayList.addAll(CommonConstants.SWAGGER_WHITES);
		String[] array = new String[newArrayList.size()];
		String[] apiWhitePathArray = newArrayList.toArray(array);
        web
            .ignoring()
            .antMatchers(apiWhitePathArray);
    }

}
