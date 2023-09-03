package com.wuxinfeng.common.enums;

import lombok.AllArgsConstructor;

/**
 * @Description: TODO
 * @Author: Wu Xinfeng
 * @CreateTime: 2023-05-28  10:18
 */
@AllArgsConstructor
public enum ScopeFieldEnum {
    PLANT("plant"),
    MATERIAL_CODES("materialCode"),
    CURRENCIES("currency");

    private String field;
}