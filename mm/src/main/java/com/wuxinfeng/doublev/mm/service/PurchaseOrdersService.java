package com.wuxinfeng.doublev.mm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wuxinfeng.common.utils.PageUtils;
import com.wuxinfeng.doublev.mm.entity.PurchaseOrdersEntity;
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
public interface PurchaseOrdersService extends IService<PurchaseOrdersEntity> {

    PageUtils queryPage(Map<String, Object> params);

    long create(PurchaseOrdersEntity purchaseOrder);

    PurchaseOrdersEntity getByPurchaseOrder(Long purchaseOrder);

    List<SupplierNameVo> getSupplierNamesForOrders(List<Long> purchaseOrders);
}

