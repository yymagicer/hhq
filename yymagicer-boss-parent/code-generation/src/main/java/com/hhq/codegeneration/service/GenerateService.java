package com.hhq.codegeneration.service;


import com.hhq.codegeneration.po.GenerateCodePO;

public interface GenerateService {

    /**
     * 生成代码
     * @param po
     */
    public void generateCode(GenerateCodePO po);
}
