package handler;


import cn.hutool.http.ContentType;
import cn.hutool.http.HttpStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.stereotype.Component;
import pojo.ErrorCode;
import pojo.R;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

/**
 * 授权失败处理器
 *
 * @author gaozhenyu
 */
@Slf4j
@AllArgsConstructor
@Component
public class AuthAccessDeniedHandler extends OAuth2AccessDeniedHandler {

    private final ObjectMapper objectMapper;

    @Override
    @SneakyThrows
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException authException) {
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType(ContentType.build(ContentType.JSON.getValue(), StandardCharsets.UTF_8));
        log.error(ErrorCode.EX_403.getMessage());
        response.setStatus(HttpStatus.HTTP_OK);
        PrintWriter printWriter = response.getWriter();
        printWriter.append(objectMapper.writeValueAsString(
                R.normal(ErrorCode.EX_403.getCode(), authException.getMessage())));
    }
}
