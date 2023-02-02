package com.tiktok.mapper;

import com.tiktok.domain.DictDetail;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DictDetailMapper {
    @SelectProvider(type = DictDetailProvider.class, method = "selectByIdSQL")
    @Results(id = "dictDetailMap", value = {
            @Result(property = "detailId", column = "detail_id"),
            @Result(property = "dictId", column = "dict_id"),
            @Result(property = "dictSort", column = "dict_sort"),
            @Result(property = "createBy", column = "create_by"),
            @Result(property = "updateBy", column = "update_by"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time"),
    })
    DictDetail findDictDetailById(Long detailId);

    @InsertProvider(type = DictDetailProvider.class, method = "insertSQL")
    @Options(useGeneratedKeys = true, keyProperty = "detailId", keyColumn = "detail_id")
    int insertDictDetail(DictDetail dictDetail);

    @UpdateProvider(type = DictDetailProvider.class, method = "updateSQL")
    int updateDictDetail(DictDetail dictDetail);

    @DeleteProvider(type = DictDetailProvider.class, method = "deleteByIdSQL")
    int deleteDictDetailById(Long detailId);

    @SelectProvider(type = DictDetailProvider.class, method = "selectAllSQL")
    @ResultMap("dictDetailMap")
    List<DictDetail> findAllDictDetail();
}
