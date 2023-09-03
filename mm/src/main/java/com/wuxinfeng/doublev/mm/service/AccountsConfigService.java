package com.wuxinfeng.doublev.mm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wuxinfeng.common.to.EntriesTo;
import com.wuxinfeng.common.utils.PageUtils;
import com.wuxinfeng.doublev.mm.entity.AccountsConfigEntity;
import com.wuxinfeng.doublev.mm.entity.MaterialVouchersEntity;
import com.wuxinfeng.doublev.mm.entity.MovementsEntity;
import com.wuxinfeng.doublev.mm.entity.ValueString;

import java.util.ArrayList;
import java.util.Map;

/**
 * 
 *
 * @author Wu Xinfeng
 * @email 390155409@qq.com
 * @date 2023-08-19 11:35:34
 */
public interface AccountsConfigService extends IService<AccountsConfigEntity> {

    PageUtils queryPage(Map<String, Object> params);

    ArrayList<EntriesTo> test(MovementsEntity moveItem, ArrayList<EntriesTo> entriesForMoveItem);

    /**
     * @description:普通的物料移动
     * @author: Wu Xinfeng
     * @date: 2023/8/25 13:35
     * @email: 390155409@qq.com
     **/
    ArrayList<EntriesTo> BSX(MaterialVouchersEntity materialVouchersEntity,ValueString valueString, MovementsEntity moveItem, ArrayList<EntriesTo> entriesForMoveItem);


    /**
     * @description:采购订单收货
     * @author: Wu Xinfeng
     * @date: 2023/8/25 13:35
     * @email: 390155409@qq.com
     **/
    ArrayList<EntriesTo> KBS(MaterialVouchersEntity materialVouchersEntity,ValueString valueString, MovementsEntity moveItem, ArrayList<EntriesTo> entriesForMoveItem);

    /**
     * @description:GR/IR
     * @author: Wu Xinfeng
     * @date: 2023/8/25 14:25
     * @email: 390155409@qq.com
     **/
    ArrayList<EntriesTo> WRX(MaterialVouchersEntity materialVouchersEntity, ValueString valueString, MovementsEntity moveItem, ArrayList<EntriesTo> entriesForMoveItem);

    /**
     * @description:处理差异
     * @author: Wu Xinfeng
     * @date: 2023/8/25 23:30
     * @email: 390155409@qq.com
     **/
    ArrayList<EntriesTo> PRD(MaterialVouchersEntity materialVouchersEntity, ValueString valueString, MovementsEntity moveItem, ArrayList<EntriesTo> entriesForMoveItem);
}

