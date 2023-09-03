package com.wuxinfeng.doublev.fi.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wuxinfeng.doublev.fi.entity.CompanyInfoEntity;
import com.wuxinfeng.doublev.fi.service.CompanyInfoService;
import com.wuxinfeng.common.utils.PageUtils;
import com.wuxinfeng.common.utils.R;



/**
 * 
 *
 * @author Wu Xinfeng
 * @email 390155409@qq.com
 * @date 2023-04-14 18:28:19
 */
@RestController
@RequestMapping("fi/companyinfo")
public class CompanyInfoController {
    @Autowired
    private CompanyInfoService companyInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
 //   @RequiresPermissions("fi:companyinfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = companyInfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{companyCode}")
  //  @RequiresPermissions("fi:companyinfo:info")
    public R info(@PathVariable("companyCode") String companyCode){
		CompanyInfoEntity companyInfo = companyInfoService.getById(companyCode);

        return R.ok().put("companyInfo", companyInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
  //  @RequiresPermissions("fi:companyinfo:save")
    public R save(@RequestBody CompanyInfoEntity companyInfo){
		companyInfoService.save(companyInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
  //  @RequiresPermissions("fi:companyinfo:update")
    public R update(@RequestBody CompanyInfoEntity companyInfo){
		companyInfoService.updateById(companyInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
  //  @RequiresPermissions("fi:companyinfo:delete")
    public R delete(@RequestBody String[] companyCodes){
		companyInfoService.removeByIds(Arrays.asList(companyCodes));

        return R.ok();
    }

}
