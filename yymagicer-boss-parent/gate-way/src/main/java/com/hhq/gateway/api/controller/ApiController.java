package com.hhq.gateway.api.controller;

import com.baidu.aip.imageclassify.AipImageClassify;
import com.baidu.aip.ocr.AipOcr;
import com.hhq.aiapi.common.BDFactory;
import com.hhq.common.util.JsonUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {

      private static Logger logger = LoggerFactory.getLogger(ApiController.class);
      AipImageClassify aipImageClassify = BDFactory.getAipImageClassify();
      AipOcr aipOcr = BDFactory.getAipOcr();

      /**
       * 图片转字符文本接口
       * @param file
       */
      @RequestMapping(value="/imageToWord",method={RequestMethod.POST})
      public String imageToWord(@RequestParam(value = "file")MultipartFile file) {
            HashMap<String, String> options = new HashMap<String, String>();
            try {
                  byte[] imageByte = file.getBytes();
                  JSONObject resp = aipOcr.basicGeneral(imageByte, options);
                  com.alibaba.fastjson.JSONObject json = com.alibaba.fastjson.JSONObject.parseObject(resp.toString(2));
                  logger.info(JsonUtils.getSucc(json));
                  return JsonUtils.getSucc(json);
            } catch (Exception e) {
                  logger.error("图片转字符文本接口出错了"+e.getMessage());
                  return JsonUtils.getFail("图片转字符文本接口出错了");
            }
      }

      /**
       * 身份证识别
       * @param file
       * @return
       */
      @RequestMapping(value="/idCard",method={RequestMethod.POST})
      public String idCard(@RequestParam(value = "file")MultipartFile file){
            HashMap<String, String> options = new HashMap<String, String>();
            try {
                  byte[] imageByte = file.getBytes();
                  JSONObject object = aipOcr.idcard(imageByte, "front", options);
                  String s = object.toString(2);
                  com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(s);
                  return JsonUtils.getSucc(jsonObject.getString("words_result"));
            } catch (Exception e) {
                  logger.error("身份证识别出错了"+e.getMessage());
                  return JsonUtils.getFail("身份证识别出错了");
            }
      }

      /**
       * 银行卡识别
       * @param file
       * @return
       */
      @RequestMapping(value="/bankCard",method={RequestMethod.POST})
      public String bankCard(@RequestParam(value = "file")MultipartFile file){
            HashMap<String, String> options = new HashMap<String, String>();
            try {
                  byte[] imageByte = file.getBytes();
                  JSONObject object = aipOcr.bankcard(imageByte,options);
                  return JsonUtils.getSucc(object.toString(2));
            } catch (Exception e) {
                  logger.error("银行卡识别出错了"+e.getMessage());
                  return JsonUtils.getFail("银行卡识别出错了");
            }
      }

      /**
       *驾驶证识别
       * @param file
       * @return
       */
      @RequestMapping(value="/drivingLicense",method={RequestMethod.POST})
      public String drivingLicense(@RequestParam(value = "file")MultipartFile file){
            HashMap<String, String> options = new HashMap<String, String>();
            try {
                  byte[] imageByte = file.getBytes();
                  JSONObject object = aipOcr.drivingLicense(imageByte,options);
                  return JsonUtils.getSucc(object.toString(2));
            } catch (Exception e) {
                  logger.error("驾驶证识别出错了"+e.getMessage());
                  return JsonUtils.getFail("驾驶证识别出错了");
            }
      }
      /**
       *行驶证识别
       * @param file
       * @return
       */
      @RequestMapping(value="/vehicleLicense",method={RequestMethod.POST})
      public String vehicleLicense(@RequestParam(value = "file")MultipartFile file){
            HashMap<String, String> options = new HashMap<String, String>();
            try {
                  byte[] imageByte = file.getBytes();
                  JSONObject object = aipOcr.vehicleLicense(imageByte,options);
                  return JsonUtils.getSucc(object.toString(2));
            } catch (Exception e) {
                  logger.error("行驶证识别出错了"+e.getMessage());
                  return JsonUtils.getFail("行驶证识别出错了");
            }
      }

      /**
       * 车牌识别
       * @param file
       * @return
       */
      @RequestMapping(value="/plateLicense",method={RequestMethod.POST})
      public String plateLicense(@RequestParam(value = "file")MultipartFile file){
            HashMap<String, String> options = new HashMap<String, String>();
            try {
                  byte[] imageByte = file.getBytes();
                  JSONObject object = aipOcr.plateLicense(imageByte,options);
                  return JsonUtils.getSucc(object.toString(2));
            } catch (Exception e) {
                  logger.error("车牌识别出错了"+e.getMessage());
                  return JsonUtils.getFail("车牌识别出错了");
            }
      }
      /**
       * 菜品识别
       * @param file
       * @return
       */
      @RequestMapping(value="/dishDetect",method={RequestMethod.POST})
      public String dishDetect(@RequestParam(value = "file")MultipartFile file){
            HashMap<String, String> options = new HashMap<String, String>();
            try {
                  byte[] imageByte = file.getBytes();
                  JSONObject object = aipImageClassify.dishDetect(imageByte,options);
                  return JsonUtils.getSucc(object.toString(2));
            } catch (Exception e) {
                  logger.error("菜品识别出错了"+e.getMessage());
                  return JsonUtils.getFail("菜品识别出错了");
            }
      }

      /**
       * 车型识别
       * @param file
       * @return
       */
      @RequestMapping(value="/carDetect",method={RequestMethod.POST})
      public String carDetect(@RequestParam(value = "file")MultipartFile file){
            HashMap<String, String> options = new HashMap<String, String>();
            try {
                  byte[] imageByte = file.getBytes();
                  JSONObject object = aipImageClassify.carDetect(imageByte,options);
                  return JsonUtils.getSucc(object.toString(2));
            } catch (Exception e) {
                  logger.error("车型识别出错了"+e.getMessage());
                  return JsonUtils.getFail("车型识别出错了");
            }
      }

      /**
       * logo商标识别
       * @param file
       * @return
       */
      @RequestMapping(value="/logoSearch",method={RequestMethod.POST})
      public String logoSearch(@RequestParam(value = "file")MultipartFile file){
            HashMap<String, String> options = new HashMap<String, String>();
            try {
                  byte[] imageByte = file.getBytes();
                  JSONObject object = aipImageClassify.logoSearch(imageByte,options);
                  return JsonUtils.getSucc(object.toString(2));
            } catch (Exception e) {
                  logger.error("logo商标识别出错了"+e.getMessage());
                  return JsonUtils.getFail("logo商标识别出错了");
            }
      }

      /**
       * 动物识别
       * @param file
       * @return
       */
      @RequestMapping(value="/animalDetect",method={RequestMethod.POST})
      public String animalDetect(@RequestParam(value = "file")MultipartFile file){
            HashMap<String, String> options = new HashMap<String, String>();
            try {
                  byte[] imageByte = file.getBytes();
                  JSONObject object = aipImageClassify.animalDetect(imageByte,options);
                  return JsonUtils.getSucc(object.toString(2));
            } catch (Exception e) {
                  logger.error("动物识别出错了"+e.getMessage());
                  return JsonUtils.getFail("动物识别出错了");
            }
      }

      /**
       * 植物识别
       * @param file
       * @return
       */
      @RequestMapping(value="/plantDetect",method={RequestMethod.POST})
      public String plantDetect(@RequestParam(value = "file")MultipartFile file){
            HashMap<String, String> options = new HashMap<String, String>();
            try {
                  byte[] imageByte = file.getBytes();
                  JSONObject object = aipImageClassify.plantDetect(imageByte,options);
                  return JsonUtils.getSucc(object.toString(2));
            } catch (Exception e) {
                  logger.error("植物识别出错了"+e.getMessage());
                  return JsonUtils.getFail("植物识别出错了");
            }
      }

      /**
       * 地标识别
       * @param file
       * @return
       */
      @RequestMapping(value="/landmark",method={RequestMethod.POST})
      public String landmark(@RequestParam(value = "file")MultipartFile file){
            HashMap<String, String> options = new HashMap<String, String>();
            try {
                  byte[] imageByte = file.getBytes();
                  JSONObject object = aipImageClassify.landmark(imageByte,options);
                  return JsonUtils.getSucc(object.toString(2));
            } catch (Exception e) {
                  logger.error("地标识别出错了"+e.getMessage());
                  return JsonUtils.getFail("地标识别出错了");
            }
      }

      /**
       * 花卉识别
       * @param file
       * @return
       */
      @RequestMapping(value="/flower",method={RequestMethod.POST})
      public String flower(@RequestParam(value = "file")MultipartFile file){
            HashMap<String, String> options = new HashMap<String, String>();
            try {
                  byte[] imageByte = file.getBytes();
                  JSONObject object = aipImageClassify.flower(imageByte,options);
                  return JsonUtils.getSucc(object.toString(2));
            } catch (Exception e) {
                  logger.error("花卉识别出错了"+e.getMessage());
                  return JsonUtils.getFail("花卉识别出错了");
            }
      }
      /**
       * 食材识别
       * @param file
       * @return
       */
      @RequestMapping(value="/ingredient",method={RequestMethod.POST})
      public String ingredient(@RequestParam(value = "file")MultipartFile file){
            HashMap<String, String> options = new HashMap<String, String>();
            try {
                  byte[] imageByte = file.getBytes();
                  JSONObject object = aipImageClassify.ingredient(imageByte,options);
                  return JsonUtils.getSucc(object.toString(2));
            } catch (Exception e) {
                  logger.error("食材识别出错了"+e.getMessage());
                  return JsonUtils.getFail("食材识别出错了");
            }
      }

      /**
       * 红酒识别
       * @param file
       * @return
       */
      @RequestMapping(value="/redwine",method={RequestMethod.POST})
      public String redwine(@RequestParam(value = "file")MultipartFile file){
            HashMap<String, String> options = new HashMap<String, String>();
            try {
                  byte[] imageByte = file.getBytes();
                  JSONObject object = aipImageClassify.redwine(imageByte,options);
                  return JsonUtils.getSucc(object.toString(2));
            } catch (Exception e) {
                  logger.error("红酒识别出错了"+e.getMessage());
                  return JsonUtils.getFail("红酒识别出错了");
            }
      }
      /**
       * 货币识别
       * @param file
       * @return
       */
      @RequestMapping(value="/currency",method={RequestMethod.POST})
      public String currency(@RequestParam(value = "file")MultipartFile file){
            HashMap<String, String> options = new HashMap<String, String>();
            try {
                  byte[] imageByte = file.getBytes();
                  JSONObject object = aipImageClassify.currency(imageByte,options);
                  return JsonUtils.getSucc(object.toString(2));
            } catch (Exception e) {
                  logger.error("货币识别出错了"+e.getMessage());
                  return JsonUtils.getFail("货币识别出错了");
            }
      }

}
