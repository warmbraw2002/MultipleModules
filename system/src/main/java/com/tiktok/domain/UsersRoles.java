package com.tiktok.domain;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
* 
* @TableName sys_users_roles
*/
@Data
public class UsersRoles implements Serializable {

    /**
    * 
    */
    @ApiModelProperty("")
    private Long userId;
    /**
    * 
    */
    @ApiModelProperty("")
    private Long roleId;
}
