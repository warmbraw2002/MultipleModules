package com.tiktok.mapper;

import org.apache.ibatis.jdbc.SQL;

public class DictDetailProvider {

    public String selectByIdSQL() {
        String sql = new SQL()
                .SELECT("*")
                .FROM("sys_dict_detail")
                .WHERE("detail_id = #{detailId}")
                .toString();
        return sql;
    }

    public String insertSQL() {
        String sql = new SQL()
                .INSERT_INTO("sys_dict_detail")
                .INTO_COLUMNS("dict_id", "label", "value", "dict_sort", "create_by", "update_by", "create_time", "update_time")
                .INTO_VALUES("#{dictId}", "#{label}", "#{value}", "#{dictSort}", "#{createBy}", "#{updateBy}", "#{createTime}", "#{updateTime}")
                .toString();
        return sql;
    }

    public String updateSQL() {
        String sql = new SQL()
                .UPDATE("sys_dict_detail")
                .SET("dict_id = #{dictId}")
                .SET("label = #{label}")
                .SET("value = #{value}")
                .SET("dict_sort = #{dictSort}")
                .SET("create_by = #{createBy}")
                .SET("update_by = #{updateBy}")
                .SET("create_time = #{createTime}")
                .SET("update_time = #{updateTime}")
                .WHERE("detail_id = #{detailId}")
                .toString();
        return sql;
    }

    public String deleteByIdSQL() {
        String sql = new SQL()
                .DELETE_FROM("sys_dict_detail")
                .WHERE("detail_id = #{detailId}")
                .toString();
        return sql;
    }

    public String selectAllSQL() {
        String sql = new SQL()
                .SELECT("*")
                .FROM("sys_dict_detail")
                .toString();
        return sql;
    }
}
