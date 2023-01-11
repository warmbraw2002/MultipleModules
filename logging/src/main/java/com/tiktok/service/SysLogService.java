package com.tiktok.service;


import com.tiktok.domain.SysLog;

/**
* @description 针对表【sys_log】的数据库操作Service
*/
public interface SysLogService {
    public boolean saveLog(SysLog sysLog);
}
