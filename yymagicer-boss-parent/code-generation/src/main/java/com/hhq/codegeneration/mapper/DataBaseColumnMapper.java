package com.hhq.codegeneration.mapper;

import com.hhq.codegeneration.po.DataBaseColumnPO;
import com.hhq.codegeneration.po.QueryDataBaseColumnParamPO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataBaseColumnMapper {

    /**
     * 查询数据库列信息
     * @param param
     * @return
     */
    List<DataBaseColumnPO> selectListByParam(QueryDataBaseColumnParamPO param);
}
