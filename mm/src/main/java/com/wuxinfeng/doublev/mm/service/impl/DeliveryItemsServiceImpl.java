package com.wuxinfeng.doublev.mm.service.impl;

import com.wuxinfeng.doublev.mm.feign.FiFeignService;
import com.wuxinfeng.doublev.mm.service.SupplierInfoService;
import com.wuxinfeng.doublev.mm.vo.SupplierNameVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuxinfeng.common.utils.PageUtils;
import com.wuxinfeng.common.utils.Query;

import com.wuxinfeng.doublev.mm.dao.DeliveryItemsDao;
import com.wuxinfeng.doublev.mm.entity.DeliveryItemsEntity;
import com.wuxinfeng.doublev.mm.service.DeliveryItemsService;


@Service("deliveryItemsService")
@RequiredArgsConstructor
public class DeliveryItemsServiceImpl extends ServiceImpl<DeliveryItemsDao, DeliveryItemsEntity> implements DeliveryItemsService {
    private final FiFeignService fiFeignService;
    private final SupplierInfoService supplierInfoService;


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<DeliveryItemsEntity> page = this.page(
                new Query<DeliveryItemsEntity>().getPage(params),
                new QueryWrapper<DeliveryItemsEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<DeliveryItemsEntity> getBatchByPurchaseOrder(Long purchaseOrder) {
        return baseMapper.getBatchByPurchaseOrder(purchaseOrder);
    }

    @Override
    public List<DeliveryItemsEntity> getByEntity(DeliveryItemsEntity deliveryItemsEntity) {
        return baseMapper.getByEntity(deliveryItemsEntity);
    }

    @Override
    public void updateReceived(DeliveryItemsEntity deliveryItem, float amountDeliveryBusiness) {
        deliveryItem.setReceivedAmount(deliveryItem.getReceivedAmount()+amountDeliveryBusiness);
        updateById(deliveryItem);
    }

    @Override
    public List<SupplierNameVo> getSupplierNameByPurchaseOrders(Long purchaseOrder) {
        ArrayList<SupplierNameVo> supplierNameVoArrayList = new ArrayList<SupplierNameVo>();
        for (String supplier : getSuppliersByPurchaseOrders(purchaseOrder)) {
            supplierNameVoArrayList.add(supplierInfoService.getNameById(supplier));
        }
        return supplierNameVoArrayList;
    }

    private List<String> getSuppliersByPurchaseOrders(Long purchaseOrder) {
        return baseMapper.selectSupplierByPurchaseOrder(purchaseOrder);

    }

}