package pojo.constant;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @Author songfeifei
 * @Date 2023/6/21 16:55
 * @Description
 */
public class CommonConstants {
    /**
     * jwks的kid
     */
    public static final String JWKS_KID = "ffsong_2021";

    /**
     * jwks端点
     */
    public static final String JWKS_ENDPOINT = "/.well-known/jwks.json";

    /**
     * jwks私钥名称
     */
    public static final String JWKS_JKS = "ffsong_jwt.jks";

    /**
     * jwks存储文件的密码
     */
    public static final String JWKS_STORE_PWD = "ffsong_su";

    /**
     * jwks的alias
     */
    public static final String JWKS_ALIAS = "ffsong_oauth_pair";

    /**
     * 内置路由白名单
     */
    public static final List<String> SWAGGER_WHITES = Lists.newArrayList(
            "/actuator/**", "/v2/api-docs", "/v3/api-docs", "/swagger-resources/configuration/ui",
            "/swagger-resources", "/swagger-resources/configuration/security", "/swagger-ui.html", "/swagger-ui/**",
            "/webjars/**", "/js/**", "/css/**", "/img/**", "/static/**", "/favicon.ico");
}
