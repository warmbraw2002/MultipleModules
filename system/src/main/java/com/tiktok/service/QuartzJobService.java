package com.tiktok.service;

import com.tiktok.Result;
import com.tiktok.domain.QuartzJob;

public interface QuartzJobService {

    Result<QuartzJob> getQuartzJobById(Long jobId);
    Result<Boolean> insertQuartzJob(QuartzJob quartzJob);
    Result<Boolean> updateQuartzJob(QuartzJob quartzJob) throws Exception;
    Result<Boolean> deleteQuartzJobById(Long quartzJob);
    Result<Boolean> triggerQuartzJob(Long jobId);
}
