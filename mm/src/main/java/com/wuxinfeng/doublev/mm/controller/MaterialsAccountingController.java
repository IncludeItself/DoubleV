package com.wuxinfeng.doublev.mm.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wuxinfeng.doublev.mm.entity.MaterialsAccountingEntity;
import com.wuxinfeng.doublev.mm.service.MaterialsAccountingService;
import com.wuxinfeng.common.utils.PageUtils;
import com.wuxinfeng.common.utils.R;



/**
 * 
 *
 * @author Wu Xinfeng
 * @email 390155409@qq.com
 * @date 2023-08-22 13:45:00
 */
@RestController
@RequestMapping("mm/materialsaccounting")
public class MaterialsAccountingController {
    @Autowired
    private MaterialsAccountingService materialsAccountingService;

    /**
     * 列表
     */
    @RequestMapping("/list")
 //   @RequiresPermissions("mm:materialsaccounting:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = materialsAccountingService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{materialCode}")
  //  @RequiresPermissions("mm:materialsaccounting:info")
    public R info(@PathVariable("materialCode") String materialCode){
		MaterialsAccountingEntity materialsAccounting = materialsAccountingService.getById(materialCode);

        return R.ok().put("materialsAccounting", materialsAccounting);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
  //  @RequiresPermissions("mm:materialsaccounting:save")
    public R save(@RequestBody MaterialsAccountingEntity materialsAccounting){
		materialsAccountingService.save(materialsAccounting);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
  //  @RequiresPermissions("mm:materialsaccounting:update")
    public R update(@RequestBody MaterialsAccountingEntity materialsAccounting){
		materialsAccountingService.updateById(materialsAccounting);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
  //  @RequiresPermissions("mm:materialsaccounting:delete")
    public R delete(@RequestBody String[] materialCodes){
		materialsAccountingService.removeByIds(Arrays.asList(materialCodes));

        return R.ok();
    }

}
