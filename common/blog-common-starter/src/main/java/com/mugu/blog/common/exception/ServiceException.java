package com.mugu.blog.common.exception;

import lombok.Data;

@Data
public class ServiceException extends RuntimeException {
    private Integer code;
    public ServiceException(Integer code,String message) {
        super(message);
        this.code=code;
    }
}
