package com.hhq.gateway.listener;

import com.hhq.common.exception.GlobalExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static com.hhq.gateway.util.SystemUtil.addDirToPath;

public class SystemListener implements ServletContextListener {
    /**
     * 日志
     */
    private static Logger LOGGER = LoggerFactory.getLogger(SystemListener.class);

    public void contextInitialized(ServletContextEvent arg0) {
        Properties properties = new Properties();
        String location = "classpath:/seetaface.properties";
        try (InputStream is = new DefaultResourceLoader().getResource(location).getInputStream()) {
            properties.load(is);
            LOGGER.info("seetaface config: {}", properties.toString());
        } catch (IOException ex) {
            LOGGER.error("Could not load property file:" + location, ex);
        }

        //获取存放dll文件的绝对路径（假设将dll文件放在系统根目录下的WEB-INF文件夹中）
        String path=arg0.getServletContext().getRealPath("WEB-INF");
        //将此目录添加到系统环境变量中
        addDirToPath(path);
        //加载相应的dll文件，注意要将'\'替换为'/'
        System.load(path.replaceAll("\\\\","/")+"/XXXX.dll");
    }
}
