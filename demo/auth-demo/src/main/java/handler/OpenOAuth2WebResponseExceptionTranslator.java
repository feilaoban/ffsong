package handler;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import pojo.ErrorCode;
import pojo.R;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Objects;

/**
 * 	自定义oauth2异常提示
 */
@Slf4j
public class OpenOAuth2WebResponseExceptionTranslator implements WebResponseExceptionTranslator {
    @Override
    public ResponseEntity translate(Exception e) {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        HashMap<String, Object> responseData = resolveException(e);
        return ResponseEntity.status((Integer)responseData.get("httpStatus")).body(responseData.get("R"));
    }
    
    public static HashMap<String, Object> resolveException(Exception ex) {
        ErrorCode code = ErrorCode.ERROR;
        int httpStatus = HttpStatus.INTERNAL_SERVER_ERROR.value();
        String message = ex.getMessage();
        String className = ex.getClass().getName();
        if (className.contains("UsernameNotFoundException")) {
            httpStatus = HttpStatus.UNAUTHORIZED.value();
            code = ErrorCode.USERNAME_NOT_FOUND;
        } else if (className.contains("BadCredentialsException")) {
            httpStatus = HttpStatus.UNAUTHORIZED.value();
            code = ErrorCode.BAD_CREDENTIALS;
        } else if (className.contains("AccountExpiredException")) {
            httpStatus = HttpStatus.UNAUTHORIZED.value();
            code = ErrorCode.ACCOUNT_EXPIRED;
        } else if (className.contains("LockedException")) {
            httpStatus = HttpStatus.UNAUTHORIZED.value();
            code = ErrorCode.ACCOUNT_LOCKED;
        } else if (className.contains("DisabledException")) {
            httpStatus = HttpStatus.UNAUTHORIZED.value();
            code = ErrorCode.ACCOUNT_DISABLED;
        } else if (className.contains("CredentialsExpiredException")) {
            httpStatus = HttpStatus.UNAUTHORIZED.value();
            code = ErrorCode.CREDENTIALS_EXPIRED;
        } else if (className.contains("InvalidClientException")) {
            httpStatus = HttpStatus.UNAUTHORIZED.value();
            code = ErrorCode.INVALID_CLIENT;
        } else if (className.contains("UnauthorizedClientException")) {
            httpStatus = HttpStatus.UNAUTHORIZED.value();
            code = ErrorCode.UNAUTHORIZED_CLIENT;
        } else if (className.contains("InsufficientAuthenticationException") || className.contains("AuthenticationCredentialsNotFoundException")) {
            httpStatus = HttpStatus.UNAUTHORIZED.value();
            code = ErrorCode.UNAUTHORIZED;
        } else if (className.contains("InvalidGrantException")) {
            code = ErrorCode.ALERT;
            if ("Bad credentials".contains(message)) {
                code = ErrorCode.BAD_CREDENTIALS;
            } else if ("User is disabled".contains(message)) {
                code = ErrorCode.ACCOUNT_DISABLED;
            } else if ("User account is locked".contains(message)) {
                code = ErrorCode.ACCOUNT_LOCKED;
            }
        } else if (className.contains("InvalidScopeException")) {
            httpStatus = HttpStatus.UNAUTHORIZED.value();
            code = ErrorCode.INVALID_SCOPE;
        } else if (className.contains("InvalidTokenException")) {
            httpStatus = HttpStatus.UNAUTHORIZED.value();
            code = ErrorCode.INVALID_TOKEN;
        } else if (className.contains("InvalidRequestException")) {
            httpStatus = HttpStatus.BAD_REQUEST.value();
            code = ErrorCode.INVALID_REQUEST;
        } else if (className.contains("RedirectMismatchException")) {
            code = ErrorCode.REDIRECT_URI_MISMATCH;
        } else if (className.contains("UnsupportedGrantTypeException")) {
            code = ErrorCode.UNSUPPORTED_GRANT_TYPE;
        } else if (className.contains("UnsupportedResponseTypeException")) {
            code = ErrorCode.UNSUPPORTED_RESPONSE_TYPE;
        } else if (className.contains("UserDeniedAuthorizationException")) {
            code = ErrorCode.ACCESS_DENIED;
        } else if (className.contains("AccessDeniedException")) {
            code = ErrorCode.ACCESS_DENIED;
            httpStatus = HttpStatus.FORBIDDEN.value();
            if (ErrorCode.ACCESS_DENIED_BLACK_LIMITED.getMessage().contains(message)) {
                code = ErrorCode.ACCESS_DENIED_BLACK_LIMITED;
            } else if (ErrorCode.ACCESS_DENIED_WHITE_LIMITED.getMessage().contains(message)) {
                code = ErrorCode.ACCESS_DENIED_WHITE_LIMITED;
            } else if (ErrorCode.ACCESS_DENIED_AUTHORITY_EXPIRED.getMessage().contains(message)) {
                code = ErrorCode.ACCESS_DENIED_AUTHORITY_EXPIRED;
            } else if (ErrorCode.ACCESS_DENIED_UPDATING.getMessage().contains(message)) {
                code = ErrorCode.ACCESS_DENIED_UPDATING;
            } else if (ErrorCode.ACCESS_DENIED_DISABLED.getMessage().contains(message)) {
                code = ErrorCode.ACCESS_DENIED_DISABLED;
            } else if (ErrorCode.ACCESS_DENIED_NOT_OPEN.getMessage().contains(message)) {
                code = ErrorCode.ACCESS_DENIED_NOT_OPEN;
            }
        } else if (className.contains("HttpMessageNotReadableException")
                || className.contains("TypeMismatchException")
                || className.contains("MissingServletRequestParameterException")) {
            httpStatus = HttpStatus.BAD_REQUEST.value();
            code = ErrorCode.BAD_REQUEST;
        } else if (className.contains("NoHandlerFoundException")) {
            httpStatus = HttpStatus.NOT_FOUND.value();
            code = ErrorCode.NOT_FOUND;
        } else if (className.contains("HttpRequestMethodNotSupportedException")) {
            httpStatus = HttpStatus.METHOD_NOT_ALLOWED.value();
            code = ErrorCode.METHOD_NOT_ALLOWED;
        } else if (className.contains("HttpMediaTypeNotAcceptableException")) {
            httpStatus = HttpStatus.BAD_REQUEST.value();
            code = ErrorCode.MEDIA_TYPE_NOT_ACCEPTABLE;
        } else if (className.contains("MethodArgumentNotValidException")) {
            BindingResult bindingResult = ((MethodArgumentNotValidException) ex).getBindingResult();
            code = ErrorCode.ALERT;
            HashMap<String, Object> resultMap = Maps.newHashMap();
            resultMap.put("httpStatus", httpStatus);
            resultMap.put("R", R.normal(code.getCode(), bindingResult.getFieldError().getDefaultMessage()));
            return resultMap;
        } else if (className.contains("IllegalArgumentException")) {
            //参数错误
            code = ErrorCode.ALERT;
            httpStatus = HttpStatus.BAD_REQUEST.value();
        } else if (className.contains("OpenAlertException")) {
            httpStatus = HttpStatus.OK.value();
            code = ErrorCode.ALERT;
        } else if (className.contains("OpenSignatureException")) {
            httpStatus = HttpStatus.BAD_REQUEST.value();
            code = ErrorCode.SIGNATURE_DENIED;
        } else if (message.equalsIgnoreCase(ErrorCode.TOO_MANY_REQUESTS.name())) {
            code = ErrorCode.TOO_MANY_REQUESTS;
        }
        return buildBody(ex, code, httpStatus);
    }
    
    /**
     * 构建返回结果对象
     *
     * @param exception
     * @return
     */
    private static HashMap<String, Object> buildBody(Exception exception, ErrorCode resultCode, int httpStatus) {
    	HashMap<String, Object> resultMap = Maps.newHashMap();
        if (resultCode == null) {
            resultCode = ErrorCode.ERROR;
        }
        log.error("==> error:{} exception: {} httpStatus: {}", resultCode, exception, httpStatus);
        resultMap.put("httpStatus", httpStatus);
        resultMap.put("R", R.normal(resultCode.getCode(), exception.getMessage()));
        return resultMap;
    }
}
