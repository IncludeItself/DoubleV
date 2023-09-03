package com.wuxinfeng.doublev.mm.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wuxinfeng.doublev.mm.entity.MovementsConfigEntity;
import com.wuxinfeng.doublev.mm.service.MovementsConfigService;
import com.wuxinfeng.common.utils.PageUtils;
import com.wuxinfeng.common.utils.R;



/**
 * 
 *
 * @author Wu Xinfeng
 * @email 390155409@qq.com
 * @date 2023-08-06 16:09:29
 */
@RestController
@RequestMapping("mm/movementsconfig")
public class MovementsConfigController {
    @Autowired
    private MovementsConfigService movementsConfigService;

    /**
     * 列表
     */
    @RequestMapping("/list")
 //   @RequiresPermissions("mm:movementsconfig:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = movementsConfigService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{movementConfigId}")
  //  @RequiresPermissions("mm:movementsconfig:info")
    public R info(@PathVariable("movementConfigId") Integer movementConfigId){
		MovementsConfigEntity movementsConfig = movementsConfigService.getById(movementConfigId);

        return R.ok().put("movementsConfig", movementsConfig);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
  //  @RequiresPermissions("mm:movementsconfig:save")
    public R save(@RequestBody MovementsConfigEntity movementsConfig){
		movementsConfigService.save(movementsConfig);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
  //  @RequiresPermissions("mm:movementsconfig:update")
    public R update(@RequestBody MovementsConfigEntity movementsConfig){
		movementsConfigService.updateById(movementsConfig);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
  //  @RequiresPermissions("mm:movementsconfig:delete")
    public R delete(@RequestBody Integer[] movementConfigIds){
		movementsConfigService.removeByIds(Arrays.asList(movementConfigIds));

        return R.ok();
    }

}
