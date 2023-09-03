package com.wuxinfeng.doublev.gateway.config.handler;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.wuxinfeng.common.exception.BizCodeEnum;
import com.wuxinfeng.common.utils.R;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class MyAuthenticationFailureHandler implements ServerAuthenticationFailureHandler {

    public Mono<Void> onAuthenticationFailure(WebFilterExchange webFilterExchange, Exception exception) {
        System.out.println("exception:"+exception);
        return Mono.defer(() -> Mono.just(webFilterExchange.getExchange()
                .getResponse()).flatMap(response -> {
            DataBufferFactory dataBufferFactory = response.bufferFactory();
            R r;
            if (exception instanceof SignatureException) {
                //jwt是乱码
                r = R.error(BizCodeEnum.JWT_SIGNATURE_EXCEPTION.getCode(), BizCodeEnum.JWT_SIGNATURE_EXCEPTION.getMsg());
            } else if (exception instanceof ExpiredJwtException) {
                //jwt已过期
                r = R.error(BizCodeEnum.JWT_EXPIRED_EXCEPTION.getCode(), BizCodeEnum.JWT_EXPIRED_EXCEPTION.getMsg());
            } else if (exception instanceof MalformedJwtException) {
                //jwt格式问题，JWT strings must contain exactly 2 period characters. Found: 0
                r = R.error(BizCodeEnum.JWT_EXPIRED_EXCEPTION.getCode(), BizCodeEnum.JWT_EXPIRED_EXCEPTION.getMsg());
            } else {
                r = R.error("未知错误，看consoler");
                exception.printStackTrace();
            }
            DataBuffer dataBuffer = dataBufferFactory.wrap(JSONObject.toJSONString(r).getBytes());
            return response.writeWith(Mono.just(dataBuffer));
        }));
    }

    @Override
    public Mono<Void> onAuthenticationFailure(WebFilterExchange webFilterExchange, AuthenticationException exception) {
        return Mono.defer(() -> Mono.just(webFilterExchange.getExchange()
                .getResponse()).flatMap(response -> {
            DataBufferFactory dataBufferFactory = response.bufferFactory();
            R r;
            if (exception instanceof BadCredentialsException) {
                // 用户名或密码错误
                r = R.error(BizCodeEnum.USER_PASSWORD_EXCEPTION.getCode(), BizCodeEnum.USER_PASSWORD_EXCEPTION.getMsg());
            }
//            ResultVO<Map<String, Object>> resultVO = ResultVoUtil.error();
//            // 账号不存在
//            if (exception instanceof UsernameNotFoundException) {
//                resultVO = ResultVoUtil.failed(UserStatusCodeEnum.ACCOUNT_NOT_EXIST);
//                // 用户名或密码错误
//            } else if (exception instanceof BadCredentialsException) {
//                resultVO = ResultVoUtil.failed(UserStatusCodeEnum.LOGIN_PASSWORD_ERROR);
//                // 账号已过期
//            } else if (exception instanceof AccountExpiredException) {
//                resultVO = ResultVoUtil.failed(UserStatusCodeEnum.ACCOUNT_EXPIRED);
//                // 账号已被锁定
//            } else if (exception instanceof LockedException) {
//                resultVO = ResultVoUtil.failed(UserStatusCodeEnum.ACCOUNT_LOCKED);
//                // 用户凭证已失效
//            } else if (exception instanceof CredentialsExpiredException) {
//                resultVO = ResultVoUtil.failed(UserStatusCodeEnum.ACCOUNT_CREDENTIAL_EXPIRED);
//                // 账号已被禁用
//            } else if (exception instanceof DisabledException) {
//                resultVO = ResultVoUtil.failed(UserStatusCodeEnum.ACCOUNT_DISABLE);
//            }
            else {
                r = R.error("未知错误，还要再看看，但是，这是AuthenticationException");
                exception.printStackTrace();
            }
            DataBuffer dataBuffer = dataBufferFactory.wrap(JSONObject.toJSONString(r).getBytes());
            return response.writeWith(Mono.just(dataBuffer));
        }));
    }
}
