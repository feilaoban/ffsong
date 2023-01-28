package com.mugu.blog.core.utils;

import lombok.SneakyThrows;
import org.springframework.util.DigestUtils;

import java.io.InputStream;


public class MD5Utils {

    /**
     * 获取文件的MD5
     * @param inputStream
     * @return
     */
    @SneakyThrows
    public static String getFileMd5(InputStream inputStream){
        return DigestUtils.md5DigestAsHex(inputStream);
    }
}
