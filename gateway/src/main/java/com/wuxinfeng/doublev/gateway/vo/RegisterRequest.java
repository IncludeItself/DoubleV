package com.wuxinfeng.doublev.gateway.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description
 * @Author Wu Xinfeng
 * @Date 2023/2/24 14:15
 **/
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class RegisterRequest {
    private String username;
    private String mobilePhone;
    private long birthday;
    private String password;
    private String role;
    private String title;
    private String email;

}
