package com.tiktok.service.impl;

import com.tiktok.domain.JwtUser;
import com.tiktok.domain.Role;
import com.tiktok.domain.User;
import com.tiktok.mapper.RoleMapper;
import com.tiktok.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userMapper.findUserByName(s);
        if (user == null) {
            throw new RuntimeException("用户" + s + "不存在"); // 可使用断言，使代码更简洁优雅
        }
        JwtUser jwtUser = new JwtUser(user);
        // 将数据库的roles解析为UserDetails的权限集
        // AuthorityUtils.commaSeparatedStringToAuthorityList将逗号分隔的字符集转成权限对象列表
//        if (user.getIsAdmin()) {
//            jwtUser.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN"));
//        } else {
//            jwtUser.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"));
//        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roleMapper.findRoleByUserId(user.getUserId())) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        jwtUser.setAuthorities(authorities);
        return jwtUser;
    }
}

