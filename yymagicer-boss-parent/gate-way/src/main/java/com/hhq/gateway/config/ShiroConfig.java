
package com.hhq.gateway.config;

import com.hhq.gateway.shiro.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.AnonymousFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;
@Configuration
public class ShiroConfig implements EnvironmentAware {

/**
     * 配置信息
     */

    private Environment environment;

    /**
     * FilterRegistrationBean
     *
     * @return FilterRegistrationBean 用于注册的filter
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
        DelegatingFilterProxy delegatingFilterProxy = new DelegatingFilterProxy("shiroFilter");
        filterRegistration.setFilter(delegatingFilterProxy);
        filterRegistration.setEnabled(true);
        filterRegistration.addUrlPatterns("/*");
        filterRegistration.setDispatcherTypes(DispatcherType.REQUEST);
        return filterRegistration;
    }

    /**
     * corsFilterRegistrationBean
     *
     * @return FilterRegistrationBean 用于注册的filter
     */
    @Bean
    public FilterRegistrationBean corsFilterRegistrationBean() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new CorsFilter(source));
        filterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        filterRegistrationBean.setName("corsFilterRegistrationBean");
        filterRegistrationBean.setEnabled(true);
        return filterRegistrationBean;
    }


    /**
     * ShiroFilterFactoryBean
     *
     * @see org.apache.shiro.spring.web.ShiroFilterFactoryBean
     * @return ShiroFilterFactoryBean
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter() {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager());
        bean.setLoginUrl("/login");
        bean.setUnauthorizedUrl("/unauthor");
        Map<String, Filter> filters = new HashMap<>();
        filters.put("anon", new AnonymousFilter());
        filters.put("authc", new CustomizeAuthcFilter());
        bean.setFilters(filters);

        Map<String, String> chains = new HashMap<>();
        // 配置url路径拦截
        chains.put("/welcome", "anon");
        chains.put("/logOut/**", "anon");
        chains.put("/token/verify", "anon");
        chains.put("/pass/**", "anon");
        chains.put("/faceInfo/**", "anon");
        chains.put("/api/**", "anon");
        chains.put("/face/**", "anon");
        chains.put("/customer/registCustomer", "anon");
        chains.put("/customer/login", "anon");
        chains.put("/user/login", "anon");
        chains.put("/**", "authc");
        bean.setFilterChainDefinitionMap(chains);
        return bean;
    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置realm.
        securityManager.setRealm(myShiroRealm());
        // 自定义缓存实现 使用redis
        //securityManager.setCacheManager(redisCacheManager());
        // 自定义session管理 使用redis
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }
    @Bean
    public RedisCacheManager redisCacheManager() {
        return new RedisCacheManager();
    }

/**
     * SessionManager bean
     *
     * @see DefaultWebSessionManager
     * @return SessionManager
     */

    @Bean(name = "sessionManager")
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new CustomizedSessionManager();
        // sesson检查时间10分钟
        String validationInterval = environment.getProperty("shiro.session.validate.interval.ms", "600000");
        sessionManager.setSessionValidationInterval(Long.valueOf(validationInterval));
        // session超时时间15分钟
        String sessionTimeoutStr = environment.getProperty("login.web.timeout.ms", "3600000");
        Long sessionTimeout = Long.valueOf(sessionTimeoutStr);
        sessionManager.setGlobalSessionTimeout(sessionTimeout);
        sessionManager.setSessionDAO(redisSessionDao());
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setSessionValidationSchedulerEnabled(true);
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.getSessionIdCookie().setName("hhq.jsid");
        String domain = environment.getProperty("shiro.cookie.domain", "");
        if (StringUtils.isNotEmpty(domain)) {
            sessionManager.getSessionIdCookie().setDomain(domain);
        }
        return sessionManager;
    }
    @Bean
    @DependsOn(value = "lifecycleBeanPostProcessor")
    public MyShiroRealm myShiroRealm(){
        return new MyShiroRealm();
    }


    @Bean
    public RedisSessionDao redisSessionDao() {
        return new RedisSessionDao();
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
    @Bean
    @DependsOn(value = "lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
        daap.setProxyTargetClass(true);
        return daap;
    }
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }
    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}

