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
* @TableName sys_dept
*/
@Data
public class Dept implements Serializable {

    /**
    * ID
    */
    @NotNull(message="[ID]不能为空")
    @ApiModelProperty("ID")
    private Long deptId;
    /**
    * 上级部门
    */
    @ApiModelProperty("上级部门")
    private Long pid;
    /**
    * 
    */
    @ApiModelProperty("子部门数目")
    private Integer subCount;
    /**
    * 名称
    */
    @NotBlank(message="[名称]不能为空")
    @Size(max= 255,message="名称长度不能超过255")
    @ApiModelProperty("名称")
    @Length(max= 255,message="名称长度不能超过255")
    private String name;
    /**
    * 排序
    */
    @ApiModelProperty("排序")
    private Integer deptSort;
    /**
    * 状态
    */
    @NotNull(message="[状态]不能为空")
    @ApiModelProperty("状态")
    private Boolean enabled;
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
    * 更新时间
    */
    @ApiModelProperty("更新时间")
    private Date updateTime;
}
