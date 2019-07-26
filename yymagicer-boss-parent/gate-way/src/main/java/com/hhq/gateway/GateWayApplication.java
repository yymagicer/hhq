package com.hhq.gateway;

import com.hhq.customer.factory.RegistCustomerFactory;
import com.hhq.gateway.util.SpringContextHelper;
import com.hhq.gateway.util.YmlConfigUtil;
import com.hhq.gateway.websocket.NettyServer;
import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = {
        "com.hhq"
})
@MapperScan("com.hhq.*.mapper.*")
public class GateWayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GateWayApplication.class, args);
        try{
            new NettyServer(12345).start();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    // 在某配置类中添加如下内容
    // 监听的http请求的端口,需要在application配置中添加http.port=端口号  如80
//    @Value("${http.port}")
//    Integer httpPort;

    //正常启用的https端口 如443
//    @Value("${server.port}")
//    Integer httpsPort;
    /**
     * 加载YmlConfigUtil工具类
     * @return
     */
    @Bean
    public YmlConfigUtil ymlConfigUtil(){
        return new YmlConfigUtil();
    }

    /**
     * 注入springContextHelper: <br>
     *
     * @author yangxiaodong<br>
     * @taskId <br>
     * @return <br>
     */
    @Bean
    public SpringContextHelper springHelper() {
        return new SpringContextHelper();
    }
    /**
     * 加载RegistCustomerFactory
     * @return
     */
    @Bean
    public RegistCustomerFactory registCustomerFactory(){
        return new RegistCustomerFactory();
    }
//
//    @Bean
//    public TomcatServletWebServerFactory servletContainer() {
//        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
//            @Override
//            protected void postProcessContext(Context context) {
//                SecurityConstraint constraint = new SecurityConstraint();
//                constraint.setUserConstraint("CONFIDENTIAL");
//                SecurityCollection collection = new SecurityCollection();
//                collection.addPattern("/*");
//                constraint.addCollection(collection);
//                context.addConstraint(constraint);
//            }
//        };
//        tomcat.addAdditionalTomcatConnectors(httpConnector());
//        return tomcat;
//    }
//    @Bean
//    public Connector httpConnector() {
//        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
//        connector.setScheme("http");
//        //Connector监听的http的端口号
//        connector.setPort(httpPort);
//        connector.setSecure(false);
//        //监听到http的端口号后转向到的https的端口号
//        connector.setRedirectPort(httpsPort);
//        return connector;
//    }
}
