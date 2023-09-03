package com.wuxinfeng.doublev.gateway.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wuxinfeng.common.utils.PageUtils;
import com.wuxinfeng.doublev.gateway.entity.UserInfoEntity;
import com.wuxinfeng.doublev.gateway.vo.AuthResponse;
import com.wuxinfeng.doublev.gateway.vo.RegisterRequest;
import org.springframework.security.core.userdetails.UserDetails;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * 
 *
 * @author Wu Xinfeng
 * @email sunlightcs@gmail.com
 * @date 2023-02-18 13:23:43
 */
public interface UserInfoService extends IService<UserInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void insert(UserInfoEntity userInfoEntity);

    AuthResponse register(RegisterRequest request);

    Mono<UserDetails> findByMobilePhone(String mobilePhone);

}

