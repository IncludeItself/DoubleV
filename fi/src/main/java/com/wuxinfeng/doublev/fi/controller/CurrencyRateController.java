package com.wuxinfeng.doublev.fi.controller;

import java.util.Arrays;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wuxinfeng.doublev.fi.entity.CurrencyRateEntity;
import com.wuxinfeng.doublev.fi.service.CurrencyRateService;
import com.wuxinfeng.common.utils.PageUtils;
import com.wuxinfeng.common.utils.R;



/**
 * 
 *
 * @author Wu Xinfeng
 * @email 390155409@qq.com
 * @date 2023-03-28 19:50:16
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("fi/currencyrate")
public class CurrencyRateController {
    private final CurrencyRateService currencyRateService;

    /**
     * 列表
     */
    @RequestMapping("/list")
 //   @RequiresPermissions("fi:currencyrate:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = currencyRateService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
  //  @RequiresPermissions("fi:currencyrate:info")
    public R info(@PathVariable("id") Integer id){
		CurrencyRateEntity currencyRate = currencyRateService.getById(id);

        return R.ok().put("currencyRate", currencyRate);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
  //  @RequiresPermissions("fi:currencyrate:save")
    public R save(@RequestBody CurrencyRateEntity currencyRate){
		currencyRateService.save(currencyRate);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
  //  @RequiresPermissions("fi:currencyrate:update")
    public R update(@RequestBody CurrencyRateEntity currencyRate){
		currencyRateService.updateById(currencyRate);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
  //  @RequiresPermissions("fi:currencyrate:delete")
    public R delete(@RequestBody Integer[] ids){
		currencyRateService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
