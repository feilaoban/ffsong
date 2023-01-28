package com.mugu.blog.core.model;

import lombok.Data;

import java.util.Objects;

@Data
public class ResultMsg<T> {
    private Integer code;

    private String msg;

    private T data;

    public static <T> ResultMsg<T> resultSuccess(){
        return new ResultMsg<>(ResultCode.REQUEST_SUCCESS.getCode(),ResultCode.REQUEST_SUCCESS.getMsg(),null);
    }

    public static ResultMsg resultFail(){
        return new ResultMsg<>(ResultCode.UNAUTHORIZED.getCode(),ResultCode.UNAUTHORIZED.getMsg(),null);
    }

    public static <T>  ResultMsg<T> resultFail(Integer code,String msg){
        return new ResultMsg<>(code,msg,null);
    }


    public static boolean isSuccess(ResultMsg<?> resultMsg){
        return Objects.nonNull(resultMsg)&&resultMsg.getCode().equals(ResultCode.REQUEST_SUCCESS.getCode());
    }

    public static <T> ResultMsg<T> resultSuccess(T data){
        return new ResultMsg(ResultCode.REQUEST_SUCCESS.getCode(),ResultCode.REQUEST_SUCCESS.getMsg(),data);
    }

    private ResultMsg(){}

    public ResultMsg(Integer code,String msg,T data){
        this.code=code;
        this.msg=msg;
        this.data=data;
    }


}
