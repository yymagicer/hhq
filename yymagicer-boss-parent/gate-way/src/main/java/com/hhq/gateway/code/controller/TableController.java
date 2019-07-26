package com.hhq.gateway.code.controller;

import com.hhq.codegeneration.po.TablePO;
import com.hhq.codegeneration.service.TableService;
import com.hhq.common.constant.BaseConstant;
import com.hhq.common.util.IdWorkerUtil;
import com.hhq.common.util.JsonUtils;
import com.hhq.common.util.ModelMapperUtil;
import com.hhq.gateway.code.vo.TableVO;
import com.hhq.gateway.constant.CommonConstants;
import com.hhq.user.po.UserPO;
import org.apache.ibatis.annotations.Insert;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/table")
public class TableController {

    @Autowired
    private TableService tableService;

    /**
     * 查询列表
     * @param vo
     * @return
     */
    @RequestMapping("/queryList")
    public String queryList(@RequestBody @Validated TableVO vo){
        TablePO tablePO = ModelMapperUtil.strictMap(vo, TablePO.class);
        List<TablePO> dataList = tableService.queryList(tablePO);
        return JsonUtils.getSucc(dataList);
    }

    /**
     * 新增
     * @param vo
     * @return
     */
    @RequestMapping("/add")
    public String add(@RequestBody @Validated(value = {Insert.class}) TableVO vo){
        UserPO user = (UserPO) SecurityUtils.getSubject().getPrincipal();
        TablePO tablePO = ModelMapperUtil.strictMap(vo, TablePO.class);
        TablePO query = new TablePO();
        query.setTableName(vo.getTableName());
        TablePO queryByName = tableService.get(query);
        if(null!=queryByName){
            return    JsonUtils.getFail(CommonConstants.NAME_EXIST,CommonConstants.NAME_EXIST_MSG);
        }

        tablePO.setTableId(IdWorkerUtil.getFlowIdWorkerInstance().nextId());
        tablePO.setIsDelete(BaseConstant.IS_NOTE_DELETE);
        tablePO.setCreateTime(new Date());
        tablePO.setCreateUserId(user.getCreateUserId());
        tablePO.setCreator(user.getUserName());
        tableService.save(tablePO);
        return JsonUtils.getSucc();
    }
    /**
     * 更新
     * @param vo
     * @return
     */
    @RequestMapping("/update")
    public String update(@RequestBody @Validated(value = {Insert.class}) TableVO vo){
        UserPO user = (UserPO) SecurityUtils.getSubject().getPrincipal();
        TablePO tablePO = ModelMapperUtil.strictMap(vo, TablePO.class);
        tablePO.setUpdateTime(new Date());
        tablePO.setUpdateUserId(user.getUserId());
        tablePO.setUpdater(user.getUserName());
        tableService.update(tablePO);

        return JsonUtils.getSucc();
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    public String delete(@RequestParam("ids") List<String> ids){
        UserPO user = (UserPO) SecurityUtils.getSubject().getPrincipal();
        tableService.batchDelete(ids,user.getUserId(),user.getUserName());
        return JsonUtils.getSucc();
    }
}
