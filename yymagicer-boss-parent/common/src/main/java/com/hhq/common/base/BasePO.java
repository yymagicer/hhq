package com.hhq.common.base;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * 基类PO
 */
@Data
public class BasePO implements Serializable {
    @Id
    @GeneratedValue(generator="JDBC")
    private Integer id;
    private String isDelete;
    private String creator;
    private String createUserId;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    private String updater;
    private String updateUserId;
    private Date updateTime;
}
