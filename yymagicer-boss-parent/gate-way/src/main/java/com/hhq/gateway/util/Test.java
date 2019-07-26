package com.hhq.gateway.util;

import com.hhq.common.util.HttpUtil;
import org.apache.commons.io.FileUtils;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) throws Exception{
        File file = new File("G:\\images\\1.jpg");
        byte[] bytes = FileUtils.readFileToByteArray(file);
        // 加密
        BASE64Encoder encoder = new BASE64Encoder();
        String image = encoder.encode(bytes);
        Map<String,String> param = new HashMap<>();
        param.put("faceName","test");
        param.put("faceImage",image);
        String s = HttpUtil.sendPost("http://172.16.9.148:8001/hhq/face/regist", param);
        System.out.printf("======================================"+s);
    }
}
