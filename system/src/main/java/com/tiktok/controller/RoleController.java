package com.tiktok.controller;

import com.tiktok.ErrorEnum;
import com.tiktok.PageVo;
import com.tiktok.Result;
import com.tiktok.annotation.Log;
import com.tiktok.domain.Role;
import com.tiktok.service.RoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('RoleQuery')")
    @Log(operModule = "角色管理",operType = "查询",operDesc = "根据id查询角色")
    @ApiOperation(value = "根据id查询角色")
    public Result<Role> getById(@PathVariable("id") Long id) {
        return roleService.getRoleById(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('RoleEdit')")
    @Log(operModule = "角色管理",operType = "新增",operDesc = "新增角色")
    @ApiOperation(value = "新增角色")
    public Result<Boolean> add(@RequestBody @Valid Role role, BindingResult errors) {
        if (errors.hasErrors()) {
            return Result.of(null, errors.getFieldError().getDefaultMessage());
        }
        return roleService.insertRole(role);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('RoleEdit')")
    @Log(operModule = "角色管理",operType = "修改",operDesc = "修改角色")
    @ApiOperation(value = "修改角色")
    public Result<Boolean> update(@RequestBody @Valid Role role, BindingResult errors) {
        if (errors.hasErrors()) {
            return Result.of(null, errors.getFieldError().getDefaultMessage());
        }
        try {
            return roleService.updateRole(role);
        } catch (Exception e) {
            return Result.error(ErrorEnum.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('RoleQuery')")
    @Log(operModule = "角色管理",operType = "删除",operDesc = "删除角色")
    @ApiOperation(value = "删除角色")
    public Result<Boolean> delete(@PathVariable("id") Long id) {
        return roleService.deleteRoleById(id);
    }

    @GetMapping("/{pageNum}/{pageSize}")
    @PreAuthorize("hasAuthority('RoleEdit')")
    @Log(operModule = "角色管理",operType = "查询",operDesc = "分页查询角色")
    @ApiOperation(value = "分页查询角色")
    public Result<PageVo> list(@PathVariable("pageSize") Integer pageSize, @PathVariable("pageNum") Integer pageNum) {
        return roleService.getRolePage(pageNum, pageSize);
    }

    @GetMapping("/export")
    @PreAuthorize("hasAuthority('RoleEdit')")
    @Log(operModule = "角色管理",operType = "导出",operDesc = "导出角色")
    @ApiOperation(value = "导出角色")
    public Result<String> export(HttpServletResponse response) {
        return roleService.exportRoleList(response);
    }

    @PostMapping("/permission/{roleId}")
    @PreAuthorize("hasAuthority('RoleEdit')")
    @Log(operModule = "角色管理",operType = "修改",operDesc = "修改角色权限")
    @ApiOperation(value = "修改角色权限")
    public Result<Boolean> updatePermission(@PathVariable("roleId") Long roleId, @RequestBody Set<Long> menuIds) {
        return roleService.updatePermission(roleId, menuIds);
    }
}
