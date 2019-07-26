package com.hhq.common.util;

import okhttp3.*;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 *OkHttp3工具类
 */
public class OkHttp3Util {
    //MEDIA_TYPE <==> Content-Type
    private static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");
    //MEDIA_TYPE_TEXT post请求不是application/x-www-form-urlencoded的，全部直接返回，不作处理，即不会解析表单数据来放到request parameter map中。所以通过request.getParameter(name)是获取不到的。只能使用最原始的方式，读取输入流来获取。
    private static final MediaType MEDIA_TYPE_TEXT = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");

    /**
     * get请求
     * @param url 请求链接地址
     * @return
     */
    public static String sendByGetUrl(String url) {
        String result;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            assert response.body() != null;
            result = response.body().string();
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * post,json方式请求
     * @param url 请求链接地址
     * @param json json参数
     * @return
     */
    public static String sendByPostJson(String url, String json) {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(MEDIA_TYPE_JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            assert response.body() != null;
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * post,map方式请求
     * @param url 请求链接地址
     * @param params map参数
     * @return
     */
    public static String sendByPostMap(String url, Map<String, String> params) {
        String result;
        OkHttpClient client = new OkHttpClient();
        StringBuilder content = new StringBuilder();
        Set<Map.Entry<String, String>> entrys = params.entrySet();
        Iterator<Map.Entry<String, String>> iterator = params.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            content.append(entry.getKey()).append("=").append(entry.getValue());
            if (iterator.hasNext()) {
                content.append("&");
            }
        }
        RequestBody requestBody = RequestBody.create(MEDIA_TYPE_TEXT, content.toString());
        Request request = new Request.Builder().url(url).post(requestBody).build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            assert response.body() != null;
            result = response.body().string();
            System.out.println("result = " + result);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
