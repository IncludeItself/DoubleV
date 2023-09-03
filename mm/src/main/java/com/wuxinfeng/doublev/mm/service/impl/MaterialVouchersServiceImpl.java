package com.wuxinfeng.doublev.mm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuxinfeng.common.jwt.JwtService;
import com.wuxinfeng.common.to.EntriesTo;
import com.wuxinfeng.common.to.FiVoucherTo;
import com.wuxinfeng.common.utils.PageUtils;
import com.wuxinfeng.common.utils.Query;
import com.wuxinfeng.doublev.mm.dao.MaterialVouchersDao;
import com.wuxinfeng.doublev.mm.entity.*;
import com.wuxinfeng.doublev.mm.interceptor.MMInterceptor;
import com.wuxinfeng.doublev.mm.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service("materialVouchersService")
@RequiredArgsConstructor
public class MaterialVouchersServiceImpl extends ServiceImpl<MaterialVouchersDao, MaterialVouchersEntity> implements MaterialVouchersService {

    private JwtService jwtService = new JwtService();
    private final MovementTypesService movementTypesService;
    private final MaterialsBasicService materialsBasicService;
    private final MaterialUnitsService materialUnitsService;
    private final MovementsConfigService movementsConfigService;
    private final PurchaseOrdersService purchaseOrdersService;
    private final AccountsConfigService accountsConfigService;
    private final MovementsService movementsService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MaterialVouchersEntity> page = this.page(
                new Query<MaterialVouchersEntity>().getPage(params),
                new QueryWrapper<MaterialVouchersEntity>()
        );

