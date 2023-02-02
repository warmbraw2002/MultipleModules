package com.tiktok.service;

import com.tiktok.PageVo;
import com.tiktok.Result;
import com.tiktok.domain.DictDetail;

import java.util.List;

public interface DictDetailService {

    Result<DictDetail> getDictDetailById(Long detailId);
    Result<Boolean> insertDictDetail(DictDetail dictDetail);
    Result<Boolean> updateDictDetail(DictDetail dictDetail) throws Exception;
    Result<Boolean> deleteDictDetailById(Long deptId);
    Result<PageVo> getDictDetailList(Integer pageNum, Integer pageSize);
}
