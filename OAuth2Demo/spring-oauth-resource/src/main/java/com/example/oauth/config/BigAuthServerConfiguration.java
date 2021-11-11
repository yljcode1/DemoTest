package com.example.oauth.config;

import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

/**
 * @author xiao.K
 * @date 2021/11/4
 */
public class BigAuthServerConfiguration extends AuthorizationServerConfigurerAdapter {

    /**
     * checkTokenAccess 权限配置为isAuthenticate,不然资源服务器来请求403
     * @param
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients()
                .checkTokenAccess("isAuthenticated()");
    }
}
