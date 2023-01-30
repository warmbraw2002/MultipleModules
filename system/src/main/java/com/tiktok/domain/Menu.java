package com.tiktok.domain;

import java.io.Serializable;

import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
* 
* @TableName sys_menu
*/
@Data
public class Menu implements Serializable {

    /**
    * 
    */
    @ApiModelProperty("")
    private Long menuId;
    /**
    * 
    */
    @ApiModelProperty("")
    private Long pid;
    /**
    * 
    */
    @ApiModelProperty("")
    private Integer subCount;
    /**
    * 
    */
    @ApiModelProperty("")
    private Integer type;
    /**
    * 
    */
    @ApiModelProperty("")
    private String title;
    /**
    * 
    */
    @ApiModelProperty("")
    private String name;
    /**
    * 
    */
    @ApiModelProperty("")
    private String component;
    /**
    * 
    */
    @ApiModelProperty("")
    private Integer menuSort;
    /**
    * 
    */
    @ApiModelProperty("")
    private String icon;
    /**
    * 
    */
    @ApiModelProperty("")
    private String path;
    /**
    * 
    */
    @ApiModelProperty("")
    private Boolean iFrame;
    /**
    * 
    */
    @ApiModelProperty("")
    private Boolean cache;
    /**
    * 
    */
    @ApiModelProperty("")
    private Boolean hidden;
    /**
    * 
    */
    @ApiModelProperty("")
    private String permission;
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
