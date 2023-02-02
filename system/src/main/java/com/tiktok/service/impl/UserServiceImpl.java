package com.tiktok.service.impl;

import com.tiktok.ErrorEnum;
import com.tiktok.PageVo;
import com.tiktok.Result;
import com.tiktok.domain.User;
import com.tiktok.mapper.UserMapper;
import com.tiktok.service.UserService;
import com.tiktok.utils.ClassExamine;
import com.tiktok.utils.FileUtil;
import com.tiktok.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Result<User> getUserByName(String username) {
        User user = userMapper.findUserByName(username);
        if (user == null) {
            return Result.error(ErrorEnum.NOT_FOUND);
        }
        return null;
    }

    @Override
    public Result<Boolean> insertUser(User user) {
        User user1 = userMapper.findUserByName(user.getUsername());
        if (user1 != null) {
            return Result.error(ErrorEnum.DUPLICATE_KEY);
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Date date = new Date();
        user.setCreateTime(date);
        return Result.of(userMapper.insertUser(user) > 0);
    }

    @Override
    public Result<Boolean> updateUser(User user) throws Exception {
        User user1 = userMapper.findUserByName(user.getUsername());
        if (user1 == null) {
            return Result.error(ErrorEnum.NOT_FOUND);
        }
        Date date = new Date();
        if (!bCryptPasswordEncoder.matches(user.getPassword(), user1.getPassword())) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            user.setPwdResetTime(date);
        }
        ClassExamine.objectOverlap(user1, user);
        user.setUpdateTime(date);
        return Result.of(userMapper.updateUser(user) > 0);
    }

    @Override
    public Result<Boolean> deleteUserByName(String username) {
        User user = userMapper.findUserByName(username);
        if (user == null) {
            return Result.error(ErrorEnum.NOT_FOUND);
        }
        return Result.of(userMapper.deleteUserByName(username) > 0);
    }

    @Override
    public Result<PageVo> getUserList(Integer pageNum, Integer pageSize) {
        PageVo pageVo = new PageVo(pageNum, pageSize);
        PageUtil.startPage(pageVo);
        List<User> userList = userMapper.findAllUser();
        return Result.of(PageUtil.endPage(pageVo, userList));
    }

    @Override
    public Result<String> exportUserList(HttpServletResponse response) {
        List<User> userList = userMapper.findAllUser();
        FileUtil.exportExcel(userList, "用户数据", "用户", User.class, "用户.xls", response);
        return Result.of("导出成功，文件名为：用户.xls");
    }
}
