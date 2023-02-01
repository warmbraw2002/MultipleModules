package com.tiktok;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 统一返回对象
 */

@Data
@AllArgsConstructor
public class Result<T> implements Serializable {
    /**
     * 通信数据
     */
    private T data;
    /**
     * 通信状态
     */
    private boolean flag = true;
    /**
     * 通信描述
     */
    private String msg = "操作成功";

    private String code = "200";

    /**
     * 通过静态方法获取实例
     */
    public static <T> Result<T> of(T data) {
        return new Result<>(data);
    }

    public static <T> Result<T> of(T data, String msg) {
        return new Result<>(data, msg);
    }

    public static <T> Result<T> of(String code, boolean flag) {
        return new Result<>(code, flag);
    }

    public static <T> Result<T> of(String code, boolean flag, String msg) {
        return new Result<>(code, flag, msg);
    }

    public static <T> Result<T> error(ErrorEnum errorEnum) {
        return new Result(errorEnum.getCode(), false, errorEnum.getMsg());
    }

    @Deprecated
    public Result() {

    }

    private Result(T data) {
        this.data = data;
    }

    private Result(String code, boolean flag) {
        this.code = code;
        this.flag = flag;
    }

    private Result(T data, String msg) {
        this.data = data;
        this.msg = msg;
    }

    private Result(String code, boolean flag, String msg) {
        this.code = code;
        this.flag = flag;
        this.msg = msg;
    }

}