package com.wuxinfeng.doublev.fi.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wuxinfeng.doublev.fi.entity.AccountsEntity;
import com.wuxinfeng.doublev.fi.service.AccountsService;
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
@RequestMapping("fi/accounts")
public class AccountsController {
    @Autowired
    private AccountsService accountsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
 //   @RequiresPermissions("fi:accounts:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = accountsService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{accountCode}")
  //  @RequiresPermissions("fi:accounts:info")
    public R info(@PathVariable("accountCode") String accountCode){
		AccountsEntity accounts = accountsService.getById(accountCode);

        return R.ok().put("accounts", accounts);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
  //  @RequiresPermissions("fi:accounts:save")
    public R save(@RequestBody AccountsEntity accounts){
		accountsService.save(accounts);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
  //  @RequiresPermissions("fi:accounts:update")
    public R update(@RequestBody AccountsEntity accounts){
		accountsService.updateById(accounts);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
  //  @RequiresPermissions("fi:accounts:delete")
    public R delete(@RequestBody String[] accountCodes){
		accountsService.removeByIds(Arrays.asList(accountCodes));

        return R.ok();
    }

}
