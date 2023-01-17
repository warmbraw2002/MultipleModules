package com.tiktok.service.impl;

import com.tiktok.domain.User;
import com.tiktok.mapper.UserMapper;
import com.tiktok.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByName(String username) {
        return userMapper.findUserByName(username);
    }
}
