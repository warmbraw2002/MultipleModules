package com.tiktok.service;

import com.tiktok.PageVo;
import com.tiktok.Result;
import com.tiktok.domain.Role;
import com.tiktok.domain.Role;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;

public interface RoleService {

    /**
     * 根据角色ID查询角色信息
     * @param roleId
     * @return Result<Role>
     */
    Result<Role> getRoleById(Long roleId);

    /**
     * 新增角色信息
     * @param role
     * @return Result<Boolean>
     */
    Result<Boolean> insertRole(Role role);

    /**
     * 修改角色信息
     * @param role
     * @return Result<Boolean>
     */
    Result<Boolean> updateRole(Role role) throws Exception;

    /**
     * 根据角色ID删除角色信息
     * @param roleId
     * @return Result<Boolean>
     */
    Result<Boolean> deleteRoleById(Long roleId);

    /**
     * 查询角色列表（分页）
     * @param pageNum
     * @param pageSize
     * @return Result<PageVo>
     */
    Result<PageVo> getRolePage(Integer pageNum, Integer pageSize);

    /**
     * 导出角色列表
     * @param response
     * @return Result<String>
     */
    Result<String> exportRoleList(HttpServletResponse response);

    Result<Boolean> updatePermission(Long roleId, Set<Long> menuIds);
}
