package com.hhq.gateway.util;

import com.hhq.gateway.config.CustomDataConfig;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

/**
 * 获取yml的配置的参数
 */
public class YmlConfigUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext = null;

    private static Map<String,String> propertiesMap =null;

    public YmlConfigUtil() {
    }
    public static String getConfigByKey(String key) {
        if (propertiesMap ==null){
            CustomDataConfig ymlConfig = applicationContext.getBean(CustomDataConfig.class);
            propertiesMap = ymlConfig.getData();
        }
        return propertiesMap.get(key);
    }
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(YmlConfigUtil.applicationContext == null){
            YmlConfigUtil.applicationContext  = applicationContext;
        }
    }
}
