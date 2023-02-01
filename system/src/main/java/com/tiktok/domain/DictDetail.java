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
* @TableName sys_dict_detail
*/
@Data
public class DictDetail implements Serializable {

    /**
    * ID
    */
    @NotNull(message="[ID]不能为空")
    @ApiModelProperty("ID")
    private Long detailId;
    /**
    * 字典id
    */
    @NotNull(message="[字典id]不能为空")
    @ApiModelProperty("字典id")
    private Long dictId;
    /**
    * 字典标签
    */
    @NotBlank(message="[字典标签]不能为空")
    @Size(max= 255,message="字典标签长度不能超过255")
    @ApiModelProperty("字典标签")
    @Length(max= 255,message="字典标签长度不能超过255")
    private String label;
    /**
    * 字典值
    */
    @NotBlank(message="[字典值]不能为空")
    @Size(max= 255,message="字典值长度不能超过255")
    @ApiModelProperty("字典值")
    @Length(max= 255,message="字典值长度不能超过255")
    private String value;
    /**
    * 排序
    */
    @ApiModelProperty("排序")
    private Integer dictSort;
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
