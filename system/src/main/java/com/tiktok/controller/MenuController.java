package com.tiktok.controller;

import com.tiktok.Result;
import com.tiktok.annotation.Log;
import com.tiktok.domain.Menu;
import com.tiktok.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

//之后任务：菜单管理API实现(在system包中实现:MenuController)
//实现 菜单的增删改查功能
//根据当前用户权限获取前端展示菜单
//根据菜单id获取所有子菜单id
//根据菜单id获取同级和上级数据
@RestController
@RequestMapping("/menu")
@Api(tags = "菜单管理")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @PostMapping
    @PreAuthorize("hasAuthority('MenuEdit')")
    @Log(operModule = "菜单管理",operType = "新增",operDesc = "新增菜单")
    @ApiOperation(value = "新增菜单")
    public Result add(@RequestBody Menu menu) {
        return menuService.insertMenu(menu);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('MenuQuery')")
    @Log(operModule = "菜单管理",operType = "查询",operDesc = "根据id查询菜单")
    @ApiOperation(value = "根据id查询菜单")
    public Result getById(@PathVariable("id") Long id) {
        return menuService.getMenuById(id);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('MenuEdit')")
    @Log(operModule = "菜单管理",operType = "修改",operDesc = "修改菜单")
    @ApiOperation(value = "修改菜单")
    public Result update(@RequestBody Menu menu) throws Exception {
        return menuService.updateMenu(menu);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('MenuEdit')")
    @Log(operModule = "菜单管理",operType = "删除",operDesc = "根据id删除菜单")
    @ApiOperation(value = "删除菜单")
    public Result delete(@PathVariable("id") Long id) {
        return menuService.deleteMenuById(id);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('MenuQuery')")
    @Log(operModule = "菜单管理",operType = "查询",operDesc = "查询所有菜单")
    @ApiOperation(value = "查询所有菜单")
    public Result getAll() {
        return menuService.getMenuByCurrentUser();
    }

    @GetMapping("/sub/{id}")
    @PreAuthorize("hasAuthority('MenuQuery')")
    @Log(operModule = "菜单管理",operType = "查询",operDesc = "根据菜单id获取所有子菜单")
    @ApiOperation(value = "根据菜单id获取所有子菜单")
    public Result getSubmenu(@PathVariable("id") Long id) {
        return menuService.getSubmenu(id);
    }

    @GetMapping("/peer/{id}")
    @PreAuthorize("hasAuthority('MenuQuery')")
    @Log(operModule = "菜单管理",operType = "查询",operDesc = "根据菜单id获取所有同级菜单")
    @ApiOperation(value = "根据菜单id获取所有同级菜单")
    public Result getPeerMenu(@PathVariable("id") Long id) {
        return menuService.getPeerMenu(id);
    }

    @GetMapping("/parent/{id}")
    @PreAuthorize("hasAuthority('MenuQuery')")
    @Log(operModule = "菜单管理",operType = "查询",operDesc = "根据菜单id获取所有上级菜单")
    @ApiOperation(value = "根据菜单id获取所有上级菜单")
    public Result getParentMenu(@PathVariable("id") Long id) {
        return menuService.getParentMenu(id);
    }
}
