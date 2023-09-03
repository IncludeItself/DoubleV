package com.wuxinfeng.doublev.gateway.config.jwt;

import com.wuxinfeng.common.jwt.JwtConstant;
import com.wuxinfeng.common.jwt.JwtService;
import com.wuxinfeng.doublev.gateway.config.handler.MyAuthenticationFailureHandler;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatcher;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;
import org.springframework.util.Assert;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: TODO
 * @Author: Wu Xinfeng
 * @CreateTime: 2023-03-29  11:55
 */

public class JwtAuthenticationFilter implements WebFilter {

    private MyAuthenticationFailureHandler authenticationFailureHandler = new MyAuthenticationFailureHandler();
    private JwtService jwtService = new JwtService();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        HttpHeaders headers = request.getHeaders();
        String authorization = headers.getFirst(JwtConstant.tokenHeader);
        if (StringUtils.isBlank(authorization)) return chain.filter(exchange);
        return Mono.just(authorization).flatMap(auth -> {
            String token = auth.substring(JwtConstant.tokenHead.length());
            String username = jwtService.extractUsername(token);
            List<SimpleGrantedAuthority> roles = jwtService.extractRoles(token).stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
            return chain.filter(exchange);
        }).onErrorResume(Exception.class, (ex) -> this.authenticationFailureHandler
                .onAuthenticationFailure(new WebFilterExchange(exchange, chain), ex));
    }

    public void setAuthenticationFailureHandler(MyAuthenticationFailureHandler authenticationFailureHandler) {
        Assert.notNull(authenticationFailureHandler, "authenticationFailureHandler cannot be null");
        this.authenticationFailureHandler = authenticationFailureHandler;
    }
}
