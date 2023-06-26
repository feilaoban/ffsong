package handler;

import cn.hutool.http.HttpStatus;
import com.alibaba.fastjson.JSON;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import pojo.ErrorCode;
import pojo.R;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 认证失败处理器
 * @author gaozhenyu
 */
@Slf4j
@Component
public class AuthExceptionEntryPointHandler implements AuthenticationEntryPoint {

	@Override
	@SneakyThrows
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws ServletException {
		response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
		response.setStatus(HttpStatus.HTTP_UNAUTHORIZED);
		String requestUri = request.getRequestURI();
		log.error("{}, 请求路径:{}, 原因是: {}", ErrorCode.EX_401.getMessage(), requestUri, authException.getMessage());
		response.getWriter().write(JSON.toJSONString(
			R.normal(ErrorCode.EX_401.getCode(), authException.getMessage())));
	}
}
