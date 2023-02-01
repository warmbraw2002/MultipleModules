package com.tiktok.service;


import com.tiktok.PageVo;
import com.tiktok.Result;
import com.tiktok.domain.Dict;

import java.util.List;

public interface DictService {

    Result<Dict> getDictById(Long dictId);
    Result<Boolean> insertDict(Dict dict);
    Result<Boolean> updateDict(Dict dict) throws Exception;
    Result<Boolean> deleteDictById(Long dictId);
    Result<PageVo> getDictList(Integer pageSize, Integer pageNum);
    List<Dict> getDictList();
}
