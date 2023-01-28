package com.mugu.blog.common.exception;

import com.mugu.blog.core.model.ResultCode;
import com.mugu.blog.core.model.ResultMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResultMsg<Void> handler(Exception ex){
        if (ex instanceof MethodArgumentNotValidException){
            log.error("参数错误：{}",ex.getMessage());
            return ResultMsg.<Void>resultFail(ResultCode.PARAMETER_FAIL.getCode(),ResultCode.PARAMETER_FAIL.getMsg());
        }

        if (ex instanceof ServiceException){
            ServiceException serviceException=(ServiceException) ex;
            log.error("业务处理异常：{}",ex.getMessage());
            return ResultMsg.<Void>resultFail(serviceException.getCode(),serviceException.getMessage());
        }

        log.error("系统出现了异常：{}",ex.getMessage());
        return ResultMsg.<Void>resultFail();
    }
}
