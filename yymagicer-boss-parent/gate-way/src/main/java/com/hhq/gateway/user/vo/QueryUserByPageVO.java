package com.hhq.gateway.user.vo;

import com.hhq.common.base.PageQueryParam;
import lombok.Data;

@Data
public class QueryUserByPageVO extends PageQueryParam {

    private String userName;

    private String mobile;

    private String email;
}
