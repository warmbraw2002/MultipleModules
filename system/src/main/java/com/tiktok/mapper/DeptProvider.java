package com.tiktok.mapper;


import org.apache.ibatis.jdbc.SQL;

public class DeptProvider {

    public String selectByIdSQL() {
        String sql = new SQL()
                .SELECT("*")
                .FROM("sys_dept")
                .WHERE("dept_id=#{deptId}")
                .toString();
        return sql;
    }

    public String insertSQL() {
        String sql = new SQL()
                .INSERT_INTO("sys_dept")
                .VALUES("pid", "#{pid}")
                .VALUES("sub_count", "#{subCount}")
                .VALUES("dept_name", "#{deptName}")
                .VALUES("leader", "#{leader}")
                .VALUES("phone", "#{phone}")
                .VALUES("email", "#{email}")
                .VALUES("status", "#{status}")
                .VALUES("dept_sort", "#{deptSort}")
                .VALUES("create_by", "#{createBy}")
                .VALUES("update_by", "#{updateBy}")
                .VALUES("create_time", "#{createTime}")
                .VALUES("update_time", "#{updateTime}")
                .toString();
        return sql;
    }

    public String updateSQL() {
        String sql = new SQL()
                .UPDATE("sys_dept")
                .SET("pid = #{pid}")
                .SET("sub_count = #{subCount}")
                .SET("dept_name = #{deptName}")
                .SET("leader = #{leader}")
                .SET("phone = #{phone}")
                .SET("email = #{email}")
                .SET("status = #{status}")
                .SET("dept_sort = #{deptSort}")
                .SET("create_by = #{createBy}")
                .SET("update_by = #{updateBy}")
                .SET("create_time = #{createTime}")
                .SET("update_time = #{updateTime}")
                .WHERE("dept_id=#{deptId}")
                .toString();
        return sql;
    }

    public String deleteByIdSQL() {
        String sql = new SQL()
                .DELETE_FROM("sys_dept")
                .WHERE("dept_id=#{deptId}")
                .toString();
        return sql;
    }

    public String selectAllSQL() {
        String sql = new SQL()
                .SELECT("*")
                .FROM("sys_dept")
                .toString();
        return sql;
    }
}
