package com.wuxinfeng.doublev.mm.controller;

import java.util.Arrays;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wuxinfeng.doublev.mm.entity.StockRemainingEntity;
import com.wuxinfeng.doublev.mm.service.StockRemainingService;
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
@RequestMapping("mm/stockremaining")
@RequiredArgsConstructor
public class StockRemainingController {
    private final StockRemainingService stockRemainingService;

    /**
     * 列表
     */
    @RequestMapping("/list")
 //   @RequiresPermissions("mm:stockremaining:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = stockRemainingService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
  //  @RequiresPermissions("mm:stockremaining:info")
    public R info(@PathVariable("id") Integer id){
		StockRemainingEntity stockRemaining = stockRemainingService.getById(id);

        return R.ok().put("stockRemaining", stockRemaining);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
  //  @RequiresPermissions("mm:stockremaining:save")
    public R save(@RequestBody StockRemainingEntity stockRemaining){
		stockRemainingService.save(stockRemaining);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
  //  @RequiresPermissions("mm:stockremaining:update")
    public R update(@RequestBody StockRemainingEntity stockRemaining){
		stockRemainingService.updateById(stockRemaining);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
  //  @RequiresPermissions("mm:stockremaining:delete")
    public R delete(@RequestBody Integer[] ids){
		stockRemainingService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
