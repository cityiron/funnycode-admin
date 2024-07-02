package com.fc.common.utils.resp;

/**
 * @author tczjh
 * @version 1.0
 * @className ResultEnum
 * @description 结果枚举
 * @date 2023/12/27 13:41
 */
public enum ResultEnum {

    SUCCESS(200, "成功"),

    NO_PERMISSION(403, "权限不足"),

    NOT_FOUND(404, "资源不存在"),

    NOT_ALLOW(4101, "不允许的操作"),

    SERVER_ERROR(500, "服务器错误");

    private final Integer code;

    private final String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ResultEnum{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }

}
