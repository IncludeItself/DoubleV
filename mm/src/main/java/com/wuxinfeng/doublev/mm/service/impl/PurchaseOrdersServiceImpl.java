package com.wuxinfeng.doublev.mm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuxinfeng.common.utils.PageUtils;
import com.wuxinfeng.common.utils.Query;
import com.wuxinfeng.doublev.mm.dao.PurchaseOrdersDao;
import com.wuxinfeng.doublev.mm.entity.DeliveryItemsEntity;
import com.wuxinfeng.doublev.mm.entity.GoodsItemsEntity;
import com.wuxinfeng.doublev.mm.entity.PurchaseOrdersEntity;
import com.wuxinfeng.doublev.mm.exception.SupplierNotExistException;
import com.wuxinfeng.doublev.mm.exception.UnitNotFoundException;
import com.wuxinfeng.doublev.mm.interceptor.MMInterceptor;
import com.wuxinfeng.doublev.mm.service.*;
import com.wuxinfeng.doublev.mm.vo.SupplierNameVo;
import com.wuxinfeng.doublev.mm.vo.PurchaseDeliveryItemsVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("purchaseOrdersService")
@RequiredArgsConstructor
public class PurchaseOrdersServiceImpl extends ServiceImpl<PurchaseOrdersDao, PurchaseOrdersEntity> implements PurchaseOrdersService {

    private final GoodsItemsService goodsItemsService;
    private final MaterialUnitsService materialUnitsService;
    private final DeliveryItemsService deliveryItemsService;
    private final SupplierInfoService supplierInfoService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PurchaseOrdersEntity> page = this.page(
                new Query<PurchaseOrdersEntity>().getPage(params),
                new QueryWrapper<PurchaseOrdersEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional
    public long create(PurchaseOrdersEntity purchaseOrder) {
        String plant = purchaseOrder.getPlantCode();
        if (!supplierInfoService.supplierExist(plant, purchaseOrder.getSupplier())) {
            throw new SupplierNotExistException(plant, purchaseOrder.getSupplier());
        }
        purchaseOrder.setDateCreated(new Date(System.currentTimeMillis()));
        purchaseOrder.setCreator(MMInterceptor.threadLocal.get().getUsername());
        if (!save(purchaseOrder)) return 0l;
        long order = purchaseOrder.getPurchaseOrder();
        List<GoodsItemsEntity> goodsItemsEntityList = purchaseOrder.getGoodsItemsEntityList();
        List<PurchaseDeliveryItemsVo> purchaseDeliveryItemsVoList = purchaseOrder.getPurchaseDeliveryItemsVoList();
        List<DeliveryItemsEntity> deliveryItemsEntityList = new ArrayList<DeliveryItemsEntity>();

        for(int i=0;i<goodsItemsEntityList.size();i++){
            GoodsItemsEntity goodsItem = goodsItemsEntityList.get(i);
            Float rateBasicUnitTo = materialUnitsService.getRateBasicUnitTo(goodsItem.getMaterialCode(), goodsItem.getOrderUnit());
            if (rateBasicUnitTo>0) {
                goodsItem.setQuantityBasicUnit(goodsItem.getOrderQuan() * rateBasicUnitTo);
                goodsItem.setItemNo(i+1);
                goodsItem.setPurchaseOrder(order);
            }else{
                throw new UnitNotFoundException();
            }
        }
        System.out.println("goodsItemsEntityList:"+goodsItemsEntityList);
        goodsItemsService.saveBatch(goodsItemsEntityList);

        for (PurchaseDeliveryItemsVo item : purchaseDeliveryItemsVoList) {

            if (!supplierInfoService.supplierExist(plant,item.getSupplier())) {
                throw new SupplierNotExistException(plant,item.getSupplier());
            }

            String supplier = item.getSupplier();
            String estimatedItem = item.getEstimatedItem();
            String currency = item.getCurrency();
            List<DeliveryItemsEntity> deliveryList = item.getDeliveryItemsEntityList();
            for (int i = 0; i < deliveryList.size(); i++) {
                DeliveryItemsEntity deliveryItem = deliveryList.get(i);
                Float estimatedAmount = deliveryItem.getEstimatedAmount();
                if (estimatedAmount != 0 && estimatedAmount != null) {
                    deliveryItem.setPurchaseOrder(order);
                    deliveryItem.setDeliverySupplier(supplier);
                    deliveryItem.setEstimatedItem(estimatedItem);
                    deliveryItem.setCurrency(currency);
                    deliveryItem.setItemNo(i+1);
                    deliveryItemsEntityList.add(deliveryItem);
                }
            }
        }
        deliveryItemsService.saveBatch(deliveryItemsEntityList);
        return order;
    }

    @Override
    public PurchaseOrdersEntity getByPurchaseOrder(Long purchaseOrder) {
        return getById(purchaseOrder);
    }

    @Override
    public List<SupplierNameVo> getSupplierNamesForOrders(List<Long> purchaseOrders) {
        List<SupplierNameVo> supplierNameVos = new ArrayList<SupplierNameVo>();


        for (int i = 0; i < purchaseOrders.size(); i++) {
            if (i==0) {
                supplierNameVos.add(supplierInfoService.getNameById(getByPurchaseOrder(purchaseOrders.get(i)).getSupplier()));

                supplierNameVos.addAll(deliveryItemsService.getSupplierNameByPurchaseOrders(purchaseOrders.get(i)));
            }else{

                List<SupplierNameVo> supplierNameVoByPurchaseOrder = deliveryItemsService.getSupplierNameByPurchaseOrders(purchaseOrders.get(i));

                supplierNameVoByPurchaseOrder.add(supplierInfoService.getNameById(getByPurchaseOrder(purchaseOrders.get(i)).getSupplier()));

                supplierNameVos = supplierNameVos.stream().filter(supplierNameVoByPurchaseOrder::contains).collect(Collectors.toList());
            }
        }


        return supplierNameVos;
    }

}