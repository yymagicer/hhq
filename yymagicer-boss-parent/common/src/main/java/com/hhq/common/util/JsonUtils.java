package com.hhq.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;
import com.hhq.common.base.BaseReturnResult;
import com.hhq.common.constant.BaseConstant;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * json工具类
 */
public class JsonUtils {
    /**
     * 序列化基础配置
     */
    private static SerializeConfig mapping = new SerializeConfig();

    static {
        mapping.put(Date.class, new SimpleDateFormatSerializer(
                "yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * 私有构造函数
     */
    private JsonUtils() {

    }

    /**
     * Description: javaBean、list、map convert to json string<br>
     *
     * @author zhangwen<br>
     * @taskId <br>
     * @param obj 对象
     * @return <br>
     */
    public static String obj2json(Object obj) {
        return JSON.toJSONString(obj, mapping);
    }

    /**
     * Description: json string convert to javaBean、map<br>
     *
     * @author zhangwen<br>
     * @taskId <br>
     * @param jsonStr json字符串
     * @param clazz 目标对象类型
     * @param <T> 泛型对象
     * @return <br>
     */
    public static <T> T json2obj(String jsonStr, Class<T> clazz) {
        return JSON.parseObject(jsonStr, clazz);
    }

    /**
     * Description: json array string convert to list with javaBean<br>
     *
     * @author zhangwen<br>
     * @taskId <br>
     * @param jsonArrayStr json数字字符串
     * @param clazz 目标对象类型
     * @param <T> 泛型对象
     * @return <br>
     */
    public static <T> List<T> json2list(String jsonArrayStr, Class<T> clazz) {
        return JSON.parseArray(jsonArrayStr, clazz);
    }

    /**
     * Description: json string convert to map<br>
     *
     * @author zhangwen<br>
     * @taskId <br>
     * @param jsonStr json字符串
     * @return <br>
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> json2map(String jsonStr) {
        return json2obj(jsonStr, Map.class);
    }

    /**
     * Description: json string convert to map with javaBean<br>
     *
     * @author zhangwen<br>
     * @taskId <br>
     * @param jsonStr json字符串
     * @param clazz 目标对象类型
     * @param <T> 泛型对象
     * @return <br>
     */
    public static <T> Map<String, T> json2map(String jsonStr, Class<T> clazz) {
        Map<String, T> map = JSON.parseObject(jsonStr,
                new TypeReference<Map<String, T>>() {
                });
        for (Map.Entry<String, T> entry : map.entrySet()) {
            JSONObject obj = (JSONObject) entry.getValue();
            map.put(entry.getKey(), JSONObject.toJavaObject(obj, clazz));
        }
        return map;
    }

    /**
     * 是否是合法的json Description: <br>
     *
     * @author zhangshuxing<br>
     * @taskId <br>
     * @param jsonStr json字符串
     * @return <br>
     */
    public static boolean isValidJsonObject(String jsonStr) {
        try {
            JSONObject.parseObject(jsonStr);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Description: 有正确码,返回信息<br>
     *
     * @author qss<br>
     * @taskId <br>
     * @return <br>
     */
    public static String getSucc() {
        return JSONObject.toJSONString(new BaseReturnResult(true,BaseConstant.RESULT_SUCC),
                SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullListAsEmpty);
    }
    /**
     * Description: 有正确码,带占位符信息<br>
     *
     * @author qss<br>
     * @taskId <br>
     * @return <br>
     */
    public static String getSucc(Object data) {
        return JSONObject.toJSONString(new BaseReturnResult(true,BaseConstant.RESULT_SUCC,data),
                SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullListAsEmpty);
    }

    public static String getFail(String msg) {
        return JSONObject.toJSONString(new BaseReturnResult(false,BaseConstant.RESULT_ERROR,msg),
                SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullListAsEmpty);
    }
    public static String getFail(Integer code,String msg) {
        return JSONObject.toJSONString(new BaseReturnResult(false,code,msg),
                SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullListAsEmpty);
    }
}
