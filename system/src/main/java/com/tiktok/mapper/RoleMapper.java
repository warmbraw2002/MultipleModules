package com.tiktok.mapper;


import com.tiktok.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RoleMapper {

    @SelectProvider(type = RoleProvider.class, method = "selectByIdSQL")
    @Results(id = "roleMap", value = {
            @Result(property = "roleId", column = "role_id"),
            @Result(property = "dataScope", column = "data_scope"),
            @Result(property = "createBy", column = "create_by"),
            @Result(property = "updateBy", column = "update_by"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time"),
    })
    Role findRoleById(Long roleId);

    @InsertProvider(type = RoleProvider.class, method = "insertSQL")
    @Options(useGeneratedKeys = true, keyProperty = "roleId", keyColumn = "role_id")
    int insertRole(Role role);

    @UpdateProvider(type = RoleProvider.class, method = "updateSQL")
    int updateRole(Role role);

    @DeleteProvider(type = RoleProvider.class, method = "deleteByIdSQL")
    int deleteRoleById(Long roleId);

    @SelectProvider(type = RoleProvider.class, method = "selectAllSQL")
    @ResultMap("roleMap")
    List<Role> findAllRole();

    @SelectProvider(type = RoleProvider.class, method = "selectByUserIdSQL")
    @ResultMap("roleMap")
    List<Role> findRoleByUserId(Long userId);

    @UpdateProvider(type = RoleProvider.class, method = "updatePermissionSQL")
    int updatePermission(Long menuId, String permission);
}
