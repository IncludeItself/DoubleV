package com.wuxinfeng.doublev.mm.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @Description: TODO
 * @Author: Wu Xinfeng
 * @CreateTime: 2023-08-29  09:42
 */
@Data
@AllArgsConstructor
public class SupplierNotExistException extends RuntimeException {

    private String plant;
    private String materialCode;
}
