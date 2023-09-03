package com.wuxinfeng.doublev.mm.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wuxinfeng.doublev.mm.entity.WarehouseSpotsEntity;
import com.wuxinfeng.doublev.mm.service.WarehouseSpotsService;
import com.wuxinfeng.common.utils.PageUtils;
import com.wuxinfeng.common.utils.R;



/**
 * 
 *
 * @author Wu Xinfeng
 * @email 390155409@qq.com
 * @date 2023-04-13 13:47:15
 */
@RestController
@RequestMapping("mm/warehousespots")
public class WarehouseSpotsController {
    @Autowired
    private WarehouseSpotsService warehouseSpotsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
 //   @RequiresPermissions("mm:warehousespots:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = warehouseSpotsService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
  //  @RequiresPermissions("mm:warehousespots:info")
    public R info(@PathVariable("id") Long id){
		WarehouseSpotsEntity warehouseSpots = warehouseSpotsService.getById(id);

        return R.ok().put("warehouseSpots", warehouseSpots);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
  //  @RequiresPermissions("mm:warehousespots:save")
    public R save(@RequestBody WarehouseSpotsEntity warehouseSpots){
		warehouseSpotsService.save(warehouseSpots);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
  //  @RequiresPermissions("mm:warehousespots:update")
    public R update(@RequestBody WarehouseSpotsEntity warehouseSpots){
		warehouseSpotsService.updateById(warehouseSpots);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
  //  @RequiresPermissions("mm:warehousespots:delete")
    public R delete(@RequestBody Long[] ids){
		warehouseSpotsService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
