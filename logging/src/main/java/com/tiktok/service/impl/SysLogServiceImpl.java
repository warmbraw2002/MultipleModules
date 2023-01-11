package com.tiktok.service.impl;


import com.tiktok.domain.SysLog;
import com.tiktok.mapper.SysLogMapper;
import com.tiktok.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @description 针对表【sys_log】的数据库操作Service实现
*/
@Service
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogMapper sysLogMapper;

    @Override
    public boolean saveLog(SysLog sysLog) {
        return sysLogMapper.insert(sysLog) > 0;
    }
}




