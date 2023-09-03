package com.wuxinfeng.doublev.gateway.config.jwt;

import com.wuxinfeng.common.jwt.JwtConstant;
import com.wuxinfeng.common.jwt.JwtService;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @BelongsProject: DoubleV
 * @BelongsPackage: com.wuxinfeng.doublev.gateway.config
 * @Author: yanhongwei
 * @CreateTime: 2023-03-21  20:57
 * @Description: TODO
 * @Version: 1.0
 */
public class JwtSecurityContextRepository implements ServerSecurityContextRepository {

    private static final JwtSecurityContextRepository INSTANCE = new JwtSecurityContextRepository();

    private JwtSecurityContextRepository() {
    }

    JwtService jwtService = new JwtService();

//    private SecurityContext securityContext=null;

    @Override
    public Mono<Void> save(ServerWebExchange exchange, SecurityContext context) {
        return Mono.empty();
    }


    @Override
    public Mono<SecurityContext> load(ServerWebExchange exchange) {
        System.out.printf("调用load");
        ServerHttpRequest request = exchange.getRequest();
        String authorization = request.getHeaders().getFirst(JwtConstant.tokenHeader);
        if(!StringUtils.isBlank(authorization)){
            String token = authorization.substring(JwtConstant.tokenHead.length());
            if(!StringUtils.isBlank(token)){
                String username = jwtService.extractUsername(token);
                if(!StringUtils.isBlank(username)){
                    List<SimpleGrantedAuthority> roles = jwtService.extractRoles(token).stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, roles);
                    return Mono.just(new SecurityContextImpl(authenticationToken));
                }
            }
        }
        return Mono.empty();
    }

    public static JwtSecurityContextRepository getInstance() {
        return INSTANCE;
    }

//    private Mono<SecurityContext> smsLogin(ServerWebExchange exchange) {
//        return Mono.empty();
//
//    }
}
