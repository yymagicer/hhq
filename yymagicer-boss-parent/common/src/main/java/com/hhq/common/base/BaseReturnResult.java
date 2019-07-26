package com.hhq.common.base;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 基类接口返回值
 */
@Data
@NoArgsConstructor
public class BaseReturnResult<T> implements Serializable {

    /**
     * 返回成功标识
     */
    private Boolean success;
    /**
     * 返回码
     */
        private Integer code;
    /**
     * 返回描述
     */
    private String msg;
    /**
     * 返回值
     */
    private T data;

    public BaseReturnResult(Boolean success){
        this.success = success;
    }

    public BaseReturnResult(Boolean success,int code){
        this.success = success;
        this.code = code;
    }
    public BaseReturnResult(Boolean success,int code,String msg){
        this.success = success;
        this.code = code;
        this.msg = msg;
    }
    public BaseReturnResult(Boolean success,int code,String msg,T t){
        this.success = success;
        this.code = code;
        this.msg = msg;
        this.data = t;
    }
    public BaseReturnResult(Boolean success,int code,T t){
        this.success = success;
        this.code = code;
        this.data = t;
    }
}
