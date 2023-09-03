package com.wuxinfeng.doublev.mm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wuxinfeng.common.utils.PageUtils;
import com.wuxinfeng.doublev.mm.entity.DeliveryItemsEntity;
import com.wuxinfeng.doublev.mm.vo.SupplierNameVo;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author Wu Xinfeng
 * @email 390155409@qq.com
 * @date 2023-04-07 12:13:17
 */
public interface DeliveryItemsService extends IService<DeliveryItemsEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<DeliveryItemsEntity> getBatchByPurchaseOrder(Long purchaseOrder);

    List<DeliveryItemsEntity> getByEntity(DeliveryItemsEntity deliveryItemsEntity);

    void updateReceived(DeliveryItemsEntity deliveryItem, float amountDeliveryBusiness);

    List<SupplierNameVo> getSupplierNameByPurchaseOrders(Long purchaseOrder);
}

