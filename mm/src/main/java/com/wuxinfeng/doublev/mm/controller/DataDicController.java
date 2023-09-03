package com.wuxinfeng.doublev.mm.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.wuxinfeng.common.validator.group.AddGroup;
import com.wuxinfeng.doublev.mm.vo.DataDicVo;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wuxinfeng.doublev.mm.entity.DataDicEntity;
import com.wuxinfeng.doublev.mm.service.DataDicService;
import com.wuxinfeng.common.utils.PageUtils;
import com.wuxinfeng.common.utils.R;



/**
 * 
 *
 * @author Wu Xinfeng
 * @email 390155409@qq.com
 * @date 2023-09-03 12:23:09
 */
@RestController
@RequestMapping("mm/datadic")
@RequiredArgsConstructor
@Validated
public class DataDicController {
    private final DataDicService dataDicService;

    /**
     * 列表
     */
    @RequestMapping("/list")
 //   @RequiresPermissions("mm:datadic:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = dataDicService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{optionDescription}")
  //  @RequiresPermissions("mm:datadic:info")
    public R info(@PathVariable("optionDescription") String optionDescription){
		DataDicEntity dataDic = dataDicService.getById(optionDescription);

        return R.ok().put("dataDic", dataDic);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
  //  @RequiresPermissions("mm:datadic:save")
    public R save(@Validated({AddGroup.class}) @RequestBody DataDicVo dataDicList){
		dataDicService.saveNewBatch(dataDicList.getDataDicList());

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
  //  @RequiresPermissions("mm:datadic:update")
    public R update(@RequestBody DataDicEntity dataDic){
		dataDicService.updateById(dataDic);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
  //  @RequiresPermissions("mm:datadic:delete")
    public R delete(@RequestBody String[] optionDescriptions){
		dataDicService.removeByIds(Arrays.asList(optionDescriptions));

        return R.ok();
    }

}
