package com.tiktok.controller;

import com.tiktok.ErrorEnum;
import com.tiktok.PageVo;
import com.tiktok.Result;
import com.tiktok.annotation.Log;
import com.tiktok.domain.Dept;
import com.tiktok.service.DeptService;
import com.tiktok.utils.FileUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @Log(operModule = "部门管理",operType = "查询",operDesc = "根据id查询部门")
    @ApiOperation(value = "根据id查询部门")
    public Result<Dept> getById(@PathVariable("id") Long id) {
        return deptService.getDeptById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Log(operModule = "部门管理",operType = "新增",operDesc = "新增部门")
    @ApiOperation(value = "新增部门")
    public Result<Boolean> add(@RequestBody @Valid Dept dept, BindingResult errors) {
        if (errors.hasErrors()) {
            return Result.of(null, errors.getFieldError().getDefaultMessage());
        }
        return deptService.insertDept(dept);
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Log(operModule = "部门管理",operType = "修改",operDesc = "修改部门")
    @ApiOperation(value = "修改部门")
    public Result<Boolean> update(@RequestBody @Valid Dept dept, BindingResult errors) {
        if (errors.hasErrors()) {
            return Result.of(null, errors.getFieldError().getDefaultMessage());
        }
        try {
            return deptService.updateDept(dept);
        } catch (Exception e) {
            return Result.error(ErrorEnum.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Log(operModule = "部门管理",operType = "删除",operDesc = "删除部门")
    @ApiOperation(value = "删除部门")
    public Result<Boolean> delete(@PathVariable("id") Long id) {
        return deptService.deleteDeptById(id);
    }

    @GetMapping("/{pageNum}/{pageSize}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @Log(operModule = "部门管理",operType = "查询",operDesc = "分页查询部门")
    @ApiOperation(value = "分页查询部门")
    public Result<PageVo> list(@PathVariable("pageSize") Integer pageSize, @PathVariable("pageNum") Integer pageNum) {
        return deptService.getDeptList(pageNum, pageSize);
    }

    @GetMapping("/export")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @Log(operModule = "部门管理",operType = "导出",operDesc = "导出部门")
    @ApiOperation(value = "导出部门")
    public Result<String> export(HttpServletResponse response) {
        List<Dept> deptList = deptService.getDeptList();
        FileUtil.exportExcel(deptList, "部门数据", "部门", Dept.class, "部门.xls", response);
        return Result.of("导出成功");
    }
}
