package com.wuxinfeng.doublev.fi.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wuxinfeng.doublev.fi.entity.CostcentersEntity;
import com.wuxinfeng.doublev.fi.service.CostcentersService;
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
@RequestMapping("fi/costcenters")
public class CostcentersController {
    @Autowired
    private CostcentersService costcentersService;

    /**
     * 列表
     */
    @RequestMapping("/list")
 //   @RequiresPermissions("fi:costcenters:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = costcentersService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
  //  @RequiresPermissions("fi:costcenters:info")
    public R info(@PathVariable("id") String id){
		CostcentersEntity costcenters = costcentersService.getById(id);

        return R.ok().put("costcenters", costcenters);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
  //  @RequiresPermissions("fi:costcenters:save")
    public R save(@RequestBody CostcentersEntity costcenters){
		costcentersService.save(costcenters);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
  //  @RequiresPermissions("fi:costcenters:update")
    public R update(@RequestBody CostcentersEntity costcenters){
		costcentersService.updateById(costcenters);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
  //  @RequiresPermissions("fi:costcenters:delete")
    public R delete(@RequestBody String[] ids){
		costcentersService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
