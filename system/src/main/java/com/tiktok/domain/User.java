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
* @TableName sys_user
*/
@Data
public class User implements Serializable {

    /**
    * ID
    */
    @NotNull(message="[ID]不能为空")
    @ApiModelProperty("ID")
    private Long userId;
    /**
    * 部门名称
    */
    @ApiModelProperty("部门名称")
    private Long deptId;
    /**
    * 用户名
    */
    @NotBlank(message="[用户名]不能为空")
    @Size(max= 255,message="用户名长度不能超过255")
    @ApiModelProperty("用户名")
    @Length(max= 255,message="用户名长度不能超过255")
    private String username;
    /**
    * 昵称
    */
    @Size(max= 255,message="昵称长度不能超过255")
    @ApiModelProperty("昵称")
    @Length(max= 255,message="昵称长度不能超过255")
    private String nickName;
    /**
    * 性别
    */
    @Size(max= 2,message="性别长度不能超过2")
    @ApiModelProperty("性别")
    @Length(max= 2,message="性别长度不能超过2")
    private String gender;
    /**
    * 手机号码
    */
    @Size(max= 255,message="手机号码长度不能超过255")
    @ApiModelProperty("手机号码")
    @Length(max= 255,message="手机号码长度不能超过255")
    private String phone;
    /**
    * 邮箱
    */
    @Size(max= 255,message="邮箱长度不能超过255")
    @ApiModelProperty("邮箱")
    @Length(max= 255,message="邮箱长度不能超过255")
    private String email;
    /**
    * 头像地址
    */
    @Size(max= 255,message="头像地址长度不能超过255")
    @ApiModelProperty("头像地址")
    @Length(max= 255,message="头像地址长度不能超过255")
    private String avatarName;
    /**
    * 头像真实路径
    */
    @Size(max= 255,message="头像真实路径长度不能超过255")
    @ApiModelProperty("头像真实路径")
    @Length(max= 255,message="头像真实路径长度不能超过255")
    private String avatarPath;
    /**
    * 密码
    */
    @Size(max= 255,message="密码长度不能超过255")
    @ApiModelProperty("密码")
    @Length(max= 255,message="密码长度不能超过255")
    private String password;
    /**
    * 是否为admin账号
    */
    @ApiModelProperty("是否为admin账号")
    private Boolean isAdmin;
    /**
    * 状态
    */
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
    * 修改密码的时间
    */
    @ApiModelProperty("修改密码的时间")
    private Date pwdResetTime;
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
