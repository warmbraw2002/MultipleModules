package com.tiktok.mapper;

import com.tiktok.domain.Role;
import com.tiktok.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @SelectProvider(type = UserProvider.class, method = "selectSQL")
    @Results(id = "userMap", value = {
            @Result(property = "userId", column = "user_id"),
            @Result(property = "deptId", column = "dept_id"),
            @Result(property = "nickName", column = "nick_name"),
            @Result(property = "avatarName", column = "avatar_name"),
            @Result(property = "avatarPath", column = "avatar_path"),
            @Result(property = "isAdmin", column = "is_admin"),
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

    @InsertProvider(type = UserProvider.class, method = "insertSQL")
    @Options(useGeneratedKeys = true, keyProperty = "userId", keyColumn = "user_id")
    int insertUser(User user);

    @UpdateProvider(type = UserProvider.class, method = "updateSQL")
    int updateUser(User user);

    @DeleteProvider(type = UserProvider.class, method = "deleteByNameSQL")
    int deleteUserByName(String username);

    @SelectProvider(type = UserProvider.class, method = "selectAllSQL")
    @ResultMap("userMap")
    List<User> findAllUser();
}
