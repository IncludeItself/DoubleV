package com.wuxinfeng.doublev.mm.to;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @Description: TODO
 * @Author: Wu Xinfeng
 * @CreateTime: 2023-05-28  07:43
 */
@Data
@AllArgsConstructor
public class UserInfoTo {
    private String username;
    private List<String> plantScopes;
}
