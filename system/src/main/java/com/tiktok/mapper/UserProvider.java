package com.tiktok.mapper;

import org.apache.ibatis.jdbc.SQL;

/**
 * @author DPH
 * @date 2023/1/16
 */
public class UserProvider {

    public String selectSQL() {
        String sql = new SQL()
                .SELECT("*")
                .FROM("sys_user")
                .WHERE("username = #{username}")
                .toString();
        return sql;
    }

    public String selectRolesSQL() {
        String sql = new SQL()
                .SELECT("r.*")
                .FROM("sys_user u")
                .LEFT_OUTER_JOIN("sys_users_roles ur on u.user_id = ur.user_id")
                .LEFT_OUTER_JOIN("sys_role r on ur.role_id = r.role_id")
                .WHERE("u.username = #{username}")
                .toString();
        return sql;
    }
}
