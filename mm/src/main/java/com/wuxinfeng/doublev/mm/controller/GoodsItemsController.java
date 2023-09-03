package com.wuxinfeng.doublev.mm.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.wuxinfeng.doublev.mm.entity.GoodsItemsEntity;
import com.wuxinfeng.doublev.mm.service.GoodsItemsService;
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
@RequiredArgsConstructor
@RequestMapping("mm/goodsitems")
public class GoodsItemsController {
    private GoodsItemsService goodsItemsService;


    /**
     * 列表
     */
    @RequestMapping("/list")
 //   @RequiresPermissions("mm:goodsitems:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = goodsItemsService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
  //  @RequiresPermissions("mm:goodsitems:info")
    public R info(@PathVariable("id") Integer id){
		GoodsItemsEntity goodsItems = goodsItemsService.getById(id);

        return R.ok().put("goodsItems", goodsItems);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
  //  @RequiresPermissions("mm:goodsitems:save")
    public R save(@RequestBody GoodsItemsEntity goodsItems){
		goodsItemsService.save(goodsItems);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
  //  @RequiresPermissions("mm:goodsitems:update")
    public R update(@RequestBody GoodsItemsEntity goodsItems){
		goodsItemsService.updateById(goodsItems);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
  //  @RequiresPermissions("mm:goodsitems:delete")
    public R delete(@RequestBody Integer[] ids){
		goodsItemsService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
