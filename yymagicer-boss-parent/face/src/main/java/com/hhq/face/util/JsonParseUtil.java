package com.hhq.face.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hhq.face.po.Landmark;

import java.util.ArrayList;

public class JsonParseUtil {
     /*String json_str = "{"
            +" \"timestamp\": 1528420653,"
            +" \"result\": {"
            +"\"face_list\": [{"
            +"\"landmark\": ["
            +" {"
            +" \"y\": 293.6487427,"
            +" \"x\": 344.6775208"
            +" },"
            +"{"
            +" \"y\": 292.1832581,"
            +" \"x\": 465.147522"
            +"},"
            +"{"
            +"  \"y\": 362.4373169,"
            +"  \"x\": 407.2205811"
            +" },"
            +" {"
            +"  \"y\": 428.7177124,"
            +"  \"x\": 411.6735535"
            +" }"
            +"],"
            +" \"face_probability\": 0.9377777576,"
            +" \"location\": {"
            +"  \"height\": 253,"
            +" \"rotation\": 0,"
            +" \"width\": 243,"
            +" \"left\": 289.150177,"
            +"  \"top\": 244.4544983"
            +"},"
            +"\"landmark72\": ["
            +" {"
            +"  \"y\": 292.0180054,"
            +"\"x\": 289.417572"
            +"},"
            +"{"
            +" \"y\": 331.9599609,"
            +"\"x\": 292.8039246"
            +"  },"
            +"  {"
            +"   \"y\": 372.3904114,"
            +"  \"x\": 301.283844"
            +"},"
            +" {"
            +" \"y\": 411.2714233,"
            +"  \"x\": 314.0955505"
            +" },"
            +" {"
            +" \"y\": 450.2736206,"
            +" \"x\": 338.0390625"
            +" },"
            +" {"
            +"  \"y\": 484.864502,"
            +"  \"x\": 376.0628662"
            +"  },"
            +"{"
            +" \"y\": 496.4431152,"
            +"\"x\": 415.7805176"
            +"},"
            +"{"
            +" \"y\": 487.0181274,"
            +" \"x\": 455.3654175"
            +"},"
            +"{"
            +" \"y\": 454.7385559,"
            +" \"x\": 492.2065735"
            +" },"
            +" {"
            +"  \"y\": 412.9674377,"
            +"  \"x\": 516.8792725"
            +"},"
            +"{"
            +"\"y\": 371.8327026,"
            +"\"x\": 526.5441284"
            +"},"
            +"{"
            +"\"y\": 330.3126831,"
            +" \"x\": 531.1585693"
            +" },"
            +"{"
            +"\"y\": 289.8967896,"
            +"\"x\": 532.8815918"
            +"},"
            +"{"
            +"\"y\": 295.760498,"
            +"\"x\": 318.7453003"
            +"},"
            +"{"
            +" \"y\": 290.3188171,"
            +" \"x\": 331.4337769"
            +" },"
            +"{"
            +"\"y\": 288.857605,"
            +"\"x\": 344.0565186"
            +"},"
            +"{"
            +"\"y\": 289.964447,"
            +"\"x\": 357.3381348"
            +"},"
            +"{"
            +"\"y\": 297.0440674,"
            +"\"x\": 370.2176819"
            +"},"
            +"{"
            +"\"y\": 299.0540771,"
            +"\"x\": 357.6977844"
            +"},"
            +"{"
            +"\"y\": 300.6704102,"
            +"\"x\": 344.3751831"
            +"},"
            +"{"
            +"\"y\": 299.0969849,"
            +" \"x\": 331.1411133"
            +" },"
            +"{"
            +"\"y\": 293.6487427,"
            +"\"x\": 344.6775208"
            +" },"
            +"{"
            +" \"y\": 265.6499634,"
            +" \"x\": 303.0313721"
            +" },"
            +" {"
            +"   \"y\": 245.9210663,"
            +"   \"x\": 320.273468"
            +"  },"
            +" {"
            +"   \"y\": 243.7492371,"
            +"   \"x\": 342.8881226"
            +" },"
            +"{"
            +"   \"y\": 247.8726349,"
            +"\"x\": 365.1636963"
            +"},"
            +"{"
            +" \"y\": 263.4034729,"
            +" \"x\": 384.873291"
            +" },"
            +"{"
            +" \"y\": 264.3016357,"
            +"\"x\": 364.7226562"
            +"},"
            +"{"
            +"  \"y\": 263.662384,"
            +"\"x\": 343.1068115"
            +" },"
            +" {"
            +" \"y\": 264.1619568,"
            +"  \"x\": 321.9691772"
            +"},"
            +" {"
            +" \"y\": 295.9424133,"
            +" \"x\": 440.2758179"
            +"},"
            +"{"
            +"\"y\": 288.9544373,"
            +" \"x\": 453.3082581"
            +" },"
            +"{"
            +" \"y\": 287.5550232,"
            +" \"x\": 466.5764771"
            +" },"
            +" {"
            +" \"y\": 288.9812317,"
            +" \"x\": 480.1110229"
            +"},"
            +"{"
            +"\"y\": 293.90625,"
            +"\"x\": 493.8017883"
            +"},"
            +"{"
            +"\"y\": 298.0003357,"
            +"\"x\": 480.5523987"
            +"},"
            +"{"
            +"\"y\": 299.4595032,"
            +" \"x\": 466.5645752"
            +" },"
            +" {"
            +" \"y\": 298.1081543,"
            +"\"x\": 452.7465515"
            +"},"
            +" {"
            +"\"y\": 292.1832581,"
            +"\"x\": 465.147522"
            +"},"
            +"{"
            +"\"y\": 263.0756226,"
            +"\"x\": 424.7915039"
            +" },"
            +" {"
            +" \"y\": 246.7488251,"
            +" \"x\": 444.5397644"
            +"},"
            +"{"
            +" \"y\": 242.2443085,"
            +" \"x\": 468.00177"
            +" },"
            +"{"
            +" \"y\": 244.3013611,"
            +" \"x\": 492.2993469"
            +" },"
            +"{"
            +" \"y\": 264.5846252,"
            +" \"x\": 512.2473755"
            +" },"
            +"{"
            +"\"y\": 262.5867615,"
            +"\"x\": 490.7645874"
            +"},"
            +"{"
            +"\"y\": 261.9186096,"
            +"\"x\": 468.2220459"
            +" },"
            +"{"
            +"\"y\": 262.9295349,"
            +"\"x\": 446.1036377"
            +"},"
            +"{"
            +" \"y\": 297.6395569,"
            +"\"x\": 387.9335022"
            +" },"
            +"{"
            +"\"y\": 323.2550659,"
            +"\"x\": 383.4273071"
            +"},"
            +"{"
            +" \"y\": 348.6310425,"
            +" \"x\": 378.8557739"
            +"},"
            +"{"
            +"\"y\": 377.025116,"
            +" \"x\": 371.0310364"
            +" },"
            +"{"
            +"\"y\": 378.413269,"
            +" \"x\": 388.319519"
            +"},"
            +" {"
            +"  \"y\": 377.3381958,"
            +"  \"x\": 429.2042236"
            +" },"
            +" {"
            +" \"y\": 374.605896,"
            +"\"x\": 447.5372314"
            +"},"
            +"{"
            +" \"y\": 346.7474365,"
            +"\"x\": 436.0992737"
            +"},"
            +"{"
            +" \"y\": 321.6994629,"
            +" \"x\": 429.4744873"
            +" },"
            +"{"
            +" \"y\": 296.7819824,"
            +" \"x\": 422.7367249"
            +" },"
            +"{"
            +" \"y\": 362.4373169,"
            +" \"x\": 407.2205811"
            +" },"
            +"{"
            +" \"y\": 426.7028503,"
            +" \"x\": 364.6296387"
            +" },"
            +" {"
            +" \"y\": 415.4924927,"
            +"\"x\": 385.1811523"
            +"},"
            +" {"
            +"\"y\": 414.2524414,"
            +"\"x\": 410.7074585"
            +" },"
            +"{"
            +" \"y\": 415.1342468,"
            +" \"x\": 436.9920044"
            +" },"
            +"{"
            +"\"y\": 424.9750366,"
            +"\"x\": 461.309082"
            +"},"
            +"{"
            +" \"y\": 442.3902588,"
            +"\"x\": 440.663147"
            +" },"
            +"{"
            +"\"y\": 449.1212158,"
            +" \"x\": 411.6611328"
            +"},"
            +"{"
            +" \"y\": 442.7553711,"
            +" \"x\": 384.1807556"
            +"},"
            +"{"
            +" \"y\": 426.5587769,"
            +" \"x\": 386.5618896"
            +"},"
            +" {"
            +"\"y\": 427.7996826,"
            +"\"x\": 411.2698364"
            +"},"
            +"{"
            +"\"y\": 425.5678711,"
            +"\"x\": 435.7932434"
            +"},"
            +"{"
            +" \"y\": 429.5809326,"
            +" \"x\": 436.4645996"
            +" },"
            +" {"
            +" \"y\": 431.8244629,"
            +" \"x\": 410.9641724"
            +"},"
            +"{"
            +"\"y\": 429.8924866,"
            +"\"x\": 387.0514832"
            +" }"
            +" ],"
            +"\"face_token\": \"1d3c6176c74239cda6fae09bc703b4b5\","
            +" \"angle\": {"
            +" \"yaw\": 0.8733408451,"
            +"\"roll\": -0.6557160616,"
            +"\"pitch\": 3.743157148"
            +"}"
            +"}],"
            +" \"face_num\": 1"
            +"},"
            +" \"cached\": 0,"
            +"\"error_code\": 0,"
            +" \"log_id\": 3506632814,"
            +"\"error_msg\": \"SUCCESS\""
            +"}";*/
    /**
     *
     * @Description: 该方法的主要作用：解析人脸检测的json数据
     * @Title: parsingFaceJson
     * @param  @param json_str
     * @param  @return 设定文件
     * @return  返回类型：Landmark
     * @throws
     */
    @SuppressWarnings("serial")
    public static Landmark parsingFaceJson(JSONObject json_str){
        Landmark landmark = new Landmark();
        //开始解析json
        //JSONObject  dataJson=new JSONObject(json_str);
        //找到result节点
        JSONObject response_result=json_str.getJSONObject("result");
        //继续找face_list节点
        JSONArray face_list_jsonArray=response_result.getJSONArray("face_list");
        JSONObject face_list_jsonObject=face_list_jsonArray.getJSONObject(0);
        //找到landmark（关键点）节点，4个关键点位置，左眼中心、右眼中心、鼻尖、嘴中心
        final JSONArray landmark_jsonArray=face_list_jsonObject.getJSONArray("landmark");
        //左眼中心
        landmark.setLeft_eye_zhongxin(new ArrayList<Double>(){
            {add((Double) landmark_jsonArray.getJSONObject(0).get("y"));
                add((Double) landmark_jsonArray.getJSONObject(0).get("x"));}
        });
        //右眼中心
        landmark.setRight_eye_zhongxin(new ArrayList<Double>(){
            {add((Double) landmark_jsonArray.getJSONObject(1).get("y"));
                add((Double) landmark_jsonArray.getJSONObject(1).get("x"));}
        });
        //鼻尖
        landmark.setNose_zhongxin(new ArrayList<Double>(){
            {add((Double) landmark_jsonArray.getJSONObject(2).get("y"));
                add((Double) landmark_jsonArray.getJSONObject(2).get("x"));}
        });
        //嘴中心
        landmark.setMouse_zhongxin(new ArrayList<Double>(){
            {add((Double) landmark_jsonArray.getJSONObject(3).get("y"));
                add((Double) landmark_jsonArray.getJSONObject(3).get("x"));}
        });
        //继续找landmark72节点
        final JSONArray landmark72_jsonArray=face_list_jsonObject.getJSONArray("landmark72");
        //左眼上边
        landmark.setLeft_eye_top(new ArrayList<Double>(){
            {add((Double) landmark72_jsonArray.getJSONObject(14).get("y"));
                add((Double) landmark72_jsonArray.getJSONObject(14).get("x"));}
        });

        //左眼下边
        landmark.setLeft_eye_bottom(new ArrayList<Double>(){
            {add((Double) landmark72_jsonArray.getJSONObject(19).get("y"));
                add((Double) landmark72_jsonArray.getJSONObject(19).get("x"));}
        });
        //右眼上边
        landmark.setRight_eye_top(new ArrayList<Double>(){
            {add((Double) landmark72_jsonArray.getJSONObject(32).get("y"));
                add((Double) landmark72_jsonArray.getJSONObject(32).get("x"));}
        });
        //右眼下边
        landmark.setRight_eye_bottom(new ArrayList<Double>(){
            {add((Double) landmark72_jsonArray.getJSONObject(36).get("y"));
                add((Double) landmark72_jsonArray.getJSONObject(36).get("x"));}
        });
        //嘴巴上边
        landmark.setMouse__top(new ArrayList<Double>(){
            {add((Double) landmark72_jsonArray.getJSONObject(60).get("y"));
                add((Double) landmark72_jsonArray.getJSONObject(60).get("x"));}
        });
        //嘴巴下边
        landmark.setMouse__bottom(new ArrayList<Double>(){
            {add((Double) landmark72_jsonArray.getJSONObject(70).get("y"));
                add((Double) landmark72_jsonArray.getJSONObject(70).get("x"));}
        });
        return landmark;
    }

}
