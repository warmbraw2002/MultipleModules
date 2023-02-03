package com.tiktok.service.impl;

import com.tiktok.ErrorEnum;
import com.tiktok.PageVo;
import com.tiktok.Result;
import com.tiktok.domain.Dict;
import com.tiktok.domain.DictDetail;
import com.tiktok.mapper.DictDetailMapper;
import com.tiktok.mapper.DictMapper;
import com.tiktok.service.DictDetailService;
import com.tiktok.utils.ClassExamine;
import com.tiktok.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictDetailServiceImpl implements DictDetailService {

    @Autowired
    private DictDetailMapper dictDetailMapper;

    @Autowired
    private DictMapper dictMapper;

    @Override
    public Result<DictDetail> getDictDetailById(Long detailId) {
        DictDetail dictDetail = dictDetailMapper.findDictDetailById(detailId);
        if (dictDetail == null) {
            return Result.error(ErrorEnum.NOT_FOUND);
        }
        return Result.of(dictDetail);
    }

    @Override
    public Result<Boolean> insertDictDetail(DictDetail dictDetail) {
        Dict dict = dictMapper.findDictById(dictDetail.getDictId());
        if (dict == null) {
            return Result.error(ErrorEnum.INVALID_FIELD);
        }
        return Result.of(dictDetailMapper.insertDictDetail(dictDetail) > 0);
    }

    @Override
    public Result<Boolean> updateDictDetail(DictDetail dictDetail) throws Exception {
        DictDetail oldDictDetail = dictDetailMapper.findDictDetailById(dictDetail.getDetailId());
        if (oldDictDetail == null) {
            return Result.error(ErrorEnum.NOT_FOUND);
        }

        Dict dict = dictMapper.findDictById(dictDetail.getDictId());
        if (dict == null) {
            return Result.error(ErrorEnum.INVALID_FIELD);
        }

        ClassExamine.objectOverlap(oldDictDetail, dictDetail);

        return Result.of(dictDetailMapper.updateDictDetail(oldDictDetail) > 0);
    }

    @Override
    public Result<Boolean> deleteDictDetailById(Long deptId) {
        DictDetail dictDetail = dictDetailMapper.findDictDetailById(deptId);
        if (dictDetail == null) {
            return Result.error(ErrorEnum.NOT_FOUND);
        }
        return Result.of(dictDetailMapper.deleteDictDetailById(deptId) > 0);
    }

    @Override
    public Result<PageVo> getDictDetailList(Integer pageNum, Integer pageSize) {
        PageVo pageVo = new PageVo(pageNum, pageSize);
        PageUtil.startPage(pageVo);
        List<DictDetail> dictDetailList = dictDetailMapper.findAllDictDetail();
        return Result.of(PageUtil.endPage(pageVo, dictDetailList));
    }
}
