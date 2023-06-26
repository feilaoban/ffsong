package pojo;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author songfeifei
 * @Date 2023/6/21 16:37
 * @Description
 */
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class R<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private int success;

    @Getter
    @Setter
    private String msg;

    @Getter
    @Setter
    private T data;

    @Getter
    @Setter
    private String requestId;

    public static <T> R<T> ok() {
        return restResult(null, OperaEnum.SUCCESS.getCode(), OperaEnum.SUCCESS.getMsg(), null);
    }

    public static <T> R<T> ok(T data) {
        return restResult(data, OperaEnum.SUCCESS.getCode(), OperaEnum.SUCCESS.getMsg(), null);
    }

    public static <T> R<T> okWithRequestId(T data, String requestId) {
        return restResult(data, OperaEnum.SUCCESS.getCode(), OperaEnum.SUCCESS.getMsg(), requestId);
    }

    public static <T> R<T> ok(T data, String msg) {
        return restResult(data, OperaEnum.SUCCESS.getCode(), msg, null);
    }

    public static <T> R<T> failed() {
        return restResult(null, OperaEnum.FAIL.getCode(), OperaEnum.FAIL.getMsg(), null);
    }

    public static <T> R<T> failed(String msg) {
        return restResult(null, OperaEnum.FAIL.getCode(), msg, null);
    }

    public static <T> R<T> failed(T data) {
        return restResult(data, OperaEnum.FAIL.getCode(), OperaEnum.FAIL.getMsg(), null);
    }

    public static <T> R<T> failed(T data, String msg) {
        return restResult(data, OperaEnum.FAIL.getCode(), msg, null);
    }

    public static <T> R<T> failed(T data, String msg, String requestId) {
        return restResult(data, OperaEnum.FAIL.getCode(), msg, requestId);
    }

    public static <T> R<T> failed(T data, int code, String msg, String requestId) {
        return restResult(data, code, msg, requestId);
    }

    public static <T> R<T> failed(T data, int code, String msg) {
        return restResult(data, code, msg, null);
    }

    public static <T> R<T> failed(OperaEnum operaEnum, String msg) {
        return restResult(null, operaEnum.getCode(), msg, null);
    }

    public static <T> R<T> failed(OperaEnum operaEnum) {
        return failedWithRequestId(operaEnum, null);
    }

    public static <T> R<T> failedWithRequestId(OperaEnum operaEnum, String requestId) {
        return restResult(null, operaEnum.getCode(), operaEnum.getMsg(), requestId);
    }

    public static <T> R<T> normal(Integer code, String msg) {
        return restResult(null, code, msg, null);
    }

    public static <T> R<T> normal(OperaEnum operaEnum) {
        return restResult(null, operaEnum.getCode(), operaEnum.getMsg(), null);
    }

    public static <T> R<T> normal(OperaEnum operaEnum, String requestId) {
        return restResult(null, operaEnum.getCode(), operaEnum.getMsg(), requestId);
    }

    private static <T> R<T> restResult(T data, int code, String msg, String requestId) {
        R<T> apiResult = new R<>();
        apiResult.setSuccess(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        apiResult.setRequestId(requestId);
        return apiResult;
    }
}
