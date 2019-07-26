package com.hhq.customer.factory;

import com.hhq.customer.service.RegistCustomerService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 注册用户工厂类
 */
public class RegistCustomerFactory implements ApplicationContextAware {
    private static ConcurrentMap<String, RegistCustomerService> beanMap;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, RegistCustomerService> map = applicationContext.getBeansOfType(RegistCustomerService.class);
        beanMap = new ConcurrentHashMap<>();
        beanMap.putAll(map);
    }

    /**
     * get verificationCodeBeanMap
     *
     * @return Returns the beanMap.<br>
     */
    public static ConcurrentMap<String, RegistCustomerService> getBeanMap() {
        return beanMap;
    }

    /**
     * 获取注册用户service: <br>
     *
     * @author yangxiaodong<br>
     * @taskId <br>
     * @param key key值
     * @return <br>
     */
    public static RegistCustomerService getBean(String key) {
        return beanMap.get(key);
    }

    /**
     * 获取注册用户service: <br>
     *
     * @author yangxiaodong<br>
     * @taskId <br>
     * @param clazz key值
     * @return <br>
     */
    public static RegistCustomerService getBean(Class clazz) {
        return beanMap.get(lowerFirstCase(clazz.getSimpleName()));
    }

    public static String lowerFirstCase(String str){

        char[] chars = str.toCharArray();

        //首字母小写方法，大写会变成小写，如果小写首字母会消失
        chars[0] +=32;
        return String.valueOf(chars);
    }
}
