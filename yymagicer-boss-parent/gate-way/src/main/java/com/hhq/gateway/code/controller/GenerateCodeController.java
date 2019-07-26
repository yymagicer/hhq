package com.hhq.gateway.code.controller;

import com.hhq.codegeneration.po.DataBaseColumnPO;
import com.hhq.codegeneration.po.DataBaseConfigPO;
import com.hhq.codegeneration.po.GenerateCodePO;
import com.hhq.codegeneration.po.TablePO;
import com.hhq.codegeneration.service.*;
import com.hhq.common.util.JsonUtils;
import com.hhq.common.util.ModelMapperUtil;
import com.hhq.gateway.code.vo.GenerateCodeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/generateCode")
public class GenerateCodeController {

    @Autowired
    private DataBaseService dataBaseService;

    @Autowired
    private DataBaseConfigService dataBaseConfigService;

    @Autowired
    private TableService tableService;

    @Autowired
    private GenerateService generateService;

    @Autowired
    private ColumnService columnService;

    /**
     * 获取数据库中所有表
     * @param configId
     * @return
     */
    @RequestMapping("/getTableList")
    public String getTableList(@RequestParam("configId") String configId) {
        DataBaseConfigPO query = new DataBaseConfigPO();
        query.setConfigId(configId);
        DataBaseConfigPO dataBaseConfigPO = dataBaseConfigService.get(query);
        if (null == dataBaseConfigPO) {
            return JsonUtils.getSucc(new ArrayList<>());
        }
        List<String> dataList = dataBaseService.queryTableListBySchema(dataBaseConfigPO.getTableSchema(), dataBaseConfigPO);
        return JsonUtils.getSucc(dataList);
    }

    /**
     * 获取表中的所有字段
     * @param configId
     * @param tableId
     * @return
     */
    @RequestMapping("/getColumnList")
    public String getColumnList(@RequestParam("configId") String configId, @RequestParam("tableId") String tableId) {
        DataBaseConfigPO query = new DataBaseConfigPO();
        query.setConfigId(configId);
        DataBaseConfigPO dataBaseConfigPO = dataBaseConfigService.get(query);
        if (null == dataBaseConfigPO) {
            return JsonUtils.getSucc(new ArrayList<>());
        }
        TablePO queryTable = new TablePO();
        queryTable.setTableId(tableId);
        TablePO tablePO = tableService.get(queryTable);
        if (null == tablePO) {
            return JsonUtils.getSucc(new ArrayList<>());

        }
        List<DataBaseColumnPO> dataList = dataBaseService.queryColumnListByTableName(tablePO.getTableName(), dataBaseConfigPO);
        return JsonUtils.getSucc(dataList);
    }

    /**
     * 代码生成
     * @param vo
     * @return
     */
    @RequestMapping("/generate")
    public String generate(@RequestBody @Validated GenerateCodeVO vo){
        GenerateCodePO generateCodePO = ModelMapperUtil.strictMap(vo, GenerateCodePO.class);
        generateService.generateCode(generateCodePO);
        return JsonUtils.getSucc();
    }
}
