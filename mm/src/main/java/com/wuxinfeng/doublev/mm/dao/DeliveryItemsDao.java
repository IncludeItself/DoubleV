package com.wuxinfeng.doublev.mm.dao;

import com.wuxinfeng.doublev.mm.entity.DeliveryItemsEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 
 * 
 * @author Wu Xinfeng
 * @email 390155409@qq.com
 * @date 2023-04-07 12:13:17
 */
@Mapper
public interface DeliveryItemsDao extends BaseMapper<DeliveryItemsEntity> {

    List<DeliveryItemsEntity> getBatchByPurchaseOrder(Long purchaseOrder);

    List<DeliveryItemsEntity> getByEntity(DeliveryItemsEntity deliveryItemsEntity);

    List<String> selectSupplierByPurchaseOrder(Long purchaseOrder);
}
