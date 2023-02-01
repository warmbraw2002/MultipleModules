package com.tiktok.service.impl;


import com.tiktok.ErrorEnum;
import com.tiktok.PageVo;
import com.tiktok.Result;
import com.tiktok.domain.Dept;
import com.tiktok.mapper.DeptMapper;
import com.tiktok.service.DeptService;
import com.tiktok.utils.ClassExamine;
import com.tiktok.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public Result<Dept> getDeptById(Long deptId) {
        Dept dept = deptMapper.findDeptById(deptId);
        if (dept == null) {
            return Result.error(ErrorEnum.NOT_FOUND);
        }
        return Result.of(dept);
    }

    @Override
    public Result<Boolean> insertDept(Dept dept) {
        return Result.of(deptMapper.insertDept(dept) > 0);
    }

    @Override
    public Result<Boolean> updateDept(Dept dept) throws Exception {
        Dept oldDept = deptMapper.findDeptById(dept.getDeptId());
        if (oldDept == null) {
            return Result.error(ErrorEnum.NOT_FOUND);
        }

        ClassExamine.objectOverlap(dept, oldDept);

        return Result.of(deptMapper.updateDept(dept) > 0);
    }

    @Override
    public Result<Boolean> deleteDeptById(Long deptId) {
        Dept dept = deptMapper.findDeptById(deptId);
        if (dept == null) {
            return Result.error(ErrorEnum.NOT_FOUND);
        }
        return Result.of(deptMapper.deleteDeptById(deptId) > 0);
    }

    @Override
    public Result<PageVo> getDeptList(Integer pageSize, Integer pageNum) {
        PageVo pageVo = new PageVo(pageSize, pageNum);
        PageUtil.startPage(pageVo);
        List<Dept> deptList = deptMapper.findAllDept();
        return Result.of(PageUtil.endPage(pageVo, deptList));
    }

    @Override
    public List<Dept> getDeptList() {
        return deptMapper.findAllDept();
    }

}
