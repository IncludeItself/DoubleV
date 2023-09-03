package com.wuxinfeng.doublev.fi.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wuxinfeng.doublev.fi.entity.ReasonsEntity;
import com.wuxinfeng.doublev.fi.service.ReasonsService;
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
@RequestMapping("fi/reasons")
public class ReasonsController {
    @Autowired
    private ReasonsService reasonsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
 //   @RequiresPermissions("fi:reasons:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = reasonsService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
  //  @RequiresPermissions("fi:reasons:info")
    public R info(@PathVariable("id") Integer id){
		ReasonsEntity reasons = reasonsService.getById(id);

        return R.ok().put("reasons", reasons);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
  //  @RequiresPermissions("fi:reasons:save")
    public R save(@RequestBody ReasonsEntity reasons){
		reasonsService.save(reasons);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
  //  @RequiresPermissions("fi:reasons:update")
    public R update(@RequestBody ReasonsEntity reasons){
		reasonsService.updateById(reasons);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
  //  @RequiresPermissions("fi:reasons:delete")
    public R delete(@RequestBody Integer[] ids){
		reasonsService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
