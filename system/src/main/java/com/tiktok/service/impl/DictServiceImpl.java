package com.tiktok.service.impl;

import com.tiktok.ErrorEnum;
import com.tiktok.PageVo;
import com.tiktok.Result;
import com.tiktok.domain.Dict;
import com.tiktok.mapper.DictMapper;
import com.tiktok.service.DictService;
import com.tiktok.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictServiceImpl implements DictService {

    @Autowired
    private DictMapper dictMapper;

    @Override
    public Result<Dict> getDictById(Long dictId) {
        Dict dict = dictMapper.findDictById(dictId);
        if (dict == null) {
            return Result.error(ErrorEnum.NOT_FOUND);
        }
        return Result.of(dict);
    }

    @Override
    public Result<Boolean> insertDict(Dict dict) {
        return Result.of(dictMapper.insertDict(dict) > 0);
    }

    @Override
    public Result<Boolean> updateDict(Dict dict) throws Exception {
        Dict oldDict = dictMapper.findDictById(dict.getDictId());
        if (oldDict == null) {
            return Result.error(ErrorEnum.NOT_FOUND);
        }
        return Result.of(dictMapper.updateDict(dict) > 0);
    }

    @Override
    public Result<Boolean> deleteDictById(Long dictId) {
        Dict dict = dictMapper.findDictById(dictId);
        if (dict == null) {
            return Result.error(ErrorEnum.NOT_FOUND);
        }
        return Result.of(dictMapper.deleteDictById(dictId) > 0);
    }

    @Override
    public Result<PageVo> getDictList(Integer pageNum, Integer pageSize) {
        PageVo pageVo = new PageVo(pageNum, pageSize);
        PageUtil.startPage(pageVo);
        List<Dict> dictList = dictMapper.findAllDict();
        return Result.of(PageUtil.endPage(pageVo, dictList));
    }

    @Override
    public List<Dict> getDictList() {
        return dictMapper.findAllDict();
    }
}
