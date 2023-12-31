package com.wuxinfeng.doublev.gateway.config;

import com.alibaba.fastjson2.JSONObject;
import com.wuxinfeng.common.utils.R;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import java.util.Collection;

/**
 * @Description
 * @Author Wu Xinfeng
 * @Date 2023/3/2 11:36
 **/

@Component
public class MyAuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {

    private final AntPathMatcher antPathMatcher=new AntPathMatcher();


    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> authentication, AuthorizationContext authorizationContext) {
        return authentication.map(auth -> {
            ServerWebExchange exchange = authorizationContext.getExchange();
            ServerHttpRequest request = exchange.getRequest();
            Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
            for (GrantedAuthority authority : authorities) {
                String authorityAuthority = authority.getAuthority();
                String path = request.getURI().getPath();
                // TODO
                // 查询用户访问所需角色进行对比
                if (antPathMatcher.match(authorityAuthority, path)) {
                    return new AuthorizationDecision(true);
                }
            }
            return new AuthorizationDecision(true);// TODO为了测试令其全为true
        }).defaultIfEmpty(new AuthorizationDecision(false));
    }

    @Override
    public Mono<Void> verify(Mono<Authentication> authentication, AuthorizationContext object) {
        return check(authentication, object)
                .filter(AuthorizationDecision::isGranted)
                .switchIfEmpty(Mono.defer(() -> {
                    String body = JSONObject.toJSONString(R.error("权限 不对2233"));
                    return Mono.error(new AccessDeniedException(body));
                })).flatMap(d -> Mono.empty());
    }
}
