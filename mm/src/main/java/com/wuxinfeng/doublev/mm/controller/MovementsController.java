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

import com.wuxinfeng.doublev.mm.entity.MovementsEntity;
import com.wuxinfeng.doublev.mm.service.MovementsService;
import com.wuxinfeng.common.utils.PageUtils;
import com.wuxinfeng.common.utils.R;



/**
 * 
 *
 * @author Wu Xinfeng
 * @email 390155409@qq.com
 * @date 2023-04-20 20:14:19
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("mm/movements")
public class MovementsController {
    private final MovementsService movementsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
 //   @RequiresPermissions("mm:movements:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = movementsService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
  //  @RequiresPermissions("mm:movements:info")
    public R info(@PathVariable("id") Integer id){
		MovementsEntity movements = movementsService.getById(id);

        return R.ok().put("movements", movements);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
  //  @RequiresPermissions("mm:movements:save")
    public R save(@RequestBody MovementsEntity movements){
		movementsService.save(movements);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
  //  @RequiresPermissions("mm:movements:update")
    public R update(@RequestBody MovementsEntity movements){
		movementsService.updateById(movements);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
  //  @RequiresPermissions("mm:movements:delete")
    public R delete(@RequestBody Integer[] ids){
		movementsService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
