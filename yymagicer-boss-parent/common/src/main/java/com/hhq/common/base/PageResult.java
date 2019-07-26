package com.hhq.common.base;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 分页查询结果
 * @param <T>
 */
@Data
public class PageResult<T> implements Serializable {
    /**
     * 当前页数
     */
    private int pageNum;

    /**
     * 每页显示的条数
     */
    private int pageSize;

    /**
     * 总条数
     */
    private long total;

    /**
     * 总页数
     */
    private int pageTotal;

    /**
     * 分页结果
     */
    private List<T> resultList;
}
