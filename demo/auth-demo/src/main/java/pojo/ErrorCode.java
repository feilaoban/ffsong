package pojo;

/**
 * 自定义返回码
 *
 * @author admin
 */

public enum ErrorCode {
    /**
     * 成功
     */
    OK(1000, "success"),
    FAIL(2000, "fail"),
    ALERT(2001, "alert"),
    
    EX_401(401, "登录过期，请重新登录"),
	EX_403(403, "无权访问"),
	ERROR(500, "系统异常"),
	
    /**
     * 系统错误
     */
	EX_1001(1001, "参数出问题咯"),

    /**
     * oauth2返回码
     */
    INVALID_TOKEN(2000, "invalid_token"),
    INVALID_SCOPE(2001, "invalid_scope"),
    INVALID_REQUEST(2002, "invalid_request"),
    INVALID_CLIENT(2003, "invalid_client"),
    INVALID_GRANT(2004, "invalid_grant"),
    REDIRECT_URI_MISMATCH(2005, "redirect_uri_mismatch"),
    UNAUTHORIZED_CLIENT(2006, "unauthorized_client"),
    EXPIRED_TOKEN(2007, "expired_token"),
    UNSUPPORTED_GRANT_TYPE(2008, "unsupported_grant_type"),
    UNSUPPORTED_RESPONSE_TYPE(2009, "unsupported_response_type"),
    UNAUTHORIZED(2012, "unauthorized"),
    SIGNATURE_DENIED(2013, "signature_denied"),

    ACCESS_DENIED(4030, "access_denied"),
    ACCESS_DENIED_BLACK_LIMITED(4031, "access_denied_black_limited"),
    ACCESS_DENIED_WHITE_LIMITED(4032, "access_denied_white_limited"),
    ACCESS_DENIED_AUTHORITY_EXPIRED(4033, "access_denied_authority_expired"),
    ACCESS_DENIED_UPDATING(4034, "access_denied_updating"),
    ACCESS_DENIED_DISABLED(4035, "access_denied_disabled"),
    ACCESS_DENIED_NOT_OPEN(4036, "access_denied_not_open"),
    /**
     * 账号错误
     */
    BAD_CREDENTIALS(3000, "bad_credentials"),
    ACCOUNT_DISABLED(3001, "account_disabled"),
    ACCOUNT_EXPIRED(3002, "account_expired"),
    CREDENTIALS_EXPIRED(3003, "credentials_expired"),
    ACCOUNT_LOCKED(3004, "account_locked"),
    USERNAME_NOT_FOUND(3005, "username_not_found"),

    /**
     * 请求错误
     */
    BAD_REQUEST(4000, "bad_request"),
    NOT_FOUND(4004, "not_found"),
    METHOD_NOT_ALLOWED(4005, "method_not_allowed"),
    MEDIA_TYPE_NOT_ACCEPTABLE(4006, "media_type_not_acceptable"),
    TOO_MANY_REQUESTS(4029, "too_many_requests"),
	
	EX_4001(4001, "threadLocal未获取到相关信息，引发原因为调用白名单接口却使用了ClientUtil"),

	;

    private int code;
    private String message;

    ErrorCode() {
    }

    private ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ErrorCode getResultEnum(int code) {
        for (ErrorCode type : ErrorCode.values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        return ERROR;
    }

    public static ErrorCode getResultEnum(String message) {
        for (ErrorCode type : ErrorCode.values()) {
            if (type.getMessage().equals(message)) {
                return type;
            }
        }
        return ERROR;
    }


    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
