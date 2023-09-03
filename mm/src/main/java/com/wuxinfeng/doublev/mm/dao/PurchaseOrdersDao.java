package com.wuxinfeng.doublev.mm.dao;

import com.wuxinfeng.doublev.mm.entity.PurchaseOrdersEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * 
 * @author Wu Xinfeng
 * @email 390155409@qq.com
 * @date 2023-04-07 12:13:17
 */
@Mapper
public interface PurchaseOrdersDao extends BaseMapper<PurchaseOrdersEntity> {

    Long getLatestPurchaseOrder();

    String getSupplier(Long purchaseOrder);

    String getCompanyCode(Long purchaseOrder);

    boolean updateFiVoucherCodeByMaterialVoucherCode(@Param("newMaterialVoucherCode") long newMaterialVoucherCode, @Param("data") long data);
}
