package com.mugu.blog.core.model;

/**
 * @author 公众号：码猿技术专栏
 * 响应码、提示信息
 */
public enum ResultCode {
    CLIENT_AUTHENTICATION_FAILED(1001,"客户端认证失败"),
    USERNAME_OR_PASSWORD_ERROR(1002,"用户名或密码错误"),
    UNSUPPORTED_GRANT_TYPE(1003, "不支持的认证模式"),
    NO_PERMISSION(1005,"无权限访问！"),
    UNAUTHORIZED(401, "系统错误"),

    INVALID_TOKEN(1004,"无效的token"),
    REQUEST_SUCCESS(200,"请求成功！"),

    SERVICE_EXCEPTION(2000,"业务处理异常！"),
    PARAMETER_FAIL(2001,"参数错误！"),
    ARTICLE_NOT_EXIST(2002,"文章不存在！"),
    ARTICLE_NO_PAGE(2003,"别再翻了，没有文章了！"),
    ARTICLE_NO_SEARCH(2004,"不好意思，没有你想要的结果o(╥﹏╥)o"),
    COMMENT_NOT_EXIST(2005,"回复的评论不存在！"),

    SERVER_FALLBACK(3001,"服务降级了........"),

    SERVER_LIMIT(3002,"网络拥挤，请稍后再试..."),

    NO_REPEATABLE_SUBMIT(3002,"请勿重复提交........"),

    FILE_MUCH_MAX(4001,"文件过大，请压缩后再上传！");



    private final int code;

    private final String msg;

    private ResultCode(int code,String msg){
        this.code=code;
        this.msg=msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
