package com.hhq.gateway.face.controller;

import com.hhq.common.util.IdWorkerUtil;
import com.hhq.common.util.JsonUtils;
import com.hhq.face.common.FaceResult;
import com.hhq.face.po.FaceInfoPO;
import com.hhq.face.po.SeetaImageData;
import com.hhq.face.service.FaceService;
import com.hhq.face.util.FaceHelper;
import com.hhq.face.util.ImageUtils;
import com.hhq.gateway.face.vo.FaceRegistVO;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Map;

/**
 *
 */
@RestController
@RequestMapping("/face")
public class FaceController {

    /**
     * faceService
     */
    @Autowired
    private FaceService faceService;
    /**
     *注册用户
     * @param
     * @return
     */
    @RequestMapping("/regist")
    public String regist(@RequestParam("faceName")String faceName,@RequestParam("faceImage") String faceImage) throws IOException {

        BASE64Decoder decoder = new BASE64Decoder();
        // 解密
        byte[] b = decoder.decodeBuffer(faceImage);
        // 处理数据
        for (int i = 0; i < b.length; ++i) {
        if (b[i] < 0) {
            b[i] += 256;
            }
        }
        BufferedImage bufferedImage = ImageUtils.bgrToBufferedImage(b, 100, 100);
        FaceHelper.register(faceName, bufferedImage);
//        if(faceResult.getSuccess()){
//            SeetaImageData data = (SeetaImageData)faceResult.getData();
//            FaceInfoPO face = new FaceInfoPO();
//            face.setFaceId(IdWorkerUtil.getFlowIdWorkerInstance().nextId());
//            face.setFaceName(faceName);
//            face.setImgData(data.getData());
//            face.setWidth(data.getWidth());
//            face.setHeight(data.getHeight());
//            face.setChannel(data.getChannels());
//            faceService.save(face);
//        }
        System.out.printf("regist success");
        return JsonUtils.getSucc();
    }
}
