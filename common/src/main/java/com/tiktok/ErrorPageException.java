package com.tiktok;

/**
 * 自定义错误页面异常
 */
public class ErrorPageException extends ServiceException {

    public ErrorPageException(String code,String msg) {
        super(code, msg);
    }
}