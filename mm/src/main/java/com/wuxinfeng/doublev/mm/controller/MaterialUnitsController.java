package com.wuxinfeng.doublev.mm.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wuxinfeng.doublev.mm.entity.MaterialUnitsEntity;
import com.wuxinfeng.doublev.mm.service.MaterialUnitsService;
import com.wuxinfeng.common.utils.PageUtils;
import com.wuxinfeng.common.utils.R;



/**
 * 
 *
 * @author Wu Xinfeng
 * @email 390155409@qq.com
 * @date 2023-04-20 21:40:59
 */
@RestController
@RequestMapping("mm/materialunits")
public class MaterialUnitsController {
    @Autowired
    private MaterialUnitsService materialUnitsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
 //   @RequiresPermissions("mm:materialunits:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = materialUnitsService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{materialCode}")
  //  @RequiresPermissions("mm:materialunits:info")
    public R info(@PathVariable("materialCode") String materialCode){
		MaterialUnitsEntity materialUnits = materialUnitsService.getById(materialCode);

        return R.ok().put("materialUnits", materialUnits);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
  //  @RequiresPermissions("mm:materialunits:save")
    public R save(@RequestBody MaterialUnitsEntity materialUnits){
		materialUnitsService.save(materialUnits);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
  //  @RequiresPermissions("mm:materialunits:update")
    public R update(@RequestBody MaterialUnitsEntity materialUnits){
		materialUnitsService.updateById(materialUnits);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
  //  @RequiresPermissions("mm:materialunits:delete")
    public R delete(@RequestBody String[] materialCodes){
		materialUnitsService.removeByIds(Arrays.asList(materialCodes));

        return R.ok();
    }

}
