package com.yao.filter;

import com.yao.config.properties.IgnoreWhiteProperties;
import io.netty.util.internal.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * <p> @description: 认证过滤器 </p>
 * <p> @date: 2022/4/1 </p>
 *
 * @author: yao
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class AuthFilter implements GlobalFilter, Ordered {

    /**
     * 令牌自定义标识
     */
    public static final String AUTHENTICATION = "Authorization";

    /**
     * 令牌前缀
     */
    public static final String PREFIX = "Bearer ";

    /**
     * 令牌秘钥
     */
    public final static String SECRET = "abcdefghijklmnopqrstuvwxyz";

    private final IgnoreWhiteProperties ignoreWhites;


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpRequest.Builder mutate = request.mutate();
        String url = request.getURI().getPath();
        // 跳过不需要验证的路径，白名单
        AntPathMatcher matcher = new AntPathMatcher();
        // 验证白名单
        if (matchers(url, ignoreWhites.getWhites())) {
            return chain.filter(exchange);
        }
        // 获取token
        String token = getToken(request);
        if (StringUtil.isNullOrEmpty(token)) {

        }
        return null;
    }

    private String getToken(ServerHttpRequest request) {
        String token = request.getHeaders().getFirst(AUTHENTICATION);
        // 如果前端设置了令牌前缀，则裁剪掉前缀
        if (StringUtils.hasText(token) && token.startsWith(PREFIX)) {
            token = token.replaceFirst(PREFIX, "");
        }
        return token;
    }

    private boolean matchers(String url, List<String> whites) {
        if (StringUtil.isNullOrEmpty(url) || CollectionUtils.isEmpty(whites)) {
            return false;
        }
        for (String white : whites) {
            if (new AntPathMatcher().match(url, white)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
