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
* @TableName sys_role
*/
@Data
public class Role implements Serializable {

    /**
    * ID
    */
    @NotNull(message="[ID]不能为空")
    @ApiModelProperty("ID")
    private Long roleId;
    /**
    * 名称
    */
    @NotBlank(message="[名称]不能为空")
    @Size(max= 255,message="名称长度不能超过255")
    @ApiModelProperty("名称")
    @Length(max= 255,message="名称长度不能超过255")
    private String name;
    /**
    * 角色级别
    */
    @ApiModelProperty("角色级别")
    private Integer level;
    /**
    * 描述
    */
    @Size(max= 255,message="描述长度不能超过255")
    @ApiModelProperty("描述")
    @Length(max= 255,message="描述长度不能超过255")
    private String description;
    /**
    * 数据权限
    */
    @Size(max= 255,message="数据权限长度不能超过255")
    @ApiModelProperty("数据权限")
    @Length(max= 255,message="数据权限长度不能超过255")
    private String dataScope;
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
