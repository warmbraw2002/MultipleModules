package com.tiktok.domain;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
* 
* @TableName sys_user
*/
@Data
public class User implements Serializable {

    /**
    * 
    */
    @ApiModelProperty("")
    private Long userId;
    /**
    * 
    */
    @ApiModelProperty("")
    private Long deptId;
    /**
    * 
    */
    @ApiModelProperty("")
    private String username;
    /**
    * 
    */
    @ApiModelProperty("")
    private String nickName;
    /**
    * 
    */
    @ApiModelProperty("")
    private String gender;
    /**
    * 
    */
    @ApiModelProperty("")
    private String phone;
    /**
    * 
    */
    @ApiModelProperty("")
    private String email;
    /**
    * 
    */
    @ApiModelProperty("")
    private String avatarName;
    /**
    * 
    */
    @ApiModelProperty("")
    private String avatarPath;
    /**
    * 
    */
    @ApiModelProperty("")
    private String password;
    /**
    * 
    */
    @ApiModelProperty("")
    private Boolean isAdmin;
    /**
    * 
    */
    @ApiModelProperty("")
    private Boolean enabled;
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
    private Date pwdResetTime;
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
