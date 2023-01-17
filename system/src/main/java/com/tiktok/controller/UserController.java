package com.tiktok.controller;


import com.tiktok.ErrorEnum;
import com.tiktok.OperationLogType;
import com.tiktok.Result;
import com.tiktok.ServiceException;
import com.tiktok.annotation.Log;
import com.tiktok.domain.User;
import com.tiktok.service.JwtAuthService;
import com.tiktok.utils.AgentUtil;
import com.tiktok.utils.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RestController
@RequestMapping("/user")
@Api(tags = "用户操作")
public class UserController {

    @Resource
    private RedisUtil redisUtil;

    @Autowired
    private JwtAuthService jwtAuthService;

    @PostMapping("/login/{code}")
    @Log(operModule = "用户操作-登录",operType = OperationLogType.LOGIN,operDesc = "用户登录")
    @ApiOperation(value = "用户登录")
    public Result login(HttpServletRequest request, @RequestBody User user, @PathVariable String code) {
        String key = getCaptchaKey(request);
        String captcha = (String) redisUtil.get(key);
        if (captcha == null) {
            throw new ServiceException(ErrorEnum.CAPTCHA_EXPIRED);
        }
        if (!captcha.equals(code.toLowerCase())) {
            return Result.of("验证码错误");
        }
        String token = jwtAuthService.login(user.getUsername(), user.getPassword());
        String redisKey = user.getUsername()+":token";
        if (redisUtil.hasKey(redisKey)) {
            redisUtil.del(redisKey);
        }
        redisUtil.set(user.getUsername()+":token", token, 240);
        return Result.of(token);
    }

    @GetMapping("/username")
    @PreAuthorize("hasRole('ROLE_USER')")
    @ApiOperation(value = "获取用户名")
    @Log(operModule = "用户操作-获取用户名",operType = OperationLogType.QUERY,operDesc = "获取用户名")
    public String currentUserName(Principal principal) {
        return principal.getName();
    }

    @GetMapping("/logout")
    @Log(operModule = "用户操作-登出",operType = OperationLogType.LOGOUT,operDesc = "用户登出")
    @PreAuthorize("hasRole('ROLE_USER')")
    @ApiOperation(value = "用户登出")
    public Result logout(HttpServletRequest request, Principal principal) {
        String token = request.getHeader("Authorization");
        String redisKey = principal.getName()+":token";
        if (redisUtil.hasKey(redisKey)) {
            redisUtil.del(redisKey);
        }
        return Result.of("登出成功");
    }

//    @PostMapping("/logintest/{username}/{password}")
//    //@Log(operModule = "用户操作-登录",operType = OperationLogType.LOGIN,operDesc = "用户登录")
//    public Result login(HttpServletRequest request, @PathVariable String username, @PathVariable String password) {
//        //String key = getCaptchaKey(request);
//        //String captcha = (String) redisUtil.get(key);
//        String token = jwtAuthService.login(username, password);
//        String redisKey = username+":token";
//        if (redisUtil.hasKey(redisKey)) {
//            redisUtil.del(redisKey);
//        }
//        redisUtil.set(redisKey, token, 240);
//        return Result.of(token);
//    }

//    @GetMapping
//    public String test() {
//        return "test";
//    }

    private String getCaptchaKey(HttpServletRequest request) {
        String ip = AgentUtil.getIpAddr(request);
        String userAgent = request.getHeader("User-Agent");
        String key = "user-service:captcha:" + DigestUtils.md5DigestAsHex((ip+userAgent).getBytes());
        return key;
    }
}