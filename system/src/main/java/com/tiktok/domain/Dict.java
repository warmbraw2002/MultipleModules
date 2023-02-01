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
* @TableName sys_dict
*/
@Data
public class Dict implements Serializable {

    /**
    * 
    */
    @NotNull(message="[ID]不能为空")
    @ApiModelProperty("ID")
    private Long dictId;
    /**
    * 
    */
    @NotBlank(message="[字典名称]不能为空")
    @Size(max= 255,message="字典名称长度不能超过255")
    @ApiModelProperty("字典名称")
    @Length(max= 255,message="字典名称长度不能超过255")
    private String name;
    /**
    * 
    */
    @Size(max= 255,message="描述长度不能超过255")
    @ApiModelProperty("描述")
    @Length(max= 255,message="描述长度不能超过255")
    private String description;
    /**
    * 
    */
    @Size(max= 255,message="创建者长度不能超过255")
    @ApiModelProperty("创建者")
    @Length(max= 255,message="创建者长度不能超过255")
    private String createBy;
    /**
    * 
    */
    @Size(max= 255,message="更新者长度不能超过255")
    @ApiModelProperty("更新者")
    @Length(max= 255,message="更新者长度不能超过255")
    private String updateBy;
    /**
    * 
    */
    @ApiModelProperty("创建日期")
    private Date createTime;
    /**
    * 
    */
    @ApiModelProperty("更新时间")
    private Date updateTime;
}
