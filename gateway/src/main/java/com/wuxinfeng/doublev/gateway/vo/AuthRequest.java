package com.wuxinfeng.doublev.gateway.vo;

import lombok.Builder;
import lombok.Data;

/**
 * @Description
 * @Author Wu Xinfeng
 * @Date 2023/2/24 14:16
 **/
@Data
@Builder
public class AuthRequest {
    private String username;
    private String password;
}
