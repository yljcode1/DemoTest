package com.yao.springtest.blbl.hm.ch06;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @date: 2023-11-17
 * @author: yao
 */
@ConfigurationProperties(prefix = "java")
public class Bean4 {
    private static final Logger log = LoggerFactory.getLogger(Bean4.class);
    private String home;
    private String version;

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
//        log.info("home --> 1234");
//        this.home = "1234";
        this.home = home;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        log.info("--version--->----");
        this.version = version;
    }
}
