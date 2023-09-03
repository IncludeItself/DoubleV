package com.wuxinfeng.doublev.mm.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wuxinfeng.doublev.mm.entity.MovementTypesEntity;
import com.wuxinfeng.doublev.mm.service.MovementTypesService;
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
@RequestMapping("mm/movementtypes")
public class MovementTypesController {
    @Autowired
    private MovementTypesService movementTypesService;

    /**
     * 列表
     */
    @RequestMapping("/list")
 //   @RequiresPermissions("mm:movementtypes:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = movementTypesService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{movementType}")
  //  @RequiresPermissions("mm:movementtypes:info")
    public R info(@PathVariable("movementType") String movementType){
		MovementTypesEntity movementTypes = movementTypesService.getById(movementType);

        return R.ok().put("movementTypes", movementTypes);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
  //  @RequiresPermissions("mm:movementtypes:save")
    public R save(@RequestBody MovementTypesEntity movementTypes){
		movementTypesService.save(movementTypes);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
  //  @RequiresPermissions("mm:movementtypes:update")
    public R update(@RequestBody MovementTypesEntity movementTypes){
		movementTypesService.updateById(movementTypes);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
  //  @RequiresPermissions("mm:movementtypes:delete")
    public R delete(@RequestBody String[] movementTypes){
		movementTypesService.removeByIds(Arrays.asList(movementTypes));

        return R.ok();
    }

}
