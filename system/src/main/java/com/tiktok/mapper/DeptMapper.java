package com.tiktok.mapper;


import com.tiktok.domain.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {
    @SelectProvider(type = DeptProvider.class, method = "selectByIdSQL")
    @Results(id = "deptMap", value = {
            @Result(property = "deptId", column = "dept_id"),
            @Result(property = "subCount", column = "sub_count"),
            @Result(property = "deptSort", column = "dept_sort"),
            @Result(property = "createBy", column = "create_by"),
            @Result(property = "updateBy", column = "update_by"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time"),
    })
    Dept findDeptById(Long deptId);

    @InsertProvider(type = DeptProvider.class, method = "insertSQL")
    @Options(useGeneratedKeys = true, keyProperty = "deptId", keyColumn = "dept_id")
    int insertDept(Dept dept);

    @UpdateProvider(type = DeptProvider.class, method = "updateSQL")
    int updateDept(Dept dept);

    @DeleteProvider(type = DeptProvider.class, method = "deleteByIdSQL")
    int deleteDeptById(Long deptId);

    @SelectProvider(type = DeptProvider.class, method = "selectAllSQL")
    @ResultMap("deptMap")
    List<Dept> findAllDept();
}
