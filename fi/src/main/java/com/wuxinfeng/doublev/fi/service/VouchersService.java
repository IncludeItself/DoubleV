package com.wuxinfeng.doublev.fi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wuxinfeng.common.to.FiVoucherReturnTo;
import com.wuxinfeng.common.to.FiVoucherTo;
import com.wuxinfeng.common.utils.PageUtils;
import com.wuxinfeng.doublev.fi.entity.VouchersEntity;
import com.wuxinfeng.doublev.fi.exception.DuplicateWriteOffException;
import com.wuxinfeng.doublev.fi.exception.WriteOffCleardException;

import java.util.Map;

/**
 * 
 *
 * @author Wu Xinfeng
 * @email 390155409@qq.com
 * @date 2023-03-20 17:47:25
 */
public interface VouchersService extends IService<VouchersEntity> {

    PageUtils queryPage(Map<String, Object> params);

    long book(VouchersEntity voucher,Map<String,String> headers);

    long writeOff(long voucherNumber, Map<String, String> headers) throws DuplicateWriteOffException, WriteOffCleardException;

    boolean isWrittenOff(long integer);

    FiVoucherReturnTo book(FiVoucherTo voucher);
}

