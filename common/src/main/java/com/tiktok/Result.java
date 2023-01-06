package com.tiktok;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

//之后任务：
//公共模块common配置（各模块共用）
//1、自定义全局异常类实现（用来进行全局的异常处理）
//2、自定义通用返回类。（类中至少包含 状态码 返回数据 是否处理成功，用于后端返还数据前端）
//3、工具包utils创建（工具类可上网查询，包含Rsa、Security、字符串处理、分页、文件、redis等等工具类）
//4、配置包config创建（配置信息可以上网查询，包含redis、swagger、文件等配置）
//拓展：通过注解作为切入点的AOP限流配置（60s内最多访问10次接口，需要结合redis一起使用）
@Data
@NoArgsConstructor
public class Result<T> implements Serializable {

    /**
     * 状态码
     */
    private int code;

    /**
     * 返回的数据主体（返回的内容）
     */
    private T data;

    /**
     * 是否成功
     */
    private Boolean success;

    /**
     * 设定结果为成功
     *
     * @param msg  消息
     * @param data 数据体
     */
    public void setResultSuccess(String msg, T data) {
        this.success = true;
        this.code = 200;
        this.data = data;
    }

    /**
     * 设定结果为失败
     *
     * @param msg 消息
     */
    public void setResultFailed(String msg) {
        this.success = false;
        this.code = 404;
        this.data = null;
    }
}
