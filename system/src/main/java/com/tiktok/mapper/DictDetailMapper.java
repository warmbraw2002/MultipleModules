package com.tiktok.mapper;

import com.tiktok.domain.DictDetail;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DictDetailMapper {
    @SelectProvider(type = DictDetailMapper.class, method = "selectByIdSQL")
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

    @InsertProvider(type = DictDetailMapper.class, method = "insertSQL")
    int insertDictDetail(DictDetail dictDetail);

    @UpdateProvider(type = DictDetailMapper.class, method = "updateSQL")
    int updateDictDetail(DictDetail dictDetail);

    @DeleteProvider(type = DictDetailMapper.class, method = "deleteByIdSQL")
    int deleteDictDetailById(Long detailId);

    @SelectProvider(type = DictDetailMapper.class, method = "selectAllSQL")
    @ResultMap("dictDetailMap")
    List<DictDetail> findAllDictDetail();
}
