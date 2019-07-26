package com.hhq.face.po;

import com.hhq.common.base.BasePO;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 */
@Data
@Table(name="t_face_info")
public class FaceInfoPO extends BasePO {
    @Id
    @GeneratedValue(generator="JDBC")
    private Integer id;
    /**
     * faceId
     */
    private String faceId;

    /**
     * face名称
     */
    private String faceName;
    /**
     * 图片数据
     */
    private byte[] imgData;
    /**
     * 图片的宽度
     */
    private int width;
    /**
     * 图片的高度
     */
    private int height;
    /**
     * 渠道
     */
    private int channel;
}