        return new PageUtils(page);
    }


    @Override
    @Transactional
    public Long move(MaterialVouchersEntity voucherEntity){
        //物料主数据加锁
        //1.根据materialEntity，根据movementConfig组构fiVoucherTo
        String movementType = voucherEntity.getMovementType();     //movementType
        String movementIndicator = getMovementIndicator(voucherEntity);    //movementIndicator
        String consumingPosting = purchaseOrdersService.getById(voucherEntity.getPurchaseOrder()).getConsumingPosting();    //consumingPosting

        FiVoucherTo fiVoucherTo = new FiVoucherTo(voucherEntity.getDocumentDate(),voucherEntity.getPostDate(),voucherEntity.getReference(),voucherEntity.getHeaderText(),MMInterceptor.threadLocal.get().getUsername(),null);
        List<EntriesTo> entriesTos = new ArrayList<EntriesTo>();

        if (!movementTypesService.isReverseType(movementType)) {
            List<MovementsEntity> moveItems = voucherEntity.getMoveItems();
            for (MovementsEntity moveItem : moveItems) {
                Float quantityOperated = moveItem.getQuantityOperated();
                if (quantityOperated ==0) continue;
                String materialCode = moveItem.getMaterialCode();
                String basicUnit = materialsBasicService.getByMaterialCode(materialCode).getBasicUnit();
                moveItem.setBasicUnit(basicUnit);
                float quantity = materialUnitsService.getRateBasicUnitTo(materialCode, moveItem.getOperateUnit()) * quantityOperated;
                moveItem.setQuantity(quantity);
                String specialStock = moveItem.getSpecialStock();
                boolean qtyUpdating = materialsBasicService.getQtyUpdatingByMaterialCode(materialCode);
                boolean valueUpdating = materialsBasicService.getValueUpdatingByMaterialCode(materialCode);
                List<ValueString> valueStrings = movementsConfigService.getValueStrings(new MovementsConfigEntity(null,movementType,specialStock,qtyUpdating,valueUpdating,movementIndicator,consumingPosting,null,null));
                ArrayList<EntriesTo> entriesForMoveItem = new ArrayList<>();
                for (ValueString valueString : valueStrings) {
                    entriesForMoveItem= extractFiItem(voucherEntity,moveItem, valueString,entriesForMoveItem);
                }
                entriesTos.addAll(entriesForMoveItem);
            }
        }else{
            Long materialVoucherCode = voucherEntity.getMaterialVoucherCode();
            List<MovementsEntity> movementsEntities = movementsService.getByVoucherCode(materialVoucherCode);
            for (MovementsEntity moveItem : movementsEntities) {
                Float quantityOperated = moveItem.getQuantityOperated();
                if (quantityOperated ==0) continue;
                String materialCode = moveItem.getMaterialCode();
                moveItem.setQuantityOperated(-moveItem.getQuantityOperated());
                moveItem.setQuantity(-moveItem.getQuantity());
                String specialStock = moveItem.getSpecialStock();
                boolean qtyUpdating = materialsBasicService.getQtyUpdatingByMaterialCode(materialCode);
                boolean valueUpdating = materialsBasicService.getValueUpdatingByMaterialCode(materialCode);
                movementType=movementTypesService.getByReverseType(movementType).getMovementType();
                List<ValueString> valueStrings = movementsConfigService.getValueStrings(new MovementsConfigEntity(null,movementType,specialStock,qtyUpdating,valueUpdating,movementIndicator,consumingPosting,null,null));
                ArrayList<EntriesTo> entriesForMoveItem = new ArrayList<>();
                for (ValueString valueString : valueStrings) {
                    entriesForMoveItem= extractFiItem(voucherEntity,moveItem, valueString,entriesForMoveItem);
                }
                entriesTos.addAll(entriesForMoveItem);
            }
        }



        fiVoucherTo.setEntries(entriesTos);
        //2.调用财务生成凭证，取回财务凭证号和金额等，完善materialVoucher
//        FiVoucherReturnTo fiVoucherReturnTo = fiFeignService.saveVoucher(fiVoucherTo);
        //3.保存materialEntity和movementsEntity
        return 0L;
    }

    @Override
    @Transactional
    public Long invoice(GoodsItemsEntity goodsItemsEntity){
        Long purchaseOrder = goodsItemsEntity.getPurchaseOrder();

        return 0l;
    }

    private ArrayList<EntriesTo> extractFiItem(MaterialVouchersEntity materialVouchersEntity,MovementsEntity moveItem, ValueString valueString, ArrayList<EntriesTo> entriesForMoveItem) {
        ArrayList<EntriesTo> entriesTos;
        try{
            Method declaredMethod = AccountsConfigService.class.getDeclaredMethod(valueString.getTransactionKey(),MaterialVouchersEntity.class,ValueString.class,MovementsEntity.class,ArrayList.class);
            entriesTos = (ArrayList<EntriesTo>) declaredMethod.invoke(accountsConfigService,materialVouchersEntity,valueString,moveItem,entriesForMoveItem);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return entriesTos;
    }


    private String getMovementIndicator(MaterialVouchersEntity voucherEntity) {
        if (voucherEntity.getPurchaseOrder()!=null) {
            return "F";
        }
        return "";
    }

    //    @Override
    @Transactional
    public Long move_old(MaterialVouchersEntity materialVouchers){
//        Date date = new Date(System.currentTimeMillis());
//
//        MovementTypesEntity movementTypesEntity = movementTypesService.getById(materialVouchers.getMovementType());
//        materialVouchers.setMovementTypeDescription(movementTypesEntity.getMovementTypeDescription());
//        materialVouchers.setDateOperated(date);
//        List<MovementsEntity> moveItems = materialVouchers.getMoveItems();
//
//        for (MovementsEntity movementsEntity : moveItems) {
//            setSomeProperties(movementsEntity);
//        }
//
//        String usedFor = movementTypesEntity.getUsedFor();
//        if (usedFor.equals(EventConstant.PURCHASE_ORDER_RECEIVE)) {
//            return receiveForPurchaseOrder(materialVouchers);
//        }
        return 0l;
    }

    private Long receiveForPurchaseOrder(MaterialVouchersEntity materialVouchers){
//        List<MovementsEntity> moveItems = materialVouchers.getMoveItems();
//        Long purchaseOrder = materialVouchers.getPurchaseOrder();
//        if (purchaseOrder==null) throw new PurchaseOrderMissingException();
//        List<GoodsItemsEntity> goodsItemList = goodsItemsService.getBatchByPurchaseOrder(purchaseOrder);
//        List<DeliveryItemsEntity> deliveiesItemList = deliveryItemsService.getBatchByPurchaseOrder(purchaseOrder);
//        String creator = MMInterceptor.threadLocal.get().getUsername();
//        materialVouchers.setCreator(creator);
//        FiVoucherTo fiVoucherTo = new FiVoucherTo(materialVouchers.getPlant(), materialVouchers.getDocumentDate(), materialVouchers.getPostDate(),purchaseOrder.toString(), materialVouchers.getHeaderText(), creator,null,EventConstant.PURCHASE_ORDER_RECEIVE);
//        List<EntriesTo> entriesTos = new ArrayList<>();
//        for (MovementsEntity moveItem : moveItems) {
//            String materialCode = moveItem.getMaterialCode();
//            String basicUnit = materialsBasicService.getById(materialCode).getBasicUnit();
//            Integer purchaseOrderItemNo = moveItem.getItemNo();
//            float partRate=0;
//            for (GoodsItemsEntity goodsItem : goodsItemList) {
//                if (purchaseOrderItemNo==goodsItem.getItemNo()) {
//                    Float totalAmount = goodsItem.getEstimatedAmount();
//                    Float totalQuan = goodsItem.getQuantityBasicUnit();
//                    Float receivingQuan = moveItem.getQuantity()*materialUnitsService.getRateBasicUnitTo(goodsItem.getMaterialCode(),moveItem.getOperateUnit());
//                    partRate = receivingQuan / totalQuan;
//                    if (partRate!=0) {
//                        EntriesTo entriesTo = new EntriesTo(purchaseOrderItemNo,goodsItem.getCurrency(),totalAmount*partRate,null,null,moveItem.getText(),materialCode,receivingQuan,basicUnit);
//                        entriesTos.add(entriesTo);
//                        for (DeliveryItemsEntity deliveryItem : deliveiesItemList) {
//                            if (purchaseOrderItemNo==deliveryItem.getItemNo()) {
//                                totalAmount = deliveryItem.getEstimatedAmount();
//                                EntriesTo deliveryTo = new EntriesTo(purchaseOrderItemNo,deliveryItem.getCurrency(), totalAmount * partRate, null, null, moveItem.getText(), materialCode, null, null);
//                                entriesTos.add(deliveryTo);
//                            }
//                        }
//                    }
//                    break;
//                }
//            }
//        }
//        System.out.println("entriesTos："+entriesTos);
//        fiVoucherTo.setEntries(entriesTos);
//        Long materialVoucherCode=null;
//        Long fiVoucherCode=null;
//        FiVoucherReturnTo fiReturn=fiFeignService.saveVoucher(fiVoucherTo);
//        try {
//            System.out.println("R里面的Data："+fiReturn);
//            fiVoucherCode= fiReturn.getVoucherNumber();
//            materialVouchers.setFiVoucherCode(fiVoucherCode);
//            save(materialVouchers);
//            materialVoucherCode = materialVouchers.getMaterialVoucherCode();
//            List<FiVoucherReturnItem> itemList = fiReturn.getItems();
//            System.out.println("itemList.size():"+itemList.size()+"，moveItems.size()"+moveItems.size());
//            for (int i = 0; i < moveItems.size(); i++) {
//                MovementsEntity moveItem = moveItems.get(i);
//                moveItem.setMaterialVoucherCode(materialVoucherCode);
//                moveItem.setAmount(itemList.get(i).getAmount());
//            }
//            movementsService.saveBatch(moveItems);
//        }catch (Exception e){
//            e.printStackTrace();
//            System.out.println("有错，凭证号："+fiVoucherCode);
//            rabbitTemplate.convertAndSend("fi-event-exchange","fi.voucher.reverse",fiVoucherCode);
//            throw e;
//        }
        return 0L;
    }

    private void setSomeProperties(MovementsEntity movementsEntity) {
        String materialCode = movementsEntity.getMaterialCode();
        String operateUnit = movementsEntity.getOperateUnit();
        System.out.println("materialCode："+materialCode);
        MaterialsBasicEntity materialsBasicEntity = materialsBasicService.getById(materialCode);
        String materialDescription = materialsBasicEntity.getMaterialDescription();
        String basicUnit = materialsBasicEntity.getBasicUnit();
        float unitRate = materialUnitsService.getUnitRate(materialCode,operateUnit, basicUnit);
        float basicQuan = movementsEntity.getQuantityOperated() * unitRate;

        movementsEntity.setMaterialDescription(materialDescription);
        movementsEntity.setBasicUnit(basicUnit);
        movementsEntity.setQuantity(basicQuan);
    }

}