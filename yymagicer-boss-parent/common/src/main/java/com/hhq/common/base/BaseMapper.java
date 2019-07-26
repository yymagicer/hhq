package com.hhq.common.base;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 基类mapper
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {

}
