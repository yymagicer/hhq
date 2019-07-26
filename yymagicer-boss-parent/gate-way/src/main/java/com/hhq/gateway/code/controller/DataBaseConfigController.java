package com.hhq.gateway.code.controller;

import com.github.pagehelper.PageInfo;
import com.hhq.codegeneration.po.DataBaseConfigPO;
import com.hhq.codegeneration.service.DataBaseConfigService;
import com.hhq.common.base.PageQueryParam;
import com.hhq.common.constant.BaseConstant;
import com.hhq.common.util.IdWorkerUtil;
import com.hhq.common.util.JsonUtils;
import com.hhq.common.util.ModelMapperUtil;
import com.hhq.gateway.code.vo.DataBaseConfigVO;
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
@RequestMapping("/dataBaseConfig")
public class DataBaseConfigController {

    @Autowired
    private DataBaseConfigService dataBaseConfigService;

    /**
     * 分页查询
     * @param vo
     * @return
     */
    @RequestMapping("/queryListByPage")
    public String queryListByPage(@RequestBody @Validated DataBaseConfigVO vo){
        DataBaseConfigPO dataBaseConfigPO = ModelMapperUtil.strictMap(vo, DataBaseConfigPO.class);
        PageQueryParam<DataBaseConfigPO> param  =new PageQueryParam<>();
        param.setPageSize(vo.getPageSize());
        param.setPageNum(vo.getPageNum());
        param.setT(dataBaseConfigPO);
        PageInfo<DataBaseConfigPO> pageInfo = dataBaseConfigService.queryListByPage(param);
        return JsonUtils.getSucc(pageInfo);
    }

    /**
     * 查询列表
     * @param vo
     * @return
     */
    @RequestMapping("/queryList")
    public String queryList(@RequestBody @Validated DataBaseConfigVO vo){
        DataBaseConfigPO dataBaseConfigPO = ModelMapperUtil.strictMap(vo, DataBaseConfigPO.class);
        List<DataBaseConfigPO> dataList = dataBaseConfigService.queryList(dataBaseConfigPO);
        return JsonUtils.getSucc(dataList);
    }

    /**
     * 新增
     * @param vo
     * @return
     */
    @RequestMapping("/add")
    public String add(@RequestBody @Validated(value = {Insert.class}) DataBaseConfigVO vo){
        UserPO user = (UserPO) SecurityUtils.getSubject().getPrincipal();
        DataBaseConfigPO dataBaseConfigPO = ModelMapperUtil.strictMap(vo, DataBaseConfigPO.class);
        DataBaseConfigPO query = new DataBaseConfigPO();
        query.setTableSchema(vo.getTableSchema());
        DataBaseConfigPO queryByName = dataBaseConfigService.get(query);
        if(null!=queryByName){
         return    JsonUtils.getFail(CommonConstants.NAME_EXIST,CommonConstants.NAME_EXIST_MSG);
        }
        dataBaseConfigPO.setConfigId(IdWorkerUtil.getFlowIdWorkerInstance().nextId());
        dataBaseConfigPO.setIsDelete(BaseConstant.IS_NOTE_DELETE);
        dataBaseConfigPO.setCreateTime(new Date());
        dataBaseConfigPO.setCreateUserId(user.getCreateUserId());
        dataBaseConfigPO.setCreator(user.getUserName());
        dataBaseConfigService.save(dataBaseConfigPO);
        return JsonUtils.getSucc();
    }
    /**
     * 更新
     * @param vo
     * @return
     */
    @RequestMapping("/update")
    public String update(@RequestBody @Validated(value = {Insert.class}) DataBaseConfigVO vo){
        UserPO user = (UserPO) SecurityUtils.getSubject().getPrincipal();
        DataBaseConfigPO dataBaseConfigPO = ModelMapperUtil.strictMap(vo, DataBaseConfigPO.class);
        dataBaseConfigPO.setUpdateTime(new Date());
        dataBaseConfigPO.setUpdateUserId(user.getUserId());
        dataBaseConfigPO.setUpdater(user.getUserName());
        dataBaseConfigService.update(dataBaseConfigPO);
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
        dataBaseConfigService.batchDelete(ids,user.getUserId(),user.getUserName());
        return JsonUtils.getSucc();
    }
}
