package com.tiktok.service.impl;

import com.tiktok.domain.JwtUser;
import com.tiktok.domain.User;
import com.tiktok.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userService.getUserByName(s);
        if (user == null) {
            throw new RuntimeException("用户" + s + "不存在"); // 可使用断言，使代码更简洁优雅
        }
        JwtUser jwtUser = new JwtUser(user);
        // 将数据库的roles解析为UserDetails的权限集
        // AuthorityUtils.commaSeparatedStringToAuthorityList将逗号分隔的字符集转成权限对象列表
        if (user.getIsAdmin()) {
            jwtUser.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN"));
        } else {
            jwtUser.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"));
        }
        return jwtUser;
    }
}

