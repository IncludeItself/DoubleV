package com.wuxinfeng.doublev.mm.service.impl;

import com.wuxinfeng.common.to.EntriesTo;
import com.wuxinfeng.doublev.mm.entity.*;
import com.wuxinfeng.doublev.mm.exception.NoPriceControlException;
import com.wuxinfeng.doublev.mm.service.*;
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

import com.wuxinfeng.doublev.mm.dao.AccountsConfigDao;

@RequiredArgsConstructor
@Service("accountsConfigService")
public class AccountsConfigServiceImpl extends ServiceImpl<AccountsConfigDao, AccountsConfigEntity> implements AccountsConfigService {
    private final MaterialsAccountingService materialsAccountingService;
    private final PlantInfoService plantInfoService;
    private final GoodsItemsService goodsItemsService;
    private final CurrencyRateService currencyRateService;
    private final DeliveryItemsService deliveryItemsService;
    private final PurchaseOrdersService purchaseOrdersService;
    private final SupplierInfoService supplierInfoService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AccountsConfigEntity> page = this.page(
                new Query<AccountsConfigEntity>().getPage(params),
                new QueryWrapper<AccountsConfigEntity>()
        );

        return new PageUtils(page);
    }

    private AccountsConfigEntity getByEntity(AccountsConfigEntity accountsConfigEntity) {
        return baseMapper.getByEntity(accountsConfigEntity);
    }

    @Override
    public ArrayList<EntriesTo> test(MovementsEntity moveItem, ArrayList<EntriesTo> entriesForMoveItem) {
        String s = "This is from test function in AccountsConfigService";
        System.out.println(s);
        return entriesForMoveItem;
    }

    @Override
    public ArrayList<EntriesTo> BSX(MaterialVouchersEntity materialVouchersEntity,ValueString valueString,MovementsEntity moveItem, ArrayList<EntriesTo> entriesForMoveItem) {
        String materialCode = moveItem.getMaterialCode();
        String plant = moveItem.getPlant();
        MaterialsAccountingEntity materialsAccountingEntity = materialsAccountingService.getByMaterialCode(plant, materialCode);
        String valuationClass = materialsAccountingEntity.getValuationClass();
        PlantInfoEntity plantInfoEntity = plantInfoService.getByPlantCode(plant);
        String chartAccounts = plantInfoEntity.getChartAccounts();
        AccountsConfigEntity accountsConfigEntity = getByEntity(new AccountsConfigEntity(null, chartAccounts, valueString.getTransactionKey(), valueString.getAccountModification(), valuationClass, null, null, null));
        Float quantity = moveItem.getQuantity();
        Long account = quantity > 0 ? accountsConfigEntity.getDebitAccount() : accountsConfigEntity.getCreditAccount();
        float amount = materialsAccountingEntity.getValue() * quantity / materialsAccountingEntity.getQtyAll();
        String currency = plantInfoEntity.getCurrency();
        entriesForMoveItem.add(new EntriesTo(null,moveItem.getPlant(),account, currency,amount,currency,amount,null,null,moveItem.getText(),materialCode,quantity,moveItem.getBasicUnit()));

        materialsAccountingService.updateStorage(plant,materialCode,quantity,amount);

        return entriesForMoveItem;
    }

    @Override
    public ArrayList<EntriesTo> KBS(MaterialVouchersEntity materialVouchersEntity,ValueString valueString, MovementsEntity moveItem, ArrayList<EntriesTo> entriesForMoveItem) {
        Long purchaseOrder = materialVouchersEntity.getPurchaseOrder();
        Integer itemNo = moveItem.getItemNo();
        String plant = moveItem.getPlant();
        String materialCode = moveItem.getMaterialCode();
        MaterialsAccountingEntity materialsAccountingEntity = materialsAccountingService.getByMaterialCode(plant, materialCode);
        String valuationClass = materialsAccountingEntity.getValuationClass();
        PlantInfoEntity plantInfoEntity = plantInfoService.getByPlantCode(plant);
        String chartAccounts = plantInfoEntity.getChartAccounts();
        AccountsConfigEntity accountsConfigEntity = getByEntity(new AccountsConfigEntity(null, chartAccounts, valueString.getTransactionKey(), valueString.getAccountModification(), valuationClass, null, null, null));
        Float quantity = moveItem.getQuantity();
        Long account = quantity > 0 ? accountsConfigEntity.getDebitAccount() : accountsConfigEntity.getCreditAccount();
        GoodsItemsEntity goodsItemsEntity = goodsItemsService.getByEntity(new GoodsItemsEntity(null, purchaseOrder, itemNo, materialCode, null, null, null, null, null, null, null, null,null));
        Float quantityBasicUnit = goodsItemsEntity.getQuantityBasicUnit();
        String currencyLocal = plantInfoEntity.getCurrency();

        float amountLocal=0;
        String priceControl = materialsAccountingEntity.getPriceControl();
        if (priceControl=="V" && quantity>0) {
            amountLocal = currencyRateService.getCurrencyRate(currencyLocal, goodsItemsEntity.getCurrency())*goodsItemsEntity.getEstimatedAmount() * quantity / quantityBasicUnit;
            List<DeliveryItemsEntity> deliveryItemsEntities = deliveryItemsService.getByEntity(new DeliveryItemsEntity(null, purchaseOrder, itemNo, null, null, null, null, null,null,null));
            for (DeliveryItemsEntity deliveryItemsEntity : deliveryItemsEntities) {
                amountLocal= amountLocal+deliveryItemsEntity.getEstimatedAmount() * quantity / quantityBasicUnit*currencyRateService.getCurrencyRate(currencyLocal,deliveryItemsEntity.getCurrency());
            }
        } else if (priceControl == "S" || quantity<0) {
            amountLocal=materialsAccountingEntity.getValue()*quantity/goodsItemsEntity.getQuantityBasicUnit();
        }else{
            throw new NoPriceControlException();
        }
        entriesForMoveItem.add(new EntriesTo(null,moveItem.getPlant(),account, currencyLocal,amountLocal,currencyLocal,amountLocal,null,null,moveItem.getText(),materialCode,quantity,moveItem.getBasicUnit()));

        materialsAccountingService.updateStorage(plant,materialCode,quantityBasicUnit,amountLocal);

        return entriesForMoveItem;
    }

    @Override
    public ArrayList<EntriesTo> WRX(MaterialVouchersEntity materialVouchersEntity, ValueString valueString, MovementsEntity moveItem, ArrayList<EntriesTo> entriesForMoveItem) {
        Long purchaseOrder = materialVouchersEntity.getPurchaseOrder();

        String plant = moveItem.getPlant();
        PlantInfoEntity plantInfoEntity = plantInfoService.getByPlantCode(plant);
        String chartAccounts = plantInfoEntity.getChartAccounts();
        String currencyLocal = plantInfoEntity.getCurrency();

        String materialCode = moveItem.getMaterialCode();
        String valuationClass = materialsAccountingService.getByMaterialCode(plant,materialCode).getValuationClass();

        PurchaseOrdersEntity purchaseOrdersEntity = purchaseOrdersService.getByPurchaseOrder(purchaseOrder);
        String supplierId = purchaseOrdersEntity.getSupplier();
        String type = supplierInfoService.getBySupplierId(supplierId).getType();

        AccountsConfigEntity accountsConfigEntity = getByEntity(new AccountsConfigEntity(null, chartAccounts, valueString.getTransactionKey(), valueString.getAccountModification(), valuationClass, type, null, null));
        Float quantity = moveItem.getQuantity();
        Long account = quantity > 0 ? accountsConfigEntity.getCreditAccount() : accountsConfigEntity.getDebitAccount();

        Integer itemNo = moveItem.getItemNo();
        GoodsItemsEntity goodsItemsEntity = goodsItemsService.getByEntity(new GoodsItemsEntity(null, purchaseOrder, itemNo, materialCode, null, null, null, null, null, null, null, null,null));
        float amountGoodBusiness = goodsItemsEntity.getEstimatedAmount() * quantity / goodsItemsEntity.getQuantityBasicUnit();
        String currency = goodsItemsEntity.getCurrency();
        float amountGoodLocal = currencyRateService.getCurrencyRate(currencyLocal, currency) * amountGoodBusiness;
        entriesForMoveItem.add(new EntriesTo(null,plant,account,currency,amountGoodBusiness,currencyLocal,amountGoodLocal,null,null,moveItem.getText(),materialCode, quantity, moveItem.getBasicUnit()));

        goodsItemsService.updateReceived(purchaseOrder,itemNo,quantity,amountGoodBusiness);

        List<DeliveryItemsEntity> deliveryItemsEntities = deliveryItemsService.getByEntity(new DeliveryItemsEntity(null, purchaseOrder, itemNo, null, null, null, null, null,null,null));
        for (DeliveryItemsEntity deliveryItem : deliveryItemsEntities) {
            String currencyDelivery = deliveryItem.getCurrency();
            float amountDeliveryBusiness = deliveryItem.getEstimatedAmount() * quantity / goodsItemsEntity.getQuantityBasicUnit();
            float amountDeliveryLocal = amountDeliveryBusiness * currencyRateService.getCurrencyRate(currencyLocal, currencyDelivery);
            entriesForMoveItem.add(new EntriesTo(null,plant,account,currencyDelivery,amountDeliveryBusiness,currencyLocal,amountDeliveryLocal,null,null,moveItem.getText(),materialCode, null, null));
            deliveryItemsService.updateReceived(deliveryItem,amountDeliveryBusiness);
        }

        return entriesForMoveItem;
    }

    @Override
    public ArrayList<EntriesTo> PRD(MaterialVouchersEntity materialVouchersEntity, ValueString valueString, MovementsEntity moveItem, ArrayList<EntriesTo> entriesForMoveItem) {
        float amount = 0;
        String plant=null;
        String currencyLocal=null;
        String costCenter=null;
        String assignment=null;
        String materialCode=null;
        for (EntriesTo entriesTo : entriesForMoveItem) {
            amount=amount+entriesTo.getAmountLocal();
            plant=entriesTo.getPlant()==null?null:entriesTo.getPlant();
            currencyLocal=entriesTo.getCurrencyLocal()==null?null:entriesTo.getCurrencyLocal();
            costCenter=entriesTo.getCostCenter()==null?null:entriesTo.getCostCenter();
            assignment=entriesTo.getAssignment()==null?null:entriesTo.getAssignment();
            materialCode=entriesTo.getMaterialCode()==null?null:entriesTo.getMaterialCode();
        }

        String chartAccounts = plantInfoService.getByPlantCode(plant).getChartAccounts();
        String valuationClass = materialsAccountingService.getByMaterialCode(plant, materialCode).getValuationClass();

        AccountsConfigEntity accountConfigEntity = getByEntity(new AccountsConfigEntity(null, chartAccounts, valueString.getTransactionKey(), valueString.getAccountModification(), valuationClass, null, null, null));
        Long account = amount > 0 ? accountConfigEntity.getCreditAccount() : accountConfigEntity.getDebitAccount();
        entriesForMoveItem.add(new EntriesTo(null,plant,account,currencyLocal,-amount,currencyLocal,-amount,costCenter,assignment,null,materialCode,null,null));
        return entriesForMoveItem;
    }

}