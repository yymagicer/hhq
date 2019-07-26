package com.hhq.gateway.vo;

import com.hhq.common.base.PageQueryParam;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PageQueryParamVO<T> extends PageQueryParam {
    /**
     * 每页数据量
     */
    @NotNull(message = "每页数据量不能为空")
    private Integer pageSize;

    /**
     * 页码编号
     */
    @NotNull(message = "页码编号不能为空")
    private Integer pageNum;
}
