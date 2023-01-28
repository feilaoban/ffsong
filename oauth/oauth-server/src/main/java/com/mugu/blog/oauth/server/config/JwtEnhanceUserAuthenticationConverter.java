package com.mugu.blog.oauth.server.config;

import com.mugu.blog.core.model.oauth.OAuthConstant;
import com.mugu.blog.oauth.server.model.SecurityUser;
import com.mugu.blog.oauth.server.model.TokenConstant;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Map;

/**
 * @author 公众号：码猿技术专栏
 * 从map中提提取用户信息
 */
public class JwtEnhanceUserAuthenticationConverter extends DefaultUserAuthenticationConverter {

    /**
     * 重写抽取用户数据方法
     */
    @Override
    public Authentication extractAuthentication(Map<String, ?> map) {
        if (map.containsKey(USERNAME)) {
            Collection<? extends GrantedAuthority> authorities = getAuthorities(map);
            String username = map.get(USERNAME).toString();
            String userId = map.get(OAuthConstant.USER_ID).toString();
            Integer gender= (Integer) map.get(OAuthConstant.GENDER);
            String nickName=map.get(OAuthConstant.NICK_NAME).toString();
            String avatar=map.get(OAuthConstant.AVATAR).toString();
            String mobile=map.get(OAuthConstant.MOBILE).toString();
            String email=map.get(OAuthConstant.EMAIL).toString();
            SecurityUser user = SecurityUser.builder()
                    .username(username)
                    .userId(userId)
                    .gender(gender)
                    .nickname(nickName)
                    .avatar(avatar)
                    .mobile(mobile)
                    .email(email)
                    .authorities(authorities)
                    .build();
            return new UsernamePasswordAuthenticationToken(user, "", authorities);
        }
        return null;
    }

    /**
     * 提取权限
     */
    private Collection<? extends GrantedAuthority> getAuthorities(Map<String, ?> map) {
        Object authorities = map.get(AUTHORITIES);
        if (authorities instanceof String) {
            return AuthorityUtils.commaSeparatedStringToAuthorityList((String) authorities);
        }
        if (authorities instanceof Collection) {
            return AuthorityUtils.commaSeparatedStringToAuthorityList(StringUtils
                    .collectionToCommaDelimitedString((Collection<?>) authorities));
        }
        throw new IllegalArgumentException("Authorities must be either a String or a Collection");
    }

}