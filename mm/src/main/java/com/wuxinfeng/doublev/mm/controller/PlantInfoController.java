package com.wuxinfeng.doublev.mm.controller;

import java.util.Arrays;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.wuxinfeng.doublev.mm.entity.PlantInfoEntity;
import com.wuxinfeng.doublev.mm.service.PlantInfoService;
import com.wuxinfeng.common.utils.PageUtils;
import com.wuxinfeng.common.utils.R;



/**
 * 
 *
 * @author Wu Xinfeng
 * @email 390155409@qq.com
 * @date 2023-06-06 07:24:35
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("mm/plantinfo")
public class PlantInfoController {
    private final PlantInfoService plantInfoService;

    /**
     * 打开一个新的账期
     */
    @PostMapping("/period")
    public R period(@RequestBody PlantInfoEntity plantInfo){
        return R.ok().put("data",plantInfoService.activeNewPeriod(plantInfo));
    }

    /**
     * 更新是否允许前期操作
     */
    @PostMapping("/allowLast")
    public R allowLast(@RequestBody PlantInfoEntity plantInfo){
        return R.ok().put("data",plantInfoService.allowLast(plantInfo));
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
 //   @RequiresPermissions("mm:plantinfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = plantInfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{plantCode}")
  //  @RequiresPermissions("mm:plantinfo:info")
    public R info(@PathVariable("plantCode") String plantCode){
		PlantInfoEntity plantInfo = plantInfoService.getById(plantCode);

        return R.ok().put("plantInfo", plantInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
  //  @RequiresPermissions("mm:plantinfo:save")
    public R save(@RequestBody PlantInfoEntity plantInfo){
		plantInfoService.save(plantInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
  //  @RequiresPermissions("mm:plantinfo:update")
    public R update(@RequestBody PlantInfoEntity plantInfo){
		plantInfoService.updateById(plantInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
  //  @RequiresPermissions("mm:plantinfo:delete")
    public R delete(@RequestBody String[] plantCodes){
		plantInfoService.removeByIds(Arrays.asList(plantCodes));

        return R.ok();
    }

}
