package com.wuxinfeng.doublev.mm.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wuxinfeng.doublev.mm.entity.CurrencyRateEntity;
import com.wuxinfeng.doublev.mm.service.CurrencyRateService;
import com.wuxinfeng.common.utils.PageUtils;
import com.wuxinfeng.common.utils.R;



/**
 * 
 *
 * @author Wu Xinfeng
 * @email 390155409@qq.com
 * @date 2023-08-24 14:11:37
 */
@RestController
@RequestMapping("mm/currencyrate")
public class CurrencyRateController {
    @Autowired
    private CurrencyRateService currencyRateService;

    /**
     * 列表
     */
    @RequestMapping("/list")
 //   @RequiresPermissions("mm:currencyrate:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = currencyRateService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
  //  @RequiresPermissions("mm:currencyrate:info")
    public R info(@PathVariable("id") Integer id){
		CurrencyRateEntity currencyRate = currencyRateService.getById(id);

        return R.ok().put("currencyRate", currencyRate);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
  //  @RequiresPermissions("mm:currencyrate:save")
    public R save(@RequestBody CurrencyRateEntity currencyRate){
		currencyRateService.save(currencyRate);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
  //  @RequiresPermissions("mm:currencyrate:update")
    public R update(@RequestBody CurrencyRateEntity currencyRate){
		currencyRateService.updateById(currencyRate);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
  //  @RequiresPermissions("mm:currencyrate:delete")
    public R delete(@RequestBody Integer[] ids){
		currencyRateService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
