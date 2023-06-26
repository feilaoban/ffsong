package controller;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Maps;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pojo.constant.AuthConstants;
import pojo.R;
import pojo.constant.RedisKeyConstants;
import pojo.entity.OauthClient;
import service.IOauthClientService;
import service.RedisService;
import util.AppUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * AuthController
 * 获取授权码
 * 获取token
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	TokenEndpoint tokenEndPoint;
	@Autowired
    private IOauthClientService oauthClientService;
	@Autowired
	private RedisService redisService;

	@ApiOperation(value = "获取授权码", notes = "获取授权码")
	@PostMapping("/code")
	public R<HashMap<String, Object>> getCode(@RequestParam String appid) throws HttpRequestMethodNotSupportedException {
		OauthClient client = oauthClientService.findById(appid);
		if (Objects.isNull(client)) {
            return R.failed("appid不存在");
        }
		
		String authCode = AppUtils.getCode();
		String key = RedisKeyConstants.OPEN_AUTH_CODE + appid;
		String value = authCode+";;"+client.getClientSecret();
		redisService.setEx(key, value, 1, TimeUnit.HOURS);
		HashMap<String, Object> resultMap = Maps.newHashMap();
		resultMap.put("authCode", authCode);
		return R.ok(resultMap);
	}
	
	private void checkAuthCode (String code, String secret, String redisValue) {
        if(StrUtil.isEmpty(redisValue)){
        	throw new RuntimeException("授权码已失效");
        }
        String authCode = redisValue.split(";;")[0];
        String appSecret = redisValue.split(";;")[1];
        if(!StringUtils.equals(code, authCode)){
        	throw new RuntimeException("授权码错误");
        }
        if(!StringUtils.equals(secret, appSecret)){
        	throw new RuntimeException("秘钥错误");
        }
    }

	@ApiOperation(value = "获取token", notes = "获取token")
	@PostMapping("/token")
	public R<HashMap<String, Object>> getToken(
			@RequestParam String appid, @RequestParam String secret, @RequestParam String authCode) throws HttpRequestMethodNotSupportedException {
		HashMap<String, String> parameters = Maps.newHashMap();
		parameters.put("grant_type", AuthConstants.GRANT_TYPE);
		parameters.put("client_id", appid);
		parameters.put("client_secret", secret);
		
		String key = RedisKeyConstants.OPEN_AUTH_CODE + appid;
		String value = redisService.get(key);
		redisService.delete(key);
		// 校验授权码
		checkAuthCode(authCode, secret, value);
		
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(appid, secret, Collections.emptyList());
		ResponseEntity<OAuth2AccessToken> result = tokenEndPoint.postAccessToken(authRequest, parameters);
		OAuth2AccessToken accessToken = result.getBody();
		HashMap<String, Object> resultMap = Maps.newHashMap();
		resultMap.put("accessToken", AuthConstants.TOKEN_TYPE + accessToken.getValue());
		resultMap.put("expiresIn", accessToken.getExpiresIn());
		return R.ok(resultMap);
	}
	
}
