package com.wuxinfeng.doublev.fi.dao;

import com.wuxinfeng.doublev.fi.entity.VouchersEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * 
 * @author Wu Xinfeng
 * @email 390155409@qq.com
 * @date 2023-03-20 17:47:25
 */
@Mapper
public interface VouchersDao extends BaseMapper<VouchersEntity> {

    void addWrittenVoucher(Long voucherNumber, long book);

    String getWriteOffVoucherByVoucherNumber(Long integer);

    boolean deleteByCode(long code);
}
