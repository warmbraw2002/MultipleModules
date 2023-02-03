package com.tiktok.service.impl;

import com.tiktok.ErrorEnum;
import com.tiktok.PageVo;
import com.tiktok.Result;
import com.tiktok.domain.Role;
import com.tiktok.mapper.RoleMapper;
import com.tiktok.service.RoleService;
import com.tiktok.utils.ClassExamine;
import com.tiktok.utils.FileUtil;
import com.tiktok.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Result<Role> getRoleById(Long roleId) {
        Role role = roleMapper.findRoleById(roleId);
        if (role == null) {
            return Result.error(ErrorEnum.NOT_FOUND);
        }
        return Result.of(role);
    }

    @Override
    public Result<Boolean> insertRole(Role role) {
        return Result.of(roleMapper.insertRole(role) > 0);
    }

    @Override
    public Result<Boolean> updateRole(Role role) throws Exception {
        Role oldRole = roleMapper.findRoleById(role.getRoleId());
        if (oldRole == null) {
            return Result.error(ErrorEnum.NOT_FOUND);
        }
        ClassExamine.objectOverlap(oldRole, role);
        return Result.of(roleMapper.updateRole(role) > 0);
    }

    @Override
    public Result<Boolean> deleteRoleById(Long roleId) {
        Role role = roleMapper.findRoleById(roleId);
        if (role == null) {
            return Result.error(ErrorEnum.NOT_FOUND);
        }
        return Result.of(roleMapper.deleteRoleById(roleId) > 0);
    }

    @Override
    public Result<PageVo> getRolePage(Integer pageNum, Integer pageSize) {
        PageVo pageVo = new PageVo(pageNum, pageSize);
        PageUtil.startPage(pageVo);
        List<Role> roleList = roleMapper.findAllRole();
        return Result.of(PageUtil.endPage(pageVo, roleList));
    }

    @Override
    public Result<String> exportRoleList(HttpServletResponse response) {
        List<Role> roleList = roleMapper.findAllRole();
        FileUtil.exportExcel(roleList, "角色列表", "角色", Role.class, "角色.xls", response);
        return Result.of("导出成功，文件名为：角色.xls");
    }

    @Override
    public Result<Boolean> updatePermission(Long roleId, Set<Long> menuIds) {
        Role role = roleMapper.findRoleById(roleId);
        if (role == null) {
            return Result.error(ErrorEnum.NOT_FOUND);
        }
        for (Long menuId : menuIds) {
            roleMapper.updatePermission(menuId, role.getName());
        }
        return Result.of(true);
    }
}
