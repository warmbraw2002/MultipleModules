package com.tiktok.mapper;

import org.apache.ibatis.jdbc.SQL;

/**
 * @author DPH
 * @date 2023/1/16
 */
public class UserProvider {

    public String selectSQL(String username) {
        String sql = new SQL()
                .SELECT("*")
                .FROM("sys_user")
                .WHERE("username = #{username}")
                .toString();
        return sql;
    }
}
