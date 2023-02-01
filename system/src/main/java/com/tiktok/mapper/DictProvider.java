package com.tiktok.mapper;


import org.apache.ibatis.jdbc.SQL;

public class DictProvider {

    public String selectByIdSQL() {
        String sql = new SQL()
                .SELECT("*")
                .FROM("sys_dict")
                .WHERE("dict_id = #{dictId}")
                .toString();
        return sql;
    }

    public String insertSQL() {
        String sql = new SQL()
                .INSERT_INTO("sys_dict")
                .VALUES("name", "#{name}")
                .VALUES("description", "#{description}")
                .VALUES("create_by", "#{createBy}")
                .VALUES("update_by", "#{updateBy}")
                .VALUES("create_time", "#{createTime}")
                .VALUES("update_time", "#{updateTime}")
                .toString();
        return sql;
    }

    public String updateSQL() {
        String sql = new SQL()
                .UPDATE("sys_dict")
                .SET("name = #{name}")
                .SET("description = #{description}")
                .SET("create_by = #{createBy}")
                .SET("update_by = #{updateBy}")
                .SET("create_time = #{createTime}")
                .SET("update_time = #{updateTime}")
                .WHERE("dict_id = #{dictId}")
                .toString();
        return sql;
    }

    public String deleteByIdSQL() {
        String sql = new SQL()
                .DELETE_FROM("sys_dict")
                .WHERE("dict_id = #{dictId}")
                .toString();
        return sql;
    }

    public String selectAllSQL() {
        String sql = new SQL()
                .SELECT("*")
                .FROM("sys_dict")
                .toString();
        return sql;
    }
}
