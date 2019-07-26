package com.hhq.user.po;

import com.hhq.common.base.BasePO;
import lombok.Data;

import javax.persistence.Table;

@Data
@Table(name = "t_sys")
public class SystemPO extends BasePO {
    /**
     * 系统id
     */
    private String sysId;
    /**
     *
     */
    private String sysName;
    /**
     * 系统描述
     */
    private String sysDesc;
}
