package com.tiktok.service;

import com.tiktok.PageVo;
import com.tiktok.Result;
import com.tiktok.domain.Dept;

import java.util.List;

public interface DeptService {

    Result<Dept> getDeptById(Long deptId);
    Result<Boolean> insertDept(Dept dept);
    Result<Boolean> updateDept(Dept dept) throws Exception;
    Result<Boolean> deleteDeptById(Long deptId);
    Result<PageVo> getDeptList(Integer pageSize, Integer pageNum);
    List<Dept> getDeptList();
}
