package com.wuxinfeng.doublev.mm.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.DataPermissionInterceptor;
import com.wuxinfeng.doublev.mm.datapermission.MyDataPermissionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: TODO
 * @Author: Wu Xinfeng
 * @CreateTime: 2023-05-25  07:19
 */
@Configuration
@RequiredArgsConstructor
public class DataPermissionConfig {
    private final MyDataPermissionHandler myDataPermissionHandler;

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        DataPermissionInterceptor dataPermissionInterceptor = new DataPermissionInterceptor();
        dataPermissionInterceptor.setDataPermissionHandler(myDataPermissionHandler);
        interceptor.addInnerInterceptor(dataPermissionInterceptor);
        return interceptor;
    }
}
