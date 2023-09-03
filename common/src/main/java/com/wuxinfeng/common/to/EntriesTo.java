package com.wuxinfeng.common.to;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: TODO
 * @Author: Wu Xinfeng
 * @CreateTime: 2023-04-22  11:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntriesTo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer itemNo;
    private String plant;
    private Long account;
    private String currencyBusiness;
    private Float amountBusiness;
    private String currencyLocal;
    private Float amountLocal;
    private String costCenter;
    private String assignment;
    private String text;
    private String materialCode;
    private Float materialQuantity;
    private String materialUnit;
}
