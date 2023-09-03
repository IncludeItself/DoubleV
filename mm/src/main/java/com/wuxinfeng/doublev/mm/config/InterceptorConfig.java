package com.wuxinfeng.doublev.mm.config;

import com.wuxinfeng.doublev.mm.interceptor.MMInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description: TODO
 * @Author: Wu Xinfeng
 * @CreateTime: 2023-05-27  20:16
 */
@Configuration
@RequiredArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {
    private final MMInterceptor mmInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(mmInterceptor);
    }
}
