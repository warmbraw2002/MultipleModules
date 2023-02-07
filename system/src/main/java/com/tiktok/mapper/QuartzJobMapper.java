package com.tiktok.mapper;

import com.tiktok.domain.QuartzJob;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuartzJobMapper {

    @SelectProvider(type = QuartzJobProvider.class, method = "selectByIdSQL")
    @Results(id = "quartzJobMap", value = {
            @Result(property = "jobId", column = "job_id"),
            @Result(property = "beanName", column = "bean_name"),
            @Result(property = "cronExpression", column = "cron_expression"),
            @Result(property = "isPause", column = "is_pause"),
            @Result(property = "jobName", column = "job_name"),
            @Result(property = "methodName", column = "method_name"),
            @Result(property = "personInCharge", column = "person_in_charge"),
            @Result(property = "subTask", column = "sub_task"),
            @Result(property = "pauseAfterFailure", column = "pause_after_failure"),
            @Result(property = "createBy", column = "create_by"),
            @Result(property = "updateBy", column = "update_by"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time"),
    })
    QuartzJob findQuartzJobById(Long jobId);

    @InsertProvider(type = QuartzJobProvider.class, method = "insertSQL")
    @Options(useGeneratedKeys = true, keyProperty = "quartzJobId", keyColumn = "quartzJob_id")
    int insertQuartzJob(QuartzJob quartzJob);

    @UpdateProvider(type = QuartzJobProvider.class, method = "updateSQL")
    int updateQuartzJob(QuartzJob quartzJob);

    @DeleteProvider(type = QuartzJobProvider.class, method = "deleteByIdSQL")
    int deleteQuartzJobById(Long jobId);

    @SelectProvider(type = QuartzJobProvider.class, method = "selectByIsPauseSQL")
    @ResultMap("quartzJobMap")
    List<QuartzJob> findQuartzJobByIsPause(Boolean isPause);
}
