package com.fc.common.utils.resp;

/**
 * @author tczjh
 * @version 1.0
 * @className Result
 * @description 结果封装对象
 * @date 2023/12/27 13:39
 */
public class Result<T> {

    private final Integer code;

    private final String message;

    private boolean success;

    private T data;

    private Result(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.message = resultEnum.getMessage();
    }

    private Result(ResultEnum resultEnum, T data) {
        this.code = resultEnum.getCode();
        this.message = resultEnum.getMessage();
        this.data = data;
    }

    public static <T> Result<T> ofFail(ResultEnum resultEnum) {
        Result<T> result = new Result<>(resultEnum);
        result.success = false;
        return result;
    }

    public static <T> Result<T> ofSuccess(T data) {
        Result<T> result = new Result<>(ResultEnum.SUCCESS, data);
        result.success = true;
        return result;
    }

    public static <T> Result<T> ofSuccess(ResultEnum resultEnum, T data) {
        Result<T> result = new Result<>(resultEnum, data);
        result.success = true;
        return result;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }

    public T getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", success=" + success +
                ", data=" + data +
                '}';
    }

}
