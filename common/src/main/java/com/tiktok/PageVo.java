package com.tiktok;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class PageVo implements Serializable {
    /**
     * 分页页码, 查询哪一页的数据
     */
    private int pageNum;
    /**
     * 每一页显示记录数量
     */
    private int pageSize;
    /**
     * 查询总记录数量
     */
    private long total;
    /**
     * 总页数
     */
    private int pages;
    /**
     * 查询数据结果集
     */
    private Object list;


    public PageVo(int pageNum, int pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

}