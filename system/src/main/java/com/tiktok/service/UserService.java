package com.tiktok.service;

import com.tiktok.PageVo;
import com.tiktok.Result;
import com.tiktok.domain.Dept;
import com.tiktok.domain.User;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author DPH
 * @date 2023/1/16
 */
public interface UserService {

        /**
        * 根据用户名查询用户
        * @param username
        * @return
        */
        Result<User> getUserByName(String username);
        Result<Boolean> insertUser(User user);
        Result<Boolean> updateUser(User user) throws Exception;
        Result<Boolean> deleteUserByName(String username);
        Result<PageVo> getUserList(Integer pageNum, Integer pageSize);
        Result<String> exportUserList(HttpServletResponse response);
}
