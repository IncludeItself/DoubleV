package com.wuxinfeng.doublev.gateway.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wuxinfeng.doublev.gateway.entity.UserRolesScopesEntity;
import com.wuxinfeng.doublev.gateway.service.UserRolesScopesService;
import com.wuxinfeng.common.utils.PageUtils;
import com.wuxinfeng.common.utils.R;



/**
 * 
 *
 * @author Wu Xinfeng
 * @email 390155409@qq.com
 * @date 2023-05-28 08:39:34
 */
@RestController
@RequestMapping("gateway/userrolesscopes")
public class UserRolesScopesController {
    @Autowired
    private UserRolesScopesService userRolesScopesService;

    /**
     * 列表
     */
    @RequestMapping("/list")
 //   @RequiresPermissions("gateway:userrolesscopes:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = userRolesScopesService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{username}")
  //  @RequiresPermissions("gateway:userrolesscopes:info")
    public R info(@PathVariable("username") String username){
		UserRolesScopesEntity userRolesScopes = userRolesScopesService.getById(username);

        return R.ok().put("userRolesScopes", userRolesScopes);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
  //  @RequiresPermissions("gateway:userrolesscopes:save")
    public R save(@RequestBody UserRolesScopesEntity userRolesScopes){
		userRolesScopesService.save(userRolesScopes);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
  //  @RequiresPermissions("gateway:userrolesscopes:update")
    public R update(@RequestBody UserRolesScopesEntity userRolesScopes){
		userRolesScopesService.updateById(userRolesScopes);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
  //  @RequiresPermissions("gateway:userrolesscopes:delete")
    public R delete(@RequestBody String[] usernames){
		userRolesScopesService.removeByIds(Arrays.asList(usernames));

        return R.ok();
    }

}
