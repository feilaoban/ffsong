package com.mugu.blog.oauth.server.controller;

import com.mugu.blog.core.model.LoginVal;
import com.mugu.blog.core.model.ResultMsg;
import com.mugu.blog.core.model.oauth.OAuthConstant;
import com.mugu.blog.core.utils.OauthUtils;
import com.mugu.blog.oauth.server.exception.OAuthServerWebResponseExceptionTranslator;
import com.mugu.blog.user.api.feign.UserFeign;
import com.mugu.blog.user.common.req.SysBindReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.BadClientCredentialsException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.endpoint.CheckTokenEndpoint;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Api(value = "OAuth接口")
@RequestMapping("/oauth")
@Controller
@Slf4j
public class AuthController implements InitializingBean {

    //令牌请求的端点
    @Autowired
    private TokenEndpoint tokenEndpoint;

    @Autowired
    private CheckTokenEndpoint checkTokenEndpoint;

    //自定义异常翻译器，针对用户名、密码异常，授权类型不支持的异常进行处理
    private OAuthServerWebResponseExceptionTranslator translate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private UserFeign userFeign;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 重写/oauth/token这个默认接口，返回的数据格式统一
     */
    @PostMapping(value = "/token")
    @ResponseBody
    public ResultMsg<OAuth2AccessToken> postAccessToken(Principal principal, @RequestParam
            Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
        OAuth2AccessToken accessToken = tokenEndpoint.postAccessToken(principal, parameters).getBody();
        return ResultMsg.resultSuccess(accessToken);
    }

    /**
     * 重写/oauth/check_token这个默认接口，用于校验令牌，返回的数据格式统一
     */
    @PostMapping(value = "/check_token")
    @ResponseBody
    public ResultMsg<Map<String,?>> checkToken(@RequestParam("token") String value)  {
        Map<String, ?> map = checkTokenEndpoint.checkToken(value);
        return ResultMsg.resultSuccess(map);
    }

    @ApiOperation(value = "注销")
    @PostMapping("/logout")
    @ResponseBody
    public ResultMsg<Void> logout(){
        LoginVal loginVal = OauthUtils.getCurrentUser();
        //这个jti放入redis中，并且过期时间设置为token的过期时间
        stringRedisTemplate.opsForValue().set(OAuthConstant.JTI_KEY_PREFIX + OauthUtils.getCurrentUser().getJti(),"",loginVal.getExpireIn(), TimeUnit.SECONDS);
        return ResultMsg.resultSuccess();
    }

    @ApiOperation(value = "表单登录跳转页面")
    @GetMapping("/login")
    public String loginPage(Model model){
        //返回跳转页面
        return "oauth-login";
    }

    @ApiOperation(value = "处理授权异常的跳转页面")
    @GetMapping("/error")
    public String error(Model model){
        return "oauth-error";
    }



    @ApiOperation(value = "社交登录绑定/注册用户")
    @PostMapping("/bind")
    @ResponseBody
    public ResultMsg<Void> bind(@Valid SysBindReq bindReq){
        bindReq.setPassword(passwordEncoder.encode(bindReq.getPassword()));
        return userFeign.bind(bindReq);
    }


    /********************************************下面的@ExceptionHandler对/oauth/token这个接口的异常进行捕获**********************************************************************/
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public ResponseEntity<ResultMsg> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) throws Exception {
        if (log.isInfoEnabled()) {
            log.info("Handling error: " + e.getClass().getSimpleName() + ", " + e.getMessage());
        }
        return translate.translate(e);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ResultMsg> handleException(Exception e) throws Exception {
        if (log.isWarnEnabled()) {
            log.warn("Handling error: " + e.getClass().getSimpleName() + ", " + e.getMessage());
        }
        return translate.translate(e);
    }

    @ExceptionHandler(ClientRegistrationException.class)
    @ResponseBody
    public ResponseEntity<ResultMsg> handleClientRegistrationException(Exception e) throws Exception {
        if (log.isWarnEnabled()) {
            log.warn("Handling error: " + e.getClass().getSimpleName() + ", " + e.getMessage());
        }
        return translate.translate(new BadClientCredentialsException());
    }

    @ExceptionHandler(OAuth2Exception.class)
    @ResponseBody
    public ResponseEntity<ResultMsg> handleException(OAuth2Exception e) throws Exception {
        if (log.isWarnEnabled()) {
            log.warn("Handling error: " + e.getClass().getSimpleName() + ", " + e.getMessage());
        }
        return translate.translate(e);
    }

    @Override
    public void afterPropertiesSet() {
        this.translate=new OAuthServerWebResponseExceptionTranslator();
    }
}
