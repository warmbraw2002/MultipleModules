package com.tiktok.service.impl;

import com.tiktok.ErrorEnum;
import com.tiktok.Result;
import com.tiktok.domain.QuartzJob;
import com.tiktok.mapper.QuartzJobMapper;
import com.tiktok.quartz.CronTaskRegister;
import com.tiktok.quartz.SchedulingRunnable;
import com.tiktok.service.QuartzJobService;
import com.tiktok.utils.ClassExamine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class QuartzJobRunner implements CommandLineRunner, QuartzJobService {

    private static final Logger logger = LoggerFactory.getLogger(QuartzJobRunner.class);

    @Autowired
    private QuartzJobMapper quartzJobMapper;

    @Autowired
    private CronTaskRegister cronTaskRegister;

    @Override
    public void run(String... args) {
        // 初始加载数据库里状态为启用的定时任务
        List<QuartzJob> jobList = quartzJobMapper.findQuartzJobByIsPause(false);
        if (!CollectionUtils.isEmpty(jobList)) {
            for (QuartzJob job : jobList) {
                SchedulingRunnable task = new SchedulingRunnable(job.getBeanName(), job.getMethodName(), job.getParams());
                cronTaskRegister.addCronTask(task, job.getCronExpression());
            }
            logger.info("定时任务已加载完毕...");
        }
    }

    @Override
    public Result<QuartzJob> getQuartzJobById(Long jobId) {
        QuartzJob quartzJob = quartzJobMapper.findQuartzJobById(jobId);
        if (quartzJob == null) {
            return Result.error(ErrorEnum.NOT_FOUND);
        }
        return Result.of(quartzJob);
    }

    @Override
    public Result<Boolean> insertQuartzJob(QuartzJob quartzJob) {
        int result = quartzJobMapper.insertQuartzJob(quartzJob);
        if (result == 0) {
            return Result.error(ErrorEnum.INTERNAL_SERVER_ERROR);
        }
        // 如果定时任务状态为启用，则添加到任务调度中
        if (quartzJob.getIsPause().equals(false)) {
            SchedulingRunnable task = new SchedulingRunnable(quartzJob.getBeanName(), quartzJob.getMethodName(), quartzJob.getParams());
            cronTaskRegister.addCronTask(task, quartzJob.getCronExpression());
        }
        return Result.of(true);
    }

    @Override
    public Result<Boolean> updateQuartzJob(QuartzJob quartzJob) throws Exception {
        QuartzJob oldJob = quartzJobMapper.findQuartzJobById(quartzJob.getJobId());
        if (oldJob == null) {
            return Result.error(ErrorEnum.NOT_FOUND);
        }
        ClassExamine.objectOverlap(oldJob, quartzJob);
        int result = quartzJobMapper.updateQuartzJob(quartzJob);
        if (result == 0) {
            return Result.error(ErrorEnum.INTERNAL_SERVER_ERROR);
        }
        // 先删除原来的定时任务
        if (oldJob.getIsPause().equals(false)) {
            SchedulingRunnable task = new SchedulingRunnable(oldJob.getBeanName(), oldJob.getMethodName(), oldJob.getParams());
            cronTaskRegister.removeCronTask(task);
        }
        // 新增定时任务
        if (quartzJob.getIsPause().equals(false)) {
            SchedulingRunnable task = new SchedulingRunnable(quartzJob.getBeanName(), quartzJob.getMethodName(), quartzJob.getParams());
            cronTaskRegister.addCronTask(task, quartzJob.getCronExpression());
        }
        return Result.of(true);
    }

    @Override
    public Result<Boolean> deleteQuartzJobById(Long quartzJob) {
        QuartzJob oldJob = quartzJobMapper.findQuartzJobById(quartzJob);
        if (oldJob == null) {
            return Result.error(ErrorEnum.NOT_FOUND);
        }
        // 如果定时任务状态为启用，则从任务调度中移除
        if (oldJob.getIsPause().equals(false)) {
            SchedulingRunnable task = new SchedulingRunnable(oldJob.getBeanName(), oldJob.getMethodName(), oldJob.getParams());
            cronTaskRegister.removeCronTask(task);
        }
        return Result.of(true);
    }

    @Override
    public Result<Boolean> triggerQuartzJob(Long jobId) {
        QuartzJob quartzJob = quartzJobMapper.findQuartzJobById(jobId);
        if (quartzJob == null) {
            return Result.error(ErrorEnum.NOT_FOUND);
        }
        // 如果定时任务状态为启用，则从任务调度中移除
        if (quartzJob.getIsPause().equals(false)) {
            SchedulingRunnable task = new SchedulingRunnable(quartzJob.getBeanName(), quartzJob.getMethodName(), quartzJob.getParams());
            cronTaskRegister.removeCronTask(task);
            quartzJob.setIsPause(true);
            quartzJobMapper.updateQuartzJob(quartzJob);
        } else {
            // 如果定时任务状态为禁用，则从任务调度中添加
            SchedulingRunnable task = new SchedulingRunnable(quartzJob.getBeanName(), quartzJob.getMethodName(), quartzJob.getParams());
            cronTaskRegister.addCronTask(task, quartzJob.getCronExpression());
            quartzJob.setIsPause(false);
            quartzJobMapper.updateQuartzJob(quartzJob);
        }
        return Result.of(true);
    }

}