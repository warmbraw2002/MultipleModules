package com.tiktok.controller;


import com.tiktok.OperationLogType;
import com.tiktok.Result;
import com.tiktok.annotation.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
/**
 * @author az
 * @since 2021-12-03
 */
@RestController
@RequestMapping("/user")
public class UserController {
 
 
    @GetMapping("/queryAllUser")
    @Log(operModule = "用户管理-查询用户列表",operType = OperationLogType.QUERY,operDesc = "查询所有用户")
    public Object queryAllUser(){
        return Result.of("查询所有用户");
    }
 
}