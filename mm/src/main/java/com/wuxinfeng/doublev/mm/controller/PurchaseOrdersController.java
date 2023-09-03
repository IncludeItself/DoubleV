package com.wuxinfeng.doublev.mm.controller;

import com.wuxinfeng.common.utils.PageUtils;
import com.wuxinfeng.common.utils.R;
import com.wuxinfeng.common.validator.group.AddGroup;
import com.wuxinfeng.doublev.mm.entity.PurchaseOrdersEntity;
import com.wuxinfeng.doublev.mm.service.PurchaseOrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * @author Wu Xinfeng
 * @email 390155409@qq.com
 * @date 2023-04-07 12:13:17
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("mm/purchaseorders")
public class PurchaseOrdersController {
    private final PurchaseOrdersService purchaseOrdersService;

    /**
     * @description: 通过采购订单获取供应商名字列表
     * @author: Wu Xinfeng
     * @date: 2023/8/28 10:32
     * @email: 390155409@qq.com
     **/
    @PostMapping("/supplier-list-for-purchase-orders")
    public R listSupplierForOrders(@RequestBody List<Long> purchaseOrders) {
        return R.ok().put("data", purchaseOrdersService.getSupplierNamesForOrders(purchaseOrders));
    }


    /**
     * @description: 新建一个采购订单
     * @author: Wu Xinfeng
     * @date: 2023/4/7 12:14
     * @email: 390155409@qq.com
     **/
    @PostMapping("/create")
    public R create(@Validated({AddGroup.class}) @RequestBody PurchaseOrdersEntity purchaseOrders) {
        return R.ok().put("data", purchaseOrdersService.create(purchaseOrders));
    }


    /**
     * 列表
     */
    @RequestMapping("/list")
    //   @RequiresPermissions("mm:purchaseorders:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = purchaseOrdersService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{purchaseOrder}")
    //  @RequiresPermissions("mm:purchaseorders:info")
    public R info(@PathVariable("purchaseOrder") Long purchaseOrder) {
        PurchaseOrdersEntity purchaseOrders = purchaseOrdersService.getById(purchaseOrder);

        return R.ok().put("purchaseOrders", purchaseOrders);
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //  @RequiresPermissions("mm:purchaseorders:update")
    public R update(@RequestBody PurchaseOrdersEntity purchaseOrders) {
        purchaseOrdersService.updateById(purchaseOrders);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //  @RequiresPermissions("mm:purchaseorders:delete")
    public R delete(@RequestBody Long[] purchaseOrders) {
        purchaseOrdersService.removeByIds(Arrays.asList(purchaseOrders));

        return R.ok();
    }

}
