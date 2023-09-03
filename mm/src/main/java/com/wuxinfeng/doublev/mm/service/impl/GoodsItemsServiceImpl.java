package com.wuxinfeng.doublev.mm.service.impl;

import com.wuxinfeng.doublev.mm.service.DeliveryItemsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuxinfeng.common.utils.PageUtils;
import com.wuxinfeng.common.utils.Query;

import com.wuxinfeng.doublev.mm.dao.GoodsItemsDao;
import com.wuxinfeng.doublev.mm.entity.GoodsItemsEntity;
import com.wuxinfeng.doublev.mm.service.GoodsItemsService;


@Service("goodsItemsService")
@RequiredArgsConstructor
public class GoodsItemsServiceImpl extends ServiceImpl<GoodsItemsDao, GoodsItemsEntity> implements GoodsItemsService {

    private final DeliveryItemsService deliveryItemsService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<GoodsItemsEntity> page = this.page(
                new Query<GoodsItemsEntity>().getPage(params),
                new QueryWrapper<GoodsItemsEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<GoodsItemsEntity> getBatchByPurchaseOrder(Long purchaseOrder) {
        return baseMapper.getBatchByPurchaseOrder(purchaseOrder);
    }

    @Override
    public GoodsItemsEntity getByEntity(GoodsItemsEntity goodsItemsEntity) {
        return baseMapper.getByEntity(goodsItemsEntity);
    }

    @Override
    public void updateReceived(Long purchaseOrder, Integer itemNo, Float quantity, float amountGoodBusiness) {
        GoodsItemsEntity goodsItemsEntity = getByEntity(new GoodsItemsEntity(null, purchaseOrder, itemNo, null, null,null, null, null, null, null, null, null, null));
        goodsItemsEntity.setReceivedQuantity(goodsItemsEntity.getReceivedQuantity()+quantity);
        goodsItemsEntity.setReceivedAmount(goodsItemsEntity.getReceivedAmount()+amountGoodBusiness);
        updateById(goodsItemsEntity);
    }
}