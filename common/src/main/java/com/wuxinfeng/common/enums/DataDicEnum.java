package com.wuxinfeng.common.enums;

import lombok.AllArgsConstructor;

/**
 * @Description: TODO
 * @Author: Wu Xinfeng
 * @CreateTime: 2023-05-28  10:18
 */
@AllArgsConstructor
public enum DataDicEnum {
    FIELD("Field"),
    MOVEMENT_INDICATOR("movementIndicator"),
    CONSUMING_POSTING("consumingPosting"),
    ESTIMATED_ITEMS("estimatedItem"), GENDER("gender");

    private String field;
}