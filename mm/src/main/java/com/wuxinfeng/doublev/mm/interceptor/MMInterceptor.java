package com.wuxinfeng.doublev.mm.interceptor;

import com.wuxinfeng.common.jwt.JwtConstant;
import com.wuxinfeng.common.jwt.JwtService;
import com.wuxinfeng.doublev.mm.to.UserInfoTo;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Description: TODO
 * @Author: Wu Xinfeng
 * @CreateTime: 2023-05-27  20:02
 */
@Component
public class MMInterceptor implements HandlerInterceptor {
    private JwtService jwtService=new JwtService();
    public static ThreadLocal<UserInfoTo> threadLocal=new ThreadLocal<>();
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String header = request.getHeader(JwtConstant.tokenHeader);
        String token = header.substring(JwtConstant.tokenHead.length());
        List<String> plantScopes = jwtService.extractPlantScopes(token);
        String username = jwtService.extractUsername(token);
        UserInfoTo userInfoTo = new UserInfoTo(username, plantScopes);
        threadLocal.set(userInfoTo);
        return true;
    }
}
