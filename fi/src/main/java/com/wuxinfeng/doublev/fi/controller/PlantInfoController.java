package com.wuxinfeng.doublev.fi.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wuxinfeng.doublev.fi.entity.PlantInfoEntity;
import com.wuxinfeng.doublev.fi.service.PlantInfoService;
import com.wuxinfeng.common.utils.PageUtils;
import com.wuxinfeng.common.utils.R;



/**
 * 
 *
 * @author Wu Xinfeng
 * @email 390155409@qq.com
 * @date 2023-04-22 13:13:12
 */
@RestController
@RequestMapping("fi/plantinfo")
public class PlantInfoController {
    @Autowired
    private PlantInfoService plantInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
 //   @RequiresPermissions("fi:plantinfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = plantInfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
  //  @RequiresPermissions("fi:plantinfo:info")
    public R info(@PathVariable("id") Integer id){
		PlantInfoEntity plantInfo = plantInfoService.getById(id);

        return R.ok().put("plantInfo", plantInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
  //  @RequiresPermissions("fi:plantinfo:save")
    public R save(@RequestBody PlantInfoEntity plantInfo){
		plantInfoService.save(plantInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
  //  @RequiresPermissions("fi:plantinfo:update")
    public R update(@RequestBody PlantInfoEntity plantInfo){
		plantInfoService.updateById(plantInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
  //  @RequiresPermissions("fi:plantinfo:delete")
    public R delete(@RequestBody Integer[] ids){
		plantInfoService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
