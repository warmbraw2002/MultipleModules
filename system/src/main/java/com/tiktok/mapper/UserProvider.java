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

    public String insertSQL() {
        String sql = new SQL()
                .INSERT_INTO("sys_user")
                .VALUES("dept_id", "#{deptId}")
                .VALUES("username", "#{username}")
                .VALUES("nick_name", "#{nickName}")
                .VALUES("gender", "#{gender}")
                .VALUES("email", "#{email}")
                .VALUES("avatar_name", "#{avatarName}")
                .VALUES("avatar_path", "#{avatarPath}")
                .VALUES("password", "#{password}")
                .VALUES("is_admin", "#{isAdmin}")
                .VALUES("enabled", "#{enabled}")
                .VALUES("create_by", "#{createBy}")
                .VALUES("update_by", "#{updateBy}")
                .VALUES("pwd_reset_time", "#{pwdResetTime}")
                .VALUES("create_time", "#{createTime}")
                .VALUES("update_time", "#{updateTime}")
                .toString();
        return sql;
    }

    public String updateSQL() {
        String sql = new SQL()
                .UPDATE("sys_user")
                .SET("dept_id = #{deptId}")
                .SET("username = #{username}")
                .SET("nick_name = #{nickName}")
                .SET("gender = #{gender}")
                .SET("email = #{email}")
                .SET("avatar_name = #{avatarName}")
                .SET("avatar_path = #{avatarPath}")
                .SET("password = #{password}")
                .SET("is_admin = #{isAdmin}")
                .SET("enabled = #{enabled}")
                .SET("create_by = #{createBy}")
                .SET("update_by = #{updateBy}")
                .SET("pwd_reset_time = #{pwdResetTime}")
                .SET("create_time = #{createTime}")
                .SET("update_time = #{updateTime}")
                .WHERE("user_id=#{userId}")
                .toString();
        return sql;
    }

    public String deleteByNameSQL() {
        String sql = new SQL()
                .DELETE_FROM("sys_user")
                .WHERE("username = #{username}")
                .toString();
        return sql;
    }

    public String selectAllSQL() {
        String sql = new SQL()
                .SELECT("*")
                .FROM("sys_user")
                .toString();
        return sql;
    }
}
