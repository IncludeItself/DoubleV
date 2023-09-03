package com.wuxinfeng.doublev.mm.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wuxinfeng.doublev.mm.entity.DeliveryItemsEntity;
import com.wuxinfeng.doublev.mm.service.DeliveryItemsService;
import com.wuxinfeng.common.utils.PageUtils;
import com.wuxinfeng.common.utils.R;



/**
 * 
 *
 * @author Wu Xinfeng
 * @email 390155409@qq.com
 * @date 2023-04-07 12:13:17
 */
@RestController
@RequestMapping("mm/deliveryitems")
public class DeliveryItemsController {
    @Autowired
    private DeliveryItemsService deliveryItemsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
 //   @RequiresPermissions("mm:deliveryitems:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = deliveryItemsService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
  //  @RequiresPermissions("mm:deliveryitems:info")
    public R info(@PathVariable("id") Integer id){
		DeliveryItemsEntity deliveryItems = deliveryItemsService.getById(id);

        return R.ok().put("deliveryItems", deliveryItems);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
  //  @RequiresPermissions("mm:deliveryitems:save")
    public R save(@RequestBody DeliveryItemsEntity deliveryItems){
		deliveryItemsService.save(deliveryItems);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
  //  @RequiresPermissions("mm:deliveryitems:update")
    public R update(@RequestBody DeliveryItemsEntity deliveryItems){
		deliveryItemsService.updateById(deliveryItems);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
  //  @RequiresPermissions("mm:deliveryitems:delete")
    public R delete(@RequestBody Integer[] ids){
		deliveryItemsService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
