package com.hhq.common.base;

import lombok.Data;

/**
 * 分页查询的参数
 */
@Data
public class PageQueryParam<T> {
    /**
     * 每页数据量
     */
    private Integer pageSize;

    /**
     * 页码编号
     */
    private Integer pageNum;

    /**
     * 传递的参数对象
     */
    private T t;
}
