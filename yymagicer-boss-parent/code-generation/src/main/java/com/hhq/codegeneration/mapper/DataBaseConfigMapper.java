package com.hhq.codegeneration.mapper;

import com.hhq.codegeneration.po.DataBaseConfigPO;
import com.hhq.common.base.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DataBaseConfigMapper extends BaseMapper<DataBaseConfigPO> {

    public int batchDelete(List<String> ids);
}
