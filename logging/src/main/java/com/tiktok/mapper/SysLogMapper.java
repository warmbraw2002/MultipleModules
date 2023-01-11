package com.tiktok.mapper;

import com.tiktok.domain.SysLog;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;

/**
* @description 针对表【sys_log】的数据库操作Mapper
* @Entity com.tiktok.domain.SysLog
*/
@Mapper
public interface SysLogMapper {
    @InsertProvider(type = SysLogProvider.class, method = "insertSQL")
    int insert(SysLog sysLog);
}




