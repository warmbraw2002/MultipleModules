package com.tiktok.controller;


import com.tiktok.*;
import com.tiktok.annotation.Log;
import com.tiktok.domain.User;
import com.tiktok.service.JwtAuthService;
import com.tiktok.service.UserService;
import com.tiktok.utils.AgentUtil;
import com.tiktok.utils.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.DigestUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.Principal;

@RestController
@RequestMapping("/user")
@Api(tags = "用户操作")
public class UserController {

    @Resource
    private RedisUtil redisUtil;

    @Autowired
    private JwtAuthService jwtAuthService;

    @Autowired
    private UserService userService;

    @PostMapping("/login/{code}")
    @Log(operModule = "用户操作-登录",operType = OperationLogType.LOGIN,operDesc = "用户登录")
    @ApiOperation(value = "用户登录")
    public Result login(HttpServletRequest request, @RequestBody User user, @PathVariable String code) {
        HttpSession session = request.getSession();
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
    @PreAuthorize("hasAuthority('userQuery')")
    @ApiOperation(value = "获取用户名")
    @Log(operModule = "用户操作-获取用户名",operType = OperationLogType.QUERY,operDesc = "获取用户名")
    public String currentUserName(Principal principal) {
        return principal.getName();
    }

    @GetMapping("/logout")
    @Log(operModule = "用户操作-登出",operType = OperationLogType.LOGOUT,operDesc = "用户登出")
    @PreAuthorize("hasAuthority('userQuery')")
    @ApiOperation(value = "用户登出")
    public Result logout(HttpServletRequest request, Principal principal) {
        String token = request.getHeader("Authorization");
        String redisKey = principal.getName()+":token";
        if (redisUtil.hasKey(redisKey)) {
            redisUtil.del(redisKey);
        }
        return Result.of("登出成功");
    }

    @GetMapping("/search/{username}")
    @PreAuthorize("hasAuthority('userQuery')")
    @Log(operModule = "用户操作-查询用户",operType = OperationLogType.QUERY,operDesc = "查询用户")
    @ApiOperation(value = "查询用户")
    public Result<User> searchUser(@PathVariable String username) {
        return userService.getUserByName(username);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('userEdit')")
    @Log(operModule = "用户操作-新增用户",operType = OperationLogType.ADD,operDesc = "新增用户")
    @ApiOperation(value = "新增用户")
    public Result<Boolean> addUser(@RequestBody User user, BindingResult errors) {
        if (errors.hasErrors()) {
            return Result.of(null, errors.getFieldError().getDefaultMessage());
        }
        return userService.insertUser(user);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('userEdit')")
    @Log(operModule = "用户操作-修改用户",operType = OperationLogType.MODIFY,operDesc = "修改用户")
    @ApiOperation(value = "修改用户")
    public Result<Boolean> updateUser(@RequestBody User user, BindingResult errors) {
        if (errors.hasErrors()) {
            return Result.of(null, errors.getFieldError().getDefaultMessage());
        }
        try {
            return userService.updateUser(user);
        } catch (Exception e) {
            return Result.error(ErrorEnum.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{username}")
    @PreAuthorize("hasAuthority('userEdit')")
    @Log(operModule = "用户操作-删除用户",operType = OperationLogType.DELETE,operDesc = "删除用户")
    @ApiOperation(value = "删除用户")
    public Result<Boolean> deleteUser(@PathVariable String username) {
        return userService.deleteUserByName(username);
    }

    @GetMapping("/export")
    @PreAuthorize("hasAuthority('userQuery')")
    @Log(operModule = "用户操作-导出用户",operType = OperationLogType.QUERY,operDesc = "导出用户")
    @ApiOperation(value = "导出用户")
    public Result<String> exportUser(HttpServletResponse response) {
        return userService.exportUserList(response);
    }

    @GetMapping("/page/{pageNum}/{pageSize}")
    @PreAuthorize("hasAuthority('userQuery')")
    @Log(operModule = "用户操作-分页查询用户",operType = OperationLogType.QUERY,operDesc = "分页查询用户")
    @ApiOperation(value = "分页查询用户")
    public Result<PageVo> getUserByPage(@PathVariable Integer pageNum, @PathVariable Integer pageSize) {
        return userService.getUserList(pageNum, pageSize);
    }
    private String getCaptchaKey(HttpServletRequest request) {
        String ip = AgentUtil.getIpAddr(request);
        String userAgent = request.getHeader("User-Agent");
        String key = "user-service:captcha:" + DigestUtils.md5DigestAsHex((ip+userAgent).getBytes());
        return key;
    }
}