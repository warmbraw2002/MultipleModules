package com.tiktok.mapper;

import com.tiktok.domain.Role;
import com.tiktok.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

@Mapper
public interface UserMapper {

    @SelectProvider(type = UserProvider.class, method = "selectSQL")
    @Results(id = "userMap", value = {
            @Result(property = "userId", column = "user_id"),
            @Result(property = "deptId", column = "dept_id"),
            @Result(property = "username", column = "username"),
            @Result(property = "nickName", column = "nick_name"),
            @Result(property = "gender", column = "gender"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "email", column = "email"),
            @Result(property = "avatarName", column = "avatar_name"),
            @Result(property = "avatarPath", column = "avatar_path"),
            @Result(property = "password", column = "password"),
            @Result(property = "isAdmin", column = "is_admin"),
            @Result(property = "enabled", column = "enabled"),
            @Result(property = "createBy", column = "create_by"),
            @Result(property = "updateBy", column = "update_by"),
            @Result(property = "pwdResetTime", column = "pwd_reset_time"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time"),
    })
    User findUserByName(String username);

    @SelectProvider(type = UserProvider.class, method = "selectRolesSQL")
    @Results(id = "roleMap", value = {
            @Result(property = "roleId", column = "role_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "level", column = "level"),
            @Result(property = "description", column = "description"),
            @Result(property = "dataScope", column = "data_scope"),
            @Result(property = "createBy", column = "create_by"),
            @Result(property = "updateBy", column = "update_by"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time"),
    })
    List<Role> findRolesByUsername(String username);
}
