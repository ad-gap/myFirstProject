package com.chy.util;

/**
 * ClassName: ResultEnum
 * Package: com.golden.common
 *
 * @Author: Golden
 * @Create: 2024/6/6
 * Description:
 */
public enum ResultEnum {
    SUCCESS(200, "成功"),
    ERROR(500, "系统异常"),
    PARAM_ERROR(400, "参数错误"),
    NOT_FOUND(404, "资源未找到"),
    UNAUTHORIZED(401, "未授权"),
    FORBIDDEN(403, "禁止访问"),
    NOT_ALLOWED(405, "不允许的请求方法"),
    REQUEST_TIMEOUT(408, "请求超时"),
    CONFLICT(409, "资源冲突"),
    GONE(410, "资源已不存在"),
    OTHER_ERROR(500, "其他错误"),
    SERVER_ERROR(500, "服务器错误");

    private int code;
    private String message;

    ResultEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
