package com.chy.util;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * ClassName: Result
 * Package: com.golden.common
 *
 * @Author: Golden
 * @Create: 2024/6/6
 * Description:
 */
@Data
@AllArgsConstructor
public class Result<T> {
    private int code;
    private String message;
    private T data;

    public Result(ResultEnum resultEnum, T data) {
        this.code = resultEnum.getCode();
        this.message = resultEnum.getMessage();
        this.data = data;
    }

    public Result(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.message = resultEnum.getMessage();
    }

    public Result(int code, String message) {
        this.code = code;
        this.message = message;
    }

    //快速构造成功的结果
    public static <T> Result<T> success(T data) {
        return new Result<>(ResultEnum.SUCCESS, data);
    }

    //快速构造失败的结果
    public static <T> Result<T> fail(ResultEnum resultEnum) {
        return new Result<>(resultEnum);
    }

    public static <T> Result<T> fail(int code,String message) {
        return new Result<>(code, message, null);
    }

    public static <T> Result<T> fail(String message) {
        return new Result<>(500, message);
    }
}
