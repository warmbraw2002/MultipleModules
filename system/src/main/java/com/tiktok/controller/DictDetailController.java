package com.tiktok.controller;

import com.tiktok.ErrorEnum;
import com.tiktok.PageVo;
import com.tiktok.Result;
import com.tiktok.annotation.Log;
import com.tiktok.domain.DictDetail;
import com.tiktok.service.DictDetailService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/dictDetail")
public class DictDetailController {

    @Autowired
    private DictDetailService dictDetailService;

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @Log(operModule = "字典详情管理",operType = "查询",operDesc = "根据id查询字典详情")
    @ApiOperation(value = "根据id查询字典详情")
    public Result<DictDetail> getById(@PathVariable("id") Long id) {
        return dictDetailService.getDictDetailById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Log(operModule = "字典详情管理",operType = "新增",operDesc = "新增字典详情")
    @ApiOperation(value = "新增字典详情")
    public Result<Boolean> add(@RequestBody @Valid DictDetail dictDetail, BindingResult errors) {
        if (errors.hasErrors()) {
            return Result.of(null, errors.getFieldError().getDefaultMessage());
        }
        return dictDetailService.insertDictDetail(dictDetail);
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Log(operModule = "字典详情管理",operType = "修改",operDesc = "修改字典详情")
    @ApiOperation(value = "修改字典详情")
    public Result<Boolean> update(@RequestBody @Valid DictDetail dictDetail, BindingResult errors) {
        if (errors.hasErrors()) {
            return Result.of(null, errors.getFieldError().getDefaultMessage());
        }
        try {
            return dictDetailService.updateDictDetail(dictDetail);
        } catch (Exception e) {
            return Result.error(ErrorEnum.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Log(operModule = "字典详情管理",operType = "删除",operDesc = "删除字典详情")
    @ApiOperation(value = "删除字典详情")
    public Result<Boolean> delete(@PathVariable("id") Long id) {
        return dictDetailService.deleteDictDetailById(id);
    }

    @GetMapping("/{pageNum}/{pageSize}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @Log(operModule = "字典详情管理",operType = "查询",operDesc = "分页查询字典详情")
    @ApiOperation(value = "分页查询字典详情")
    public Result<PageVo> list(@PathVariable("pageSize") Integer pageSize, @PathVariable("pageNum") Integer pageNum) {
        return dictDetailService.getDictDetailList(pageNum, pageSize);
    }
}
