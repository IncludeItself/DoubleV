package com.wuxinfeng.doublev.fi.controller;

import java.util.Arrays;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.wuxinfeng.doublev.fi.entity.EntriesEntity;
import com.wuxinfeng.doublev.fi.service.EntriesService;
import com.wuxinfeng.common.utils.PageUtils;
import com.wuxinfeng.common.utils.R;



/**
 * 
 *
 * @author Wu Xinfeng
 * @email 390155409@qq.com
 * @date 2023-03-20 17:47:26
 */
@RestController
@RequestMapping("fi/entries")
public class EntriesController {
    @Autowired
    private EntriesService entriesService;

    /**
     * 列表
     */
    @RequestMapping("/list")
 //   @RequiresPermissions("fi:entries:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = entriesService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{voucherNumber}")
  //  @RequiresPermissions("fi:entries:info")
    public R info(@PathVariable("voucherNumber") Integer voucherNumber){
		EntriesEntity entries = entriesService.getById(voucherNumber);

        return R.ok().put("entries", entries);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
  //  @RequiresPermissions("fi:entries:save")
    public R save(@RequestBody EntriesEntity entries){
		entriesService.save(entries);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
  //  @RequiresPermissions("fi:entries:update")
    public R update(@RequestBody EntriesEntity entries){
		entriesService.updateById(entries);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
  //  @RequiresPermissions("fi:entries:delete")
    public R delete(@RequestBody Integer[] voucherNumbers){
		entriesService.removeByIds(Arrays.asList(voucherNumbers));

        return R.ok();
    }

}
