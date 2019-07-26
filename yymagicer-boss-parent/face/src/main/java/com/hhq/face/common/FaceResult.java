package com.hhq.face.common;

import lombok.Data;

@Data
public class FaceResult {

    /**
     * 成功标识
     */
    private Boolean success;
    /**
     * 数据
     */
    private Object data;
}
