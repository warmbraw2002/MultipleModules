package com.tiktok.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
* 
* @TableName sys_quartz_job
*/
@Data
public class QuartzJob implements Serializable {

    /**
    * ID
    */
    @NotNull(message="[ID]不能为空")
    @ApiModelProperty("ID")
    private Long jobId;
    /**
    * Spring Bean名称
    */
    @Size(max= 255,message="Spring Bean名称长度不能超过255")
    @ApiModelProperty("Spring Bean名称")
    @Length(max= 255,message="Spring Bean名称长度不能超过255")
    private String beanName;
    /**
    * cron表达式
    */
    @Size(max= 255,message="cron表达式长度不能超过255")
    @ApiModelProperty("cron表达式")
    @Length(max= 255,message="cron表达式长度不能超过255")
    private String cronExpression;
    /**
    * 状态：1暂停、0启用
    */
    @ApiModelProperty("状态：1暂停、0启用")
    private Boolean isPause;
    /**
    * 任务名称
    */
    @Size(max= 255,message="任务名称长度不能超过255")
    @ApiModelProperty("任务名称")
    @Length(max= 255,message="任务名称长度不能超过255")
    private String jobName;
    /**
    * 方法名称
    */
    @Size(max= 255,message="方法名称长度不能超过255")
    @ApiModelProperty("方法名称")
    @Length(max= 255,message="方法名称长度不能超过255")
    private String methodName;
    /**
    * 参数
    */
    @Size(max= 255,message="参数长度不能超过255")
    @ApiModelProperty("参数")
    @Length(max= 255,message="参数长度不能超过255")
    private String params;
    /**
    * 备注
    */
    @Size(max= 255,message="备注长度不能超过255")
    @ApiModelProperty("备注")
    @Length(max= 255,message="备注长度不能超过255")
    private String description;
    /**
    * 负责人
    */
    @Size(max= 100,message="负责人长度不能超过100")
    @ApiModelProperty("负责人")
    @Length(max= 100,message="负责人长度不能超过100")
    private String personInCharge;
    /**
    * 报警邮箱
    */
    @Size(max= 100,message="报警邮箱长度不能超过100")
    @ApiModelProperty("报警邮箱")
    @Length(max= 100,message="报警邮箱长度不能超过100")
    private String email;
    /**
    * 子任务ID
    */
    @Size(max= 100,message="子任务ID长度不能超过100")
    @ApiModelProperty("子任务ID")
    @Length(max= 100,message="子任务ID长度不能超过100")
    private String subTask;
    /**
    * 任务失败后是否暂停
    */
    @ApiModelProperty("任务失败后是否暂停")
    private Boolean pauseAfterFailure;
    /**
    * 创建者
    */
    @Size(max= 255,message="创建者长度不能超过255")
    @ApiModelProperty("创建者")
    @Length(max= 255,message="创建者长度不能超过255")
    private String createBy;
    /**
    * 更新者
    */
    @Size(max= 255,message="更新者长度不能超过255")
    @ApiModelProperty("更新者")
    @Length(max= 255,message="更新者长度不能超过255")
    private String updateBy;
    /**
    * 创建日期
    */
    @ApiModelProperty("创建日期")
    private Date createTime;
    /**
    * 更新日期
    */
    @ApiModelProperty("更新日期")
    private Date updateTime;
}
