package com.yao.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * <p> @description: gateway白名单 </p>
 * <p> @date: 2022/4/1 </p>
 *
 * @author: yao
 * @version: 1.0
 */
@Data
@Configuration
@RefreshScope
@ConfigurationProperties(prefix = "security.ignore")
public class IgnoreWhiteProperties {

    /**
     * 白名单配置，网关不需要验证这个白名单
     */
    private List<String> whites = new ArrayList<>();
}
