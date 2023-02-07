package com.tiktok.mapper;


import org.apache.ibatis.jdbc.SQL;

public class QuartzJobProvider {

    public String selectByIdSQL() {
        String sql = new SQL()
                .SELECT("*")
                .FROM("sys_quartz_job")
                .WHERE("job_id = #{jobId}")
                .toString();
        return sql;
    }

    public String insertSQL() {
        String sql = new SQL()
                .INSERT_INTO("sys_quartz_job")
                .INTO_COLUMNS("bean_name", "cron_expression", "is_pause", "job_name", "method_name", "params", "description",
                        "person_in_charge", "sub_task", "pause_after_failure", "create_by", "update_by", "create_time",
                        "update_time")
                .INTO_VALUES("#{beanName}", "#{cronExpression}", "#{isPause}", "#{jobName}", "#{methodName}", "#{params}",
                        "#{description}", "#{personInCharge}", "#{subTask}", "#{pauseAfterFailure}", "#{createBy}",
                        "#{updateBy}", "#{createTime}", "#{updateTime}")
                .toString();
        return sql;
    }

    public String updateSQL() {
        String sql = new SQL()
                .UPDATE("sys_quartz_job")
                .SET("bean_name = #{beanName}")
                .SET("cron_expression = #{cronExpression}")
                .SET("is_pause = #{isPause}")
                .SET("job_name = #{jobName}")
                .SET("method_name = #{methodName}")
                .SET("params = #{params}")
                .SET("description = #{description}")
                .SET("person_in_charge = #{personInCharge}")
                .SET("sub_task = #{subTask}")
                .SET("pause_after_failure = #{pauseAfterFailure}")
                .SET("create_by = #{createBy}")
                .SET("update_by = #{updateBy}")
                .SET("create_time = #{createTime}")
                .SET("update_time = #{updateTime}")
                .WHERE("job_id = #{jobId}")
                .toString();
        return sql;
    }

    public String deleteByIdSQL() {
        String sql = new SQL()
                .DELETE_FROM("sys_quartz_job")
                .WHERE("job_id = #{jobId}")
                .toString();
        return sql;
    }

    public String selectByIsPauseSQL() {
        String sql = new SQL()
                .SELECT("*")
                .FROM("sys_quartz_job")
                .WHERE("is_pause = #{isPause}")
                .toString();
        return sql;
    }
}
