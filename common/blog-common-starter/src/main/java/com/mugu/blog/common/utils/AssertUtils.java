package com.mugu.blog.common.utils;

import com.mugu.blog.common.exception.ServiceException;
import com.mugu.blog.core.model.ResultCode;

public class AssertUtils {

    public static void assertTrue(boolean flag){
        if (!flag)
            throw new ServiceException(ResultCode.SERVICE_EXCEPTION.getCode(),ResultCode.SERVICE_EXCEPTION.getMsg());
    }

    public static void assertTrue(boolean flag,ResultCode resultCode){
        if (!flag)
            throw new ServiceException(resultCode.getCode(),resultCode.getMsg());
    }

    public static void assertTrue(boolean flag,Integer code,String msg){
        if (!flag)
            throw new ServiceException(code,msg);
    }
}
