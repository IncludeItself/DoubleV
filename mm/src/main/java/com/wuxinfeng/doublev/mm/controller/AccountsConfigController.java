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

import com.wuxinfeng.doublev.mm.entity.AccountsConfigEntity;
import com.wuxinfeng.doublev.mm.service.AccountsConfigService;
import com.wuxinfeng.common.utils.PageUtils;
import com.wuxinfeng.common.utils.R;



/**
 * 
 *
 * @author Wu Xinfeng
 * @email 390155409@qq.com
 * @date 2023-08-19 11:35:34
 */
@RestController
@RequestMapping("mm/accountsconfig")
@RequiredArgsConstructor
public class AccountsConfigController {
    private final AccountsConfigService accountsConfigService;

    /**
     * 列表
     */
    @RequestMapping("/list")
 //   @RequiresPermissions("mm:accountsconfig:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = accountsConfigService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
  //  @RequiresPermissions("mm:accountsconfig:info")
    public R info(@PathVariable("id") Integer id){
		AccountsConfigEntity accountsConfig = accountsConfigService.getById(id);

        return R.ok().put("accountsConfig", accountsConfig);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
  //  @RequiresPermissions("mm:accountsconfig:save")
    public R save(@RequestBody AccountsConfigEntity accountsConfig){
		accountsConfigService.save(accountsConfig);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
  //  @RequiresPermissions("mm:accountsconfig:update")
    public R update(@RequestBody AccountsConfigEntity accountsConfig){
		accountsConfigService.updateById(accountsConfig);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
  //  @RequiresPermissions("mm:accountsconfig:delete")
    public R delete(@RequestBody Integer[] ids){
		accountsConfigService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
