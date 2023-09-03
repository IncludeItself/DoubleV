package com.wuxinfeng.doublev.mm.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wuxinfeng.doublev.mm.entity.MaterialsBasicEntity;
import com.wuxinfeng.doublev.mm.service.MaterialsBasicService;
import com.wuxinfeng.common.utils.PageUtils;
import com.wuxinfeng.common.utils.R;



/**
 * 
 *
 * @author Wu Xinfeng
 * @email 390155409@qq.com
 * @date 2023-04-06 13:32:46
 */
@RestController
@RequestMapping("mm/materialsbasic")
public class MaterialsBasicController {
    @Autowired
    private MaterialsBasicService materialsBasicService;

    /**
     * 列表
     */
    @RequestMapping("/list")
 //   @RequiresPermissions("mm:materialsbasic:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = materialsBasicService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{materialCode}")
  //  @RequiresPermissions("mm:materialsbasic:info")
    public R info(@PathVariable("materialCode") String materialCode){
		MaterialsBasicEntity materialsBasic = materialsBasicService.getById(materialCode);

        return R.ok().put("materialsBasic", materialsBasic);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
  //  @RequiresPermissions("mm:materialsbasic:save")
    public R save(@RequestBody MaterialsBasicEntity materialsBasic){
		materialsBasicService.save(materialsBasic);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
  //  @RequiresPermissions("mm:materialsbasic:update")
    public R update(@RequestBody MaterialsBasicEntity materialsBasic){
		materialsBasicService.updateById(materialsBasic);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
  //  @RequiresPermissions("mm:materialsbasic:delete")
    public R delete(@RequestBody String[] materialCodes){
		materialsBasicService.removeByIds(Arrays.asList(materialCodes));

        return R.ok();
    }

}
