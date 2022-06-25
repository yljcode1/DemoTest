//package com.yao.config;
//
//import com.yao.service.UserDetailsServiceImpl;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
//import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
//import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
//
//import java.util.Arrays;
//import java.util.concurrent.TimeUnit;
//
///**
// * 认证配置
// *
// * @date: 2022/4/26
// * @author: yao
// */
//@Configuration
//@RequiredArgsConstructor
//@EnableAuthorizationServer
//public class AuthorizationConfig extends AuthorizationServerConfigurerAdapter {
//    private final AuthenticationManager authenticationManager;
//    private final UserDetailsServiceImpl userDetailsService;
////    private final ClientDetailsServiceImpl clientDetailsService;
//    private final RedisConnectionFactory redisConnectionFactory;
//    private final RedisTemplate<String, Object> redisTemplate;
//
//    /**
//     * 配置token存储,这个配置token存到redis中
//     *
//     * @return token存储对象
//     */
//    @Bean
//    public TokenStore tokenStore() {
//        return new ReidsTokenStore(redisConnectionFactory);
//    }
//
//    public AuthorizationCodeServices authorizationCodeServices() {
//        return new RedisAuthorizationCodeServices(redisConnectionFactory);
//    }
//
//    /**
//     * 配置客户端详情
//     *
//     * @param clients 客户端
//     * @throws Exception 异常
//     */
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.withClientDetails(clientDetailsService);
//    }
//
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        // 采用token转jwt，并添加一些自定义信息（token增强）（有默认非必须）
//        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
//        tokenEnhancerChain.setTokenEnhancers(
//                Arrays.asList(jwtAccessTokenConverter(), tokenEnhancer()));
//        endpoints.tokenEnhancer(tokenEnhancerChain)
//                // 配置token存储，一般配置redis存储
//                .tokenStore(tokenStore())
//                // 配置认证管理器
//                .authenticationManager(authenticationManager)
//                // 配置用户详情server，密码模式必须
//                .userDetailsService(userDetailsService)
//                // 配置授权码模式授权码服务,不配置默认为内存模式
//                .authorizationCodeServices(authorizationCodeServices())
//                // 配置grant_type模式，如果不配置则默认使用密码模式、简化模式、验证码模式以及刷新token模式，如果配置了只使用配置中，默认配置失效
//                // 具体可以查询AuthorizationServerEndpointsConfigurer中的getDefaultTokenGranters方法
//                .tokenGranter(tokenGranter(endpoints));
//        // 配置TokenServices参数
//        DefaultTokenServices tokenServices = new DefaultTokenServices();
//        tokenServices.setTokenStore(endpoints.getTokenStore());
//        // 是否支持刷新Token
//        tokenServices.setSupportRefreshToken(true);
//        tokenServices.setReuseRefreshToken(true);
//        tokenServices.setClientDetailsService(endpoints.getClientDetailsService());
//        tokenServices.setTokenEnhancer(endpoints.getTokenEnhancer());
//        // 设置accessToken和refreshToken的默认超时时间(如果clientDetails的为null就取默认的，如果clientDetails的不为null取clientDetails中的)
//        tokenServices.setAccessTokenValiditySeconds((int) TimeUnit.HOURS.toSeconds(2));
//        tokenServices.setRefreshTokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(30));
//        endpoints.tokenServices(tokenServices);
//
//    }
//
//    private Object tokenEnhancer() {
//    }
//
//    /**
//     * jwt格式封装token
//     *
//     * @return
//     */
//    private JwtAccessTokenConverter jwtAccessTokenConverter() {
//        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
//        // 设置jwt加解密秘钥，不设置会随机一个
//        jwtAccessTokenConverter.setSigningKey("yaohw");
//        return jwtAccessTokenConverter;
//    }
//
//}
