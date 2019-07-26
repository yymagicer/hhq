package com.hhq.user.mapper;

import com.hhq.common.base.BaseMapper;
import com.hhq.user.po.UserRoleRelPO;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRelMapper extends BaseMapper<UserRoleRelPO> {

    @Select("select role_id from t_user_role where is_delete = '0' and user_id = #{userId}")
    List<String> queryRoleIds(String userId);
}
