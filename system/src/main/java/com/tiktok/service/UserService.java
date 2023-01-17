package com.tiktok.service;

import com.tiktok.domain.User;

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
        User getUserByName(String username);
}
