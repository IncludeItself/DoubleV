package com.wuxinfeng.common.to;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description: TODO
 * @Author: Wu Xinfeng
 * @CreateTime: 2023-04-22  13:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FiVoucherReturnItem implements Serializable {
    private static final long serialVersionUID = 1L;
    Integer itemNo;
    Float amount;
}
