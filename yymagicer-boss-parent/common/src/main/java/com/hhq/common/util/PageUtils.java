package com.hhq.common.util;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.hhq.common.base.PageResult;

import java.util.List;

/**
 * 分页工具类
 */
public class PageUtils {

    public static PageResult parseBasePageResult(PageInfo page) {
        PageResult result = new PageResult();
            result.setPageNum(page.getPageNum());
            result.setPageSize(page.getPageSize());
            result.setPageTotal(page.getPages());
            result.setTotal(page.getTotal());
            result.setResultList(page.getList());
        return result;
    }
}
