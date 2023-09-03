package com.wuxinfeng.common.to;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: TODO
 * @Author: Wu Xinfeng
 * @CreateTime: 2023-04-22  13:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FiVoucherReturnTo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long voucherNumber;
    private List<FiVoucherReturnItem> items;
}
