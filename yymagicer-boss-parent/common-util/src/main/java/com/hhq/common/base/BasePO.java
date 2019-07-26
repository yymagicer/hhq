package com.hhq.common.base;

import lombok.Data;

import java.util.Date;

/**
 * 基类PO
 */
@Data
public class BasePO {
    private String isDelete;
    private String creator;
    private String createUserId;
    private Date createTime;
    private String updater;
    private String updateUserId;
    private Date updateTime;
}
