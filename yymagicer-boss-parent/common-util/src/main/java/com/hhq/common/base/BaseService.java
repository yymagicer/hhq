package com.hhq.common.base;

import java.util.List;

/**
 * 基类service
 */
public interface BaseService<T> {

    /**
     * 保存
     * @param t
     * @return
     */
    int save(T t);

    /**
     * 获取单体
     * @param t
     * @return
     */
    T get(T t);

    /**
     * 修改
     * @param t
     * @return
     */
    int update(T t);

    /**
     * 删除
     * @param t
     * @return
     */
    int delete(T t);

    /**
     * 查询列表
     * @param t
     * @return
     */
    List<T> queryList(T t);
}
