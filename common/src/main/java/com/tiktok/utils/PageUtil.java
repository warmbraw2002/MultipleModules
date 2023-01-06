package com.tiktok.utils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tiktok.PageVo;

import java.util.List;
import java.util.Objects;

public class PageUtil {

    /**
     * 开启分页
     * @param pageVo 分页参数对象
     * @return
     */
    public static PageVo startPage(PageVo pageVo) {
        int pageNum = 0;
        int pageSize = 0;
        if (!Objects.isNull(pageVo)) {
            pageNum = pageVo.getPageNum();
            pageSize = pageVo.getPageSize();
        } else {
            pageVo = new PageVo();
        }
        // 开启分页
        PageHelper.startPage(pageNum, pageSize);
        return pageVo;
    }

    /**
     * 结束分页
     * @param pageVo 分页参数对象
     * @param list 查询数据结果集
     * @return
     */
    public static <T> PageVo endPage(PageVo pageVo, List<T> list) {
        // 封装查询结果
        PageInfo<T> pageInfo = new PageInfo<>(list);
        pageVo.setList(list);
        pageVo.setTotal(pageInfo.getTotal());
        pageVo.setPages(pageInfo.getPages());
        // 清除分页参数对象
        PageHelper.clearPage();
        return pageVo;
    }

}