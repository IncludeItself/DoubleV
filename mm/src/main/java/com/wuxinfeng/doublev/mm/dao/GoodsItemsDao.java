package com.wuxinfeng.doublev.mm.dao;

import com.wuxinfeng.doublev.mm.entity.GoodsItemsEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wuxinfeng.doublev.mm.entity.PurchaseOrdersEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * 
 * @author Wu Xinfeng
 * @email 390155409@qq.com
 * @date 2023-04-07 12:13:17
 */
@Mapper
public interface GoodsItemsDao extends BaseMapper<GoodsItemsEntity> {

    public List<GoodsItemsEntity> selectByPurchaseOrder(@Param("purchaseOrders") long order);

    List<GoodsItemsEntity> getBatchByPurchaseOrder(Long purchaseOrder);

    GoodsItemsEntity getByEntity(GoodsItemsEntity goodsItemsEntity);
}
