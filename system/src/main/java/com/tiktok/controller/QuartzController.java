package com.tiktok.controller;

import com.tiktok.ErrorEnum;
import com.tiktok.Result;
import com.tiktok.annotation.Log;
import com.tiktok.domain.QuartzJob;
import com.tiktok.service.impl.QuartzJobRunner;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/quartz")
public class QuartzController {

    @Autowired
    private QuartzJobRunner quartzJobRunner;

    @PostMapping
    @PreAuthorize("hasAuthority('QuartzJobEdit')")
    @Log(operModule = "定时任务管理",operType = "新增",operDesc = "新增定时任务")
    @ApiOperation(value = "新增定时任务")
    public Result<Boolean> add(@RequestBody @Valid QuartzJob quartzJob, BindingResult errors) {
        if (errors.hasErrors()) {
            return Result.of(null, errors.getFieldError().getDefaultMessage());
        }
        return quartzJobRunner.insertQuartzJob(quartzJob);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('QuartzJobEdit')")
    @Log(operModule = "定时任务管理",operType = "删除",operDesc = "删除定时任务")
    @ApiOperation(value = "删除定时任务")
    public Result<Boolean> delete(@PathVariable("id") Long id) {
        return quartzJobRunner.deleteQuartzJobById(id);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('QuartzJobEdit')")
    @Log(operModule = "定时任务管理",operType = "修改",operDesc = "修改定时任务")
    @ApiOperation(value = "修改定时任务")
    public Result<Boolean> update(@RequestBody @Valid QuartzJob quartzJob, BindingResult errors) {
        if (errors.hasErrors()) {
            return Result.of(null, errors.getFieldError().getDefaultMessage());
        }
        try {
            return quartzJobRunner.updateQuartzJob(quartzJob);
        } catch (Exception e) {
            return Result.error(ErrorEnum.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('QuartzJobQuery')")
    @Log(operModule = "定时任务管理",operType = "查询",operDesc = "根据id查询定时任务")
    @ApiOperation(value = "根据id查询定时任务")
    public Result<QuartzJob> getById(@PathVariable("id") Long id) {
        return quartzJobRunner.getQuartzJobById(id);
    }

    @GetMapping("/trigger/{id}")
    @PreAuthorize("hasAuthority('QuartzJobEdit')")
    @Log(operModule = "定时任务管理",operType = "触发",operDesc = "启，停定时任务的状态切换")
    @ApiOperation(value = "启，停定时任务的状态切换")
    public Result<Boolean> trigger(@PathVariable("id") Long id) {
        return quartzJobRunner.triggerQuartzJob(id);
    }
}
