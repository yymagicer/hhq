package com.hhq.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义参数配置，以custom为前缀，列如：custom.data.test,会把test放入到data的map中
 */
@Component
@ConfigurationProperties(prefix="custom")
@Data
public class CustomDataConfig {
    private Map<String,String> data = new HashMap<>();
}
