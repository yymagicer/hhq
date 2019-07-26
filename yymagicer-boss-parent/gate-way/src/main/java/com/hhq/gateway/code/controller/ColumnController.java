package com.hhq.gateway.code.controller;

import com.hhq.codegeneration.po.ColumnPO;
import com.hhq.codegeneration.service.ColumnService;
import com.hhq.common.constant.BaseConstant;
import com.hhq.common.util.IdWorkerUtil;
import com.hhq.common.util.JsonUtils;
import com.hhq.common.util.ModelMapperUtil;
import com.hhq.gateway.code.vo.ColumnVO;
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
@RequestMapping("/column")
public class ColumnController {

    @Autowired
    private ColumnService columnService;
    /**
     * 查询列表
     * @param vo
     * @return
     */
    @RequestMapping("/queryList")
    public String queryList(@RequestBody @Validated ColumnVO vo){
        ColumnPO columnPO = ModelMapperUtil.strictMap(vo, ColumnPO.class);
        List<ColumnPO> dataList = columnService.queryList(columnPO);
        return JsonUtils.getSucc(dataList);
    }

    /**
     * 新增
     * @param vo
     * @return
     */
    @RequestMapping("/add")
    public String add(@RequestBody @Validated(value = {Insert.class}) ColumnVO vo){
        UserPO user = (UserPO) SecurityUtils.getSubject().getPrincipal();
        ColumnPO columnPO = ModelMapperUtil.strictMap(vo, ColumnPO.class);

        columnPO.setColumnId(IdWorkerUtil.getFlowIdWorkerInstance().nextId());
        columnPO.setIsDelete(BaseConstant.IS_NOTE_DELETE);
        columnPO.setCreateTime(new Date());
        columnPO.setCreateUserId(user.getCreateUserId());
        columnPO.setCreator(user.getUserName());
        columnService.save(columnPO);
        return JsonUtils.getSucc();
    }
    /**
     * 更新
     * @param vo
     * @return
     */
    @RequestMapping("/update")
    public String update(@RequestBody @Validated(value = {Insert.class}) ColumnVO vo){
        UserPO user = (UserPO) SecurityUtils.getSubject().getPrincipal();
        ColumnPO columnPO = ModelMapperUtil.strictMap(vo, ColumnPO.class);
        columnPO.setUpdateTime(new Date());
        columnPO.setUpdateUserId(user.getUserId());
        columnPO.setUpdater(user.getUserName());
        columnService.update(columnPO);

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
        columnService.batchDelete(ids,user.getUserId(),user.getUserName());
        return JsonUtils.getSucc();
    }
}
