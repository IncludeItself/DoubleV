package com.wuxinfeng.doublev.mm.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wuxinfeng.doublev.mm.entity.MaterialTypesEntity;
import com.wuxinfeng.doublev.mm.service.MaterialTypesService;
import com.wuxinfeng.common.utils.PageUtils;
import com.wuxinfeng.common.utils.R;



/**
 * 
 *
 * @author Wu Xinfeng
 * @email 390155409@qq.com
 * @date 2023-08-12 12:34:46
 */
@RestController
@RequestMapping("mm/materialtypes")
public class MaterialTypesController {
    @Autowired
    private MaterialTypesService materialTypesService;

    /**
     * 列表
     */
    @RequestMapping("/list")
 //   @RequiresPermissions("mm:materialtypes:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = materialTypesService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{materialType}")
  //  @RequiresPermissions("mm:materialtypes:info")
    public R info(@PathVariable("materialType") String materialType){
		MaterialTypesEntity materialTypes = materialTypesService.getById(materialType);

        return R.ok().put("materialTypes", materialTypes);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
  //  @RequiresPermissions("mm:materialtypes:save")
    public R save(@RequestBody MaterialTypesEntity materialTypes){
		materialTypesService.save(materialTypes);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
  //  @RequiresPermissions("mm:materialtypes:update")
    public R update(@RequestBody MaterialTypesEntity materialTypes){
		materialTypesService.updateById(materialTypes);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
  //  @RequiresPermissions("mm:materialtypes:delete")
    public R delete(@RequestBody String[] materialTypes){
		materialTypesService.removeByIds(Arrays.asList(materialTypes));

        return R.ok();
    }

}
