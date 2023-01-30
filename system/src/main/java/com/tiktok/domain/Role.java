package com.tiktok.domain;


import java.io.Serializable;

import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
* 
* @TableName sys_role
*/
@Data
public class Role implements Serializable {

    /**
    * 
    */
    @ApiModelProperty("")
    private Long roleId;
    /**
    * 
    */
    @ApiModelProperty("")
    private String name;
    /**
    * 
    */
    @ApiModelProperty("")
    private Integer level;
    /**
    * 
    */
    @ApiModelProperty("")
    private String description;
    /**
    * 
    */
    @ApiModelProperty("")
    private String dataScope;
    /**
    * 
    */
    @ApiModelProperty("")
    private String createBy;
    /**
    * 
    */
    @ApiModelProperty("")
    private String updateBy;
    /**
    * 
    */
    @ApiModelProperty("")
    private Date createTime;
    /**
    * 
    */
    @ApiModelProperty("")
    private Date updateTime;
}
