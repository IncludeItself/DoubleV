package com.wuxinfeng.doublev.mm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wuxinfeng.common.utils.PageUtils;
import com.wuxinfeng.doublev.mm.entity.GoodsItemsEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author Wu Xinfeng
 * @email 390155409@qq.com
 * @date 2023-04-07 12:13:17
 */
public interface GoodsItemsService extends IService<GoodsItemsEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<GoodsItemsEntity> getBatchByPurchaseOrder(Long purchaseOrder);

    GoodsItemsEntity getByEntity(GoodsItemsEntity goodsItemsEntity);

    void updateReceived(Long purchaseOrder, Integer itemNo, Float quantity, float amountGoodBusiness);
}

