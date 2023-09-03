package com.wuxinfeng.doublev.gateway.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuxinfeng.common.jwt.JwtService;
import com.wuxinfeng.common.utils.PageUtils;
import com.wuxinfeng.common.utils.Query;
import com.wuxinfeng.doublev.gateway.dao.UserInfoDao;
import com.wuxinfeng.doublev.gateway.entity.UserInfoEntity;
import com.wuxinfeng.doublev.gateway.service.UserInfoService;
import com.wuxinfeng.doublev.gateway.vo.AuthResponse;
import com.wuxinfeng.doublev.gateway.vo.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.sql.Date;
import java.util.Map;


@Service("userInfoService")
@RequiredArgsConstructor
public class UserInfoServiceImpl extends ServiceImpl<UserInfoDao, UserInfoEntity> implements UserInfoService,ReactiveUserDetailsService {


    private JwtService jwtService=new JwtService();
    private final PasswordEncoder passwordEncoder;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UserInfoEntity> page = this.page(
                new Query<UserInfoEntity>().getPage(params),
                new QueryWrapper<UserInfoEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void insert(UserInfoEntity userInfoEntity) {
        baseMapper.insert(userInfoEntity);
    }

    @Override
    public AuthResponse register(RegisterRequest request) {
        Date sqlBirthday = new Date(request.getBirthday());
        UserInfoEntity newUser = UserInfoEntity.builder()
                .username(request.getUsername())
                .mobilePhone(request.getMobilePhone())
                .birthday(sqlBirthday)
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .title(request.getTitle())
                .email(request.getEmail())
                .timeLast(new Date(System.currentTimeMillis()))
                .timeCreated(new Date(System.currentTimeMillis()))
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .build();
        baseMapper.insert(newUser);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setUserInfo(newUser);
        authResponse.setToken(jwtService.generateToken(newUser.getUsername()));
        return authResponse;
    }

    @Override
    public Mono<UserDetails> findByMobilePhone(String mobilePhone) {
        UserInfoEntity targetUser = baseMapper.selectOne(new QueryWrapper<UserInfoEntity>().eq("mobile_phone", mobilePhone));
        if(targetUser==null){
            System.out.println("手机号不存在吴鑫峰说");
            throw new UsernameNotFoundException("手机号不存在吴鑫峰说");
        }
        System.out.println("targetUserByMobilePhone: "+targetUser);
        return Mono.just(targetUser);
    }

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        System.out.println("开始调用findByUsername");
        UserInfoEntity targetUser = baseMapper.selectOne(new QueryWrapper<UserInfoEntity>().eq("username", username));
//        if(targetUser==null){
//            targetUser = baseMapper.selectOne(new QueryWrapper<UserInfoEntity>().eq("mobile_phone", username));
//        }
        if(targetUser==null){
            System.out.println("用户名不存在吴鑫峰说");
            throw new UsernameNotFoundException("用户名不存在吴鑫峰说");
        }
        System.out.println("targetUser: "+targetUser);
        return Mono.just(targetUser);
    }


}