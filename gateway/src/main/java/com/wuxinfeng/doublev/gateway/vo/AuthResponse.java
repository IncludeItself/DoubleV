package com.wuxinfeng.doublev.gateway.vo;

import com.wuxinfeng.doublev.gateway.entity.UserInfoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

/**
 * @Description
 * @Author Wu Xinfeng
 * @Date 2023/2/24 14:13
 **/
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class AuthResponse {

    private UserInfoEntity userInfo;
    private String token;

    private String refreshToken;
    private ArrayList<String> roleList;
    private boolean sessionTimeout=false;
    private long lastUpdateTime=System.currentTimeMillis();
}
