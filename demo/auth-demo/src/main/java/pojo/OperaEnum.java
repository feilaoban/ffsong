package pojo;

import lombok.Getter;

/**
 * @Author songfeifei
 * @Date 2023/6/21 16:37
 * @Description
 */
@Getter
public enum OperaEnum {
    /* -------------------------------------- 基础操作码 -------------------------------------- */
    SUCCESS(1000, "success"),
    FAIL(2000, "fail"),
    EX_401(401, "认证失败"),
    EX_403(403, "无权访问"),
    EX_404(404, "未登录公司"),
    EX_500(500, "系统异常"),
    EX_503(500, "服务实例未发现"),
    EX_503_1(500, "服务实例未发现，等待服务列表刷新"),
    EX_504(500, "网关请求超时"),

    /* -------------------------------------- 系统异常码 -------------------------------------- */
    EX_1001(FAIL.getCode(), "参数出问题咯"),
    EX_1002(FAIL.getCode(), "全局用户不存在, 请检查是否在白名单中使用了contextUtil"),
    EX_1003(FAIL.getCode(), "微信配置出错"),
    EX_1004(FAIL.getCode(), "唯一性校验出错"),
    EX_1005(FAIL.getCode(), "当前token和全局用户类型不一致"),
    EX_1100(FAIL.getCode(), "服务调用失败"),
    //EX_1101(FAIL.getCode(), String.format("%s服务调用失败", ServiceNameConstants.OPERATION_DES)),
    //EX_1102(FAIL.getCode(), String.format("%s服务调用失败", ServiceNameConstants.SMS_DES)),
    //EX_1103(FAIL.getCode(), String.format("%s服务调用失败", ServiceNameConstants.USER_DES)),


    /* -------------------------------------- 业务异常码 -------------------------------------- */

    // toB管理后台
    EX_1900(FAIL.getCode(), "商户不存在或审核未通过"),


    // gateway网关服务异常 2000~2099
    EX_2000(FAIL.getCode(), ""),

    // auth认证服务异常 2100~2199
    EX_2100(FAIL.getCode(), "请登录..."),
    EX_2101(FAIL.getCode(), "密码错误"),
    EX_2102(FAIL.getCode(), "账号不存在"),
    EX_2103(FAIL.getCode(), "用户名已存在"),
    EX_2104(FAIL.getCode(), "当前用户暂无该权限"),
    EX_2105(FAIL.getCode(), "手机号已存在"),
    EX_2106(FAIL.getCode(), "用户名不可以为空"),
    EX_2107(FAIL.getCode(), "用户已停用"),
    EX_2108(FAIL.getCode(), "token解析出错"),
    EX_2109(FAIL.getCode(), "clientId或者clientSecret错误"),
    EX_2110(FAIL.getCode(), "不支持的授权方式response_type"),

    // 基础服务异常 2200~2299
    EX_2200(2200, ""),

    // sms短信中心服务异常 2400~2499
    EX_2400(2400, "发送编码不存在"),
    EX_2401(2401, "没有找到对应的发送配置，请手动添加"),
    EX_2402(2402, "通道类型策略未配置，请在数据库配置"),
    EX_2403(2403, "该通道不存在，请在数据库配置"),
    EX_2404(2404, "该签名不存在或审核不成功"),
    EX_2405(2405, "该模板不存在或审核不成功"),
    EX_2406(2406, "content字段不符合json规范"),
    EX_2407(2407, "验证码类型的content入参只能有一个key"),
    EX_2408(2408, "验证码类型只能有一个手机号"),
    EX_2409(2409, "验证码类型的入参content字段不能为空"),
    EX_2410(2410, "产品不可用或者不存在"),
    EX_2411(2411, "该签名绑定的商户不存在或审核未通过"),
    EX_2412(2412, "三方通道异常"),
    EX_2413(2413, "手机号参数格式错误"),
    EX_2414(2414, "手机验证码错误"),

    // user用户中心服务异常2500～2599
    EX_2501(2501, "应用不存在或已删除"),
    EX_2502(2502, "用户池已删除"),
    EX_2503(2503, "应用不可用"),
    EX_2504(2504, "用户池不存在"),

    ;

    private final Integer code;
    private final String msg;

    OperaEnum(Integer code, String msg) {
        this.msg = msg;
        this.code = code;
    }
}
