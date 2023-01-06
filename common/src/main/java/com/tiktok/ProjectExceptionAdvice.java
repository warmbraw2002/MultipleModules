package com.tiktok;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProjectExceptionAdvice {
    @ExceptionHandler(Exception.class)
    public Result<String> doException(Exception e){
        Result<String> result = new Result<>();
        e.printStackTrace();
        result.setResultFailed("服务器故障，请稍后再试");
        return result;
    }
}