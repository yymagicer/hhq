package com.hhq.gateway.face.vo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.awt.image.BufferedImage;

/**
 * 人脸注册
 */
@Data
public class FaceRegistVO {

    /**
     * face名称
     */
    @NotEmpty(message = "face名称不能为空")
    private String faceName;

    /**
     * face图片
     */
    @NotNull(message = "face图片不能为空")
    private String faceImage;
}
