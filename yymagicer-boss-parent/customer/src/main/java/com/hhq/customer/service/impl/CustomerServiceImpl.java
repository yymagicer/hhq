package com.hhq.customer.service.impl;

import com.hhq.common.base.AbstractService;
import com.hhq.common.constant.BaseConstant;
import com.hhq.common.util.IdWorkerUtil;
import com.hhq.common.util.SecretUtils;
import com.hhq.customer.mapper.CustomerMapper;
import com.hhq.customer.po.CustomerPO;
import com.hhq.customer.service.CustomerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
public class CustomerServiceImpl extends AbstractService<CustomerPO> implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;
    @Override
    public CustomerPO queryByMobile(String mobile) {
        return customerMapper.selectOne(CustomerPO.builder().mobile(mobile).build());
    }

    @Override
    public CustomerPO queryByUserName(String userName) {
        return customerMapper.selectOne(CustomerPO.builder().userName(userName).build());
    }

    @Override
    public CustomerPO queryByEmail(String email) {
        return customerMapper.selectOne(CustomerPO.builder().email(email).build());
    }
    @Override
    public int save(CustomerPO customerPO) {
        customerPO.setUserId(IdWorkerUtil.getFlowIdWorkerInstance().nextId());
        String salt = SecretUtils.getSalt();
        customerPO.setSalt(salt);
        customerPO.setPassword(SecretUtils.generateStoredPwd(salt,customerPO.getPassword()));
        customerPO.setIsDelete(BaseConstant.IS_NOTE_DELETE);
        customerPO.setCreateTime(new Date());
        return customerMapper.insertSelective(customerPO);
    }

    @Override
    public CustomerPO get(CustomerPO customerPO) {
        return customerMapper.selectOne(customerPO);
    }

    @Override
    public int update(CustomerPO customerPO) {
        customerPO.setPassword(SecretUtils.generateStoredPwd(customerPO.getSalt(),customerPO.getPassword()));
        customerPO.setUpdateTime(new Date());
        return customerMapper.updateByPrimaryKeySelective(customerPO);
    }

    @Override
    public int delete(CustomerPO customerPO) {
        customerPO.setIsDelete(BaseConstant.IS_DELETE);
        return customerMapper.updateByPrimaryKeySelective(customerPO);
    }

    @Override
    public List<CustomerPO> queryList(CustomerPO customerPO) {
        Example example = new Example(CustomerPO.class);
        Example.Criteria criteria = example.createCriteria();
        if(null==customerPO){
            customerPO = new CustomerPO();
        }
        if(StringUtils.isNotEmpty(customerPO.getUserName())){
            criteria.andEqualTo("userName",customerPO.getUserName());
        }
        if(StringUtils.isNotEmpty(customerPO.getMobile())){
            criteria.andEqualTo("mobile",customerPO.getMobile());
        }
        if(StringUtils.isNotEmpty(customerPO.getEmail())){
            criteria.andEqualTo("email",customerPO.getEmail());
        }
        example.setOrderByClause("create_time desc");
        return customerMapper.selectByExample(example);
    }


    @Override
    public int batchDelete(List<String> ids, String userId, String userName) {
        return 0;
    }
}
