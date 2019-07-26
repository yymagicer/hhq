package com.hhq.common.base;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractService<T> implements BaseService<T> {

    /**
     * 公用分页方法
     * @param param
     * @return
     */
    @Override
    public PageInfo<T> queryListByPage(PageQueryParam<T> param) {
        PageHelper.startPage(param.getPageNum(),param.getPageSize(),true);
        List<T> dataList = queryList(param.getT());
        PageInfo pageInfo = new PageInfo(dataList);
        return pageInfo;
    }
}
