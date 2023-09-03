package com.wuxinfeng.doublev.gateway.config.handler;

import com.alibaba.fastjson2.JSONObject;
import com.wuxinfeng.common.jwt.JwtConstant;
import com.wuxinfeng.common.jwt.JwtService;
import com.wuxinfeng.common.utils.R;
import com.wuxinfeng.doublev.gateway.entity.UserInfoEntity;
import com.wuxinfeng.doublev.gateway.service.UserRolesScopesService;
import com.wuxinfeng.doublev.gateway.vo.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description 身份验证成功后令response返回Jwt给前台
 * @Author Wu Xinfeng
 * @Date 2023/3/2 10:21
 **/

@RequiredArgsConstructor
@Component
public class MyAuthenticationSuccessHandler implements ServerAuthenticationSuccessHandler {
    private JwtService jwtService = new JwtService();
    private final UserRolesScopesService userRolesScopesService;


    // 认证成功后跳转
    @Override
    public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange, Authentication authentication) {
        System.out.println("调用了MyAuthenticationSuccessHandler");
        return Mono.defer(() -> Mono.just(webFilterExchange.getExchange().getResponse()).flatMap(response -> {
            DataBufferFactory dataBufferFactory = response.bufferFactory();
            // 生成JWT token
            Map<String, Object> map = new HashMap<>(2);
            UserDetails userEntity = (UserDetails) authentication.getPrincipal();
            String username = userEntity.getUsername();
            map.put(JwtConstant.JWT_USERNAME, username);
            map.put(JwtConstant.JWT_ROLES, userEntity.getAuthorities().stream().map(auth -> auth.toString()).collect(Collectors.toList()));
            map.put(JwtConstant.PLANT_SCOPES,userRolesScopesService.getPlantScopesByUsername(username));
            String token = jwtService.generateToken(map, username);
            String refreshToken = jwtService.generateToken(map, username, JwtConstant.refreshExpired);
            AuthResponse authResponse = new AuthResponse();
            authResponse.setUserInfo((UserInfoEntity) userEntity);
            authResponse.setToken(token);
            authResponse.setRefreshToken(refreshToken);
            R result = R.ok().put("data", authResponse);

            DataBuffer dataBuffer = dataBufferFactory.wrap(JSONObject.toJSONString(result).getBytes());
            return response.writeWith(Mono.just(dataBuffer));
        }));
    }
}
