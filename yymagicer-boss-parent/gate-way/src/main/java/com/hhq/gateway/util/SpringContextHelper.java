package com.hhq.gateway.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SpringContextHelper  implements ApplicationContextAware {
    /**
     * appCtx
     */
    private static ApplicationContext appCtx;

    /**
     *
     * Description: 此方法可以把ApplicationContext对象inject到当前类中作为一个静态成员变量。<br>
     *
     * @taskId <br>
     * @param applicationContext ApplicationContext 对象. <br>
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext)  {
        SpringContextHelper.setAppCtx(applicationContext);
    }

    /**
     *
     * Description: 此方法可以把ApplicationContext对象inject到当前类中作为一个静态成员变量。<br>
     *
     * @taskId <br>
     * @param applicationContext ApplicationContext 对象. <br>
     */
    private static void setAppCtx(ApplicationContext applicationContext) {
        appCtx = applicationContext;
    }

    /**
     *
     * Description: 获取ApplicationContext<br>
     *
     * @taskId <br>
     * @return <br>
     */
    public static ApplicationContext getApplicationContext() {
        return appCtx;
    }

    /**
     *
     * Description:这是一个便利的方法，帮助我们快速得到一个BEAN  <br>
     *
     * @taskId <br>
     * @param beanName beanName
     * @return <br>
     */
    public static Object getBean(String beanName) {
        return appCtx.getBean(beanName);
    }
    /**
     *
     * Description: 快速获取Spring容器中的bean<br>
     *
     * @taskId <br>
     * @param <T> <br>
     * @param clazz <br>
     * @return <br>
     */
    public static <T> T getBean(Class<T> clazz) {
        return appCtx.getBean(clazz);
    }

    /**
     * Description: 根据类型type获取Spring容器中的bean<br>
     * @taskId <br>
     * @param clazz <br>
     * @return <br>
     */
    public static <T> Map<String, T> getBeansOfType(Class<T> clazz) {
        return appCtx.getBeansOfType(clazz);
    }
}