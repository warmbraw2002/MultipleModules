package com.tiktok.controller;

import com.tiktok.ErrorEnum;
import com.tiktok.PageVo;
import com.tiktok.Result;
import com.tiktok.annotation.Log;
import com.tiktok.domain.Dept;
import com.tiktok.domain.Dict;
import com.tiktok.service.DictService;
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
@RequestMapping("/dict")
public class DictController {

    @Autowired
    private DictService dictService;

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('DictQuery')")
    @Log(operModule = "字典管理",operType = "查询",operDesc = "根据id查询字典")
    @ApiOperation(value = "根据id查询字典")
    public Result<Dict> getById(@PathVariable("id") Long id) {
        return dictService.getDictById(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('DictEdit')")
    @Log(operModule = "字典管理",operType = "新增",operDesc = "新增字典")
    @ApiOperation(value = "新增字典")
    public Result<Boolean> add(@RequestBody @Valid Dict dict, BindingResult errors) {
        if (errors.hasErrors()) {
            return Result.of(null, errors.getFieldError().getDefaultMessage());
        }
        return dictService.insertDict(dict);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('DictEdit')")
    @Log(operModule = "字典管理",operType = "修改",operDesc = "修改字典")
    @ApiOperation(value = "修改字典")
    public Result<Boolean> update(@RequestBody @Valid Dict dict, BindingResult errors) {
        if (errors.hasErrors()) {
            return Result.of(null, errors.getFieldError().getDefaultMessage());
        }
        try {
            return dictService.updateDict(dict);
        } catch (Exception e) {
            return Result.error(ErrorEnum.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DictEdit')")
    @Log(operModule = "字典管理",operType = "删除",operDesc = "删除字典")
    @ApiOperation(value = "删除字典")
    public Result<Boolean> delete(@PathVariable("id") Long id) {
        return dictService.deleteDictById(id);
    }

    @GetMapping("/{pageNum}/{pageSize}")
    @PreAuthorize("hasAuthority('DictQuery')")
    @Log(operModule = "字典管理",operType = "查询",operDesc = "分页查询字典")
    @ApiOperation(value = "分页查询字典")
    public Result<PageVo> list(@PathVariable("pageSize") Integer pageSize, @PathVariable("pageNum") Integer pageNum) {
        return dictService.getDictList(pageNum, pageSize);
    }

    @GetMapping("/export")
    @PreAuthorize("hasAuthority('DictQuery')")
    @Log(operModule = "字典管理",operType = "导出",operDesc = "导出字典")
    @ApiOperation(value = "导出字典")
    public Result<String> export(HttpServletResponse response) {
        List<Dict> dictList = dictService.getDictList();
        FileUtil.exportExcel(dictList, "部门数据", "部门", Dept.class, "部门.xls", response);
        return Result.of("导出成功");
    }
}
