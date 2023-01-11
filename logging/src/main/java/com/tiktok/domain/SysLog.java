package com.tiktok.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
* 
* @TableName sys_log
*/
@Data
public class SysLog implements Serializable {

    private Long logId;
    /**
    * 
    */
    private String description;
    /**
    * 
    */
    private String logType;
    /**
    * 
    */
    private String method;
    /**
    * 
    */
    private String params;
    /**
    * 
    */
    private String requestIp;
    /**
    * 
    */
    private Long time;
    /**
    * 
    */
    private String username;
    /**
    * 
    */
    private String address;
    /**
    * 
    */
    private String browser;
    /**
    * 
    */
    private String exceptionDetail;
    /**
    * 
    */
    private Date createTime;
}
