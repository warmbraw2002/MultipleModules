package com.tiktok.mapper;

import com.tiktok.domain.Dict;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DictMapper {

    @SelectProvider(type = DictProvider.class, method = "selectByIdSQL")
    @Results(id = "dictMap", value = {
            @Result(property = "dictId", column = "dict_id"),
            @Result(property = "createBy", column = "create_by"),
            @Result(property = "updateBy", column = "update_by"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time"),
    })
    Dict findDictById(Long dictId);

    @InsertProvider(type = DictProvider.class, method = "insertSQL")
    @Options(useGeneratedKeys = true, keyProperty = "dictId", keyColumn = "dict_id")
    int insertDict(Dict dict);

    @UpdateProvider(type = DictProvider.class, method = "updateSQL")
    int updateDict(Dict dict);

    @DeleteProvider(type = DictProvider.class, method = "deleteByIdSQL")
    int deleteDictById(Long dictId);

    @SelectProvider(type = DictProvider.class, method = "selectAllSQL")
    @ResultMap("deptMap")
    List<Dict> findAllDict();
}
