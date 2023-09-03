package com.wuxinfeng.doublev.fi.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wuxinfeng.doublev.fi.entity.AuxiliaryAccountsEntity;
import com.wuxinfeng.doublev.fi.service.AuxiliaryAccountsService;
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
@RequestMapping("fi/auxiliaryaccounts")
public class AuxiliaryAccountsController {
    @Autowired
    private AuxiliaryAccountsService auxiliaryAccountsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
 //   @RequiresPermissions("fi:auxiliaryaccounts:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = auxiliaryAccountsService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
  //  @RequiresPermissions("fi:auxiliaryaccounts:info")
    public R info(@PathVariable("id") Integer id){
		AuxiliaryAccountsEntity auxiliaryAccounts = auxiliaryAccountsService.getById(id);

        return R.ok().put("auxiliaryAccounts", auxiliaryAccounts);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
  //  @RequiresPermissions("fi:auxiliaryaccounts:save")
    public R save(@RequestBody AuxiliaryAccountsEntity auxiliaryAccounts){
		auxiliaryAccountsService.save(auxiliaryAccounts);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
  //  @RequiresPermissions("fi:auxiliaryaccounts:update")
    public R update(@RequestBody AuxiliaryAccountsEntity auxiliaryAccounts){
		auxiliaryAccountsService.updateById(auxiliaryAccounts);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
  //  @RequiresPermissions("fi:auxiliaryaccounts:delete")
    public R delete(@RequestBody Integer[] ids){
		auxiliaryAccountsService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
