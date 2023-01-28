package com.mugu.blog.core.utils;

import com.mugu.blog.core.model.LoginVal;
import com.mugu.blog.core.model.RequestConstant;

import java.util.Objects;

/**
 * 从Request中获取用户身份信息
 */
public class OauthUtils {
    public static LoginVal getCurrentUser(){
        return (LoginVal) Objects.requireNonNull(RequestContextUtils.getRequest().getAttribute(RequestConstant.LOGIN_VAL_ATTRIBUTE));
    }
}
