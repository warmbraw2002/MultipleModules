package com.tiktok.mapper;

import com.tiktok.domain.SysLog;
import org.apache.ibatis.jdbc.SQL;

public class SysLogProvider {

    public String insertSQL(SysLog sysLog) {
        String sql = new SQL()
                .INSERT_INTO("sys_log")
                .VALUES("description", "#{description}")
                .VALUES("log_type", "#{logType}")
                .VALUES("method", "#{method}")
                .VALUES("params", "#{params}")
                .VALUES("request_ip", "#{requestIp}")
                .VALUES("time", "#{time}")
                .VALUES("username", "#{username}")
                .VALUES("address", "#{address}")
                .VALUES("browser", "#{browser}")
                .VALUES("exception_detail", "#{exceptionDetail}")
                .VALUES("create_time", "#{createTime}")
                .toString();
        return sql;
    }
}
