package com.wuxinfeng.doublev.gateway.config.handler;

import com.alibaba.fastjson2.JSONObject;
import com.wuxinfeng.common.exception.BizCodeEnum;
import com.wuxinfeng.common.utils.R;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import java.nio.charset.Charset;

@Component
public class MyAccessDeniedHandler implements ServerAccessDeniedHandler {

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, AccessDeniedException denied) {
        return Mono.defer(() -> Mono.just(exchange.getResponse()))
                .flatMap(response -> {
                    response.setStatusCode(HttpStatus.FORBIDDEN);
                    response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
                    DataBufferFactory dataBufferFactory = response.bufferFactory();
                    String result = JSONObject.toJSONString(R.error(BizCodeEnum.PERMISSION_DENIED_EXCEPTION.getCode(),BizCodeEnum.PERMISSION_DENIED_EXCEPTION.getMsg()));
                    DataBuffer buffer = dataBufferFactory.wrap(result.getBytes(Charset.defaultCharset()));
                    return response.writeWith(Mono.just(buffer));
                });

    }
}
