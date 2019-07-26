package com.hhq.common.base;

import lombok.Data;

import java.util.List;

@Data
public class BaseVO {
    /**
     *每页分页数
     */
    private Integer pageSize;
    /**
     * 页码数
     */
    private Integer pageNum;

    /**
     * 主键集合
     */
    private List<String> keyIds;
}
