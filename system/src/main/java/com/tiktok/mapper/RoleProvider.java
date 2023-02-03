package com.tiktok.mapper;

import org.apache.ibatis.jdbc.SQL;

public class RoleProvider {

    public String selectByIdSQL() {
        String sql = new SQL()
                .SELECT("*")
                .FROM("sys_role")
                .WHERE("role_id = #{roleId}")
                .toString();
        return sql;
    }

    public String insertSQL() {
        String sql = new SQL()
                .INSERT_INTO("sys_role")
                .INTO_COLUMNS("name", "level", "description", "data_scope", "create_by", "create_time", "update_by", "update_time")
                .INTO_VALUES("#{name}", "#{level}", "#{description}", "#{dataScope}", "#{createBy}", "#{createTime}", "#{updateBy}", "#{updateTime}")
                .toString();
        return sql;
    }

    public String updateSQL() {
        String sql = new SQL()
                .UPDATE("sys_role")
                .SET("name = #{name}")
                .SET("level = #{level}")
                .SET("description = #{description}")
                .SET("data_scope = #{dataScope}")
                .SET("create_by = #{createBy}")
                .SET("create_time = #{createTime}")
                .SET("update_by = #{updateBy}")
                .SET("update_time = #{updateTime}")
                .WHERE("role_id = #{roleId}")
                .toString();
        return sql;
    }

    public String deleteByIdSQL() {
        String sql = new SQL()
                .DELETE_FROM("sys_role")
                .WHERE("role_id = #{roleId}")
                .toString();
        return sql;
    }

    public String selectAllSQL() {
        String sql = new SQL()
                .SELECT("*")
                .FROM("sys_role")
                .toString();
        return sql;
    }

    public String selectByUserIdSQL() {
        String sql = new SQL()
                .SELECT("r.*")
                .FROM("sys_role r")
                .JOIN("sys_users_roles ur on r.role_id = ur.role_id")
                .WHERE("ur.user_id = #{userId}")
                .toString();
        return sql;
    }

    public String updatePermissionSQL() {
        String sql = new SQL()
                .UPDATE("sys_menu")
                .SET("permission = #{permission}")
                .WHERE("menu_id=#{menuId}")
                .toString();
        return sql;
    }
}
