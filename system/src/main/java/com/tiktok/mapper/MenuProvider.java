package com.tiktok.mapper;

import org.apache.ibatis.jdbc.SQL;

/**
 * @author DPH
 * @date 2023/1/30
 */
public class MenuProvider {

    public String insertSQL() {
        String sql = new SQL()
                .INSERT_INTO("sys_menu")
                .VALUES("pid", "#{pid}")
                .VALUES("sub_count", "#{subCount}")
                .VALUES("type", "#{type}")
                .VALUES("title", "#{title}")
                .VALUES("name", "#{name}")
                .VALUES("component", "#{component}")
                .VALUES("menu_sort", "#{menuSort}")
                .VALUES("icon", "#{icon}")
                .VALUES("path", "#{path}")
                .VALUES("i_frame", "#{iFrame}")
                .VALUES("cache", "#{cache}")
                .VALUES("hidden", "#{hidden}")
                .VALUES("permission", "#{permission}")
                .VALUES("create_by", "#{createBy}")
                .VALUES("update_by", "#{updateBy}")
                .VALUES("create_time", "#{createTime}")
                .VALUES("update_time", "#{updateTime}")
                .toString();
        return sql;
    }

    public String updateSQL() {
        String sql = new SQL()
                .UPDATE("sys_menu")
                .SET("pid = #{pid}")
                .SET("sub_count = #{subCount}")
                .SET("type = #{type}")
                .SET("title = #{title}")
                .SET("name = #{name}")
                .SET("component = #{component}")
                .SET("menu_sort = #{menuSort}")
                .SET("icon = #{icon}")
                .SET("path = #{path}")
                .SET("i_frame = #{iFrame}")
                .SET("cache = #{cache}")
                .SET("hidden = #{hidden}")
                .SET("permission = #{permission}")
                .SET("create_by = #{createBy}")
                .SET("update_by = #{updateBy}")
                .SET("create_time = #{createTime}")
                .SET("update_time = #{updateTime}")
                .WHERE("menu_id=#{menuId}")
                .toString();
        return sql;
    }

    public String selectByIdSQL() {
        String sql = new SQL()
                .SELECT("*")
                .FROM("sys_menu")
                .WHERE("menu_id=#{menuId}")
                .toString();
        return sql;
    }

    public String deleteByIdSQL() {
        String sql = new SQL()
                .DELETE_FROM("sys_menu")
                .WHERE("menu_id=#{menuId}")
                .toString();
        return sql;
    }

    public String selectSubSQL() {
        String sql = new SQL()
                .SELECT("*")
                .FROM("sys_menu")
                .WHERE("pid=#{menuId}")
                .toString();

        sql = new SQL()
                .SELECT("m.*")
                .FROM("sys_user u")
                .LEFT_OUTER_JOIN("sys_users_roles ur on u.user_id = ur.user_id")
                .LEFT_OUTER_JOIN("sys_role r on ur.role_id = r.role_id")
                .LEFT_OUTER_JOIN("sys_menu m on m.permission like CONCAT('%',r.name,'%')")
                .WHERE("u.username = #{username}").AND().WHERE("m.pid = #{menuId}")
                .toString();
        return sql;
    }

    public String selectPeerSQL() {
        String sql = new SQL()
                .SELECT("m.*")
                .FROM("sys_user u")
                .LEFT_OUTER_JOIN("sys_users_roles ur on u.user_id = ur.user_id")
                .LEFT_OUTER_JOIN("sys_role r on ur.role_id = r.role_id")
                .LEFT_OUTER_JOIN("sys_menu m on m.permission like CONCAT('%',r.name,'%')")
                .WHERE("u.username = #{username}").AND()
                .WHERE("m.pid=(SELECT pid FROM menu WHERE menu_id=#{menuId})")
                .toString();
        return sql;
    }

    public String selectParentSQL() {
        String sql = new SQL()
                .SELECT("m.*")
                .FROM("sys_user u")
                .LEFT_OUTER_JOIN("sys_users_roles ur on u.user_id = ur.user_id")
                .LEFT_OUTER_JOIN("sys_role r on ur.role_id = r.role_id")
                .LEFT_OUTER_JOIN("sys_menu m on m.permission like CONCAT('%',r.name,'%')")
                .WHERE("u.username = #{username}").AND()
                .WHERE("m.menu_id=(SELECT pid FROM menu WHERE menu_id=#{menuId})")
                .toString();
        return sql;
    }

    public String selectRootMenuSQL() {
        String sql = new SQL()
                .SELECT("m.*")
                .FROM("sys_user u")
                .LEFT_OUTER_JOIN("sys_users_roles ur on u.user_id = ur.user_id")
                .LEFT_OUTER_JOIN("sys_role r on ur.role_id = r.role_id")
                .LEFT_OUTER_JOIN("sys_menu m on m.permission like CONCAT('%',r.name,'%')")
                .WHERE("u.username = #{username}").AND()
                .WHERE("m.pid is null")
                .toString();
        return sql;
    }
}
