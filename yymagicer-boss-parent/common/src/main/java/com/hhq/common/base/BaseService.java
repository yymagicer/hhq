package com.hhq.common.base;

import com.github.pagehelper.PageInfo;

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

    /**
     * 分页查询
     * @param param
     * @return
     */
    PageInfo<T> queryListByPage(PageQueryParam<T> param);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    int batchDelete(List<String> ids,String userId,String userName);
}
