package com.wuxinfeng.doublev.mm.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wuxinfeng.doublev.mm.entity.SupplierInfoEntity;
import com.wuxinfeng.doublev.mm.service.SupplierInfoService;
import com.wuxinfeng.common.utils.PageUtils;
import com.wuxinfeng.common.utils.R;



/**
 * 
 *
 * @author Wu Xinfeng
 * @email 390155409@qq.com
 * @date 2023-08-25 14:45:14
 */
@RestController
@RequestMapping("mm/supplierinfo")
public class SupplierInfoController {
    @Autowired
    private SupplierInfoService supplierInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
 //   @RequiresPermissions("mm:supplierinfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = supplierInfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{supplier}")
  //  @RequiresPermissions("mm:supplierinfo:info")
    public R info(@PathVariable("supplier") Integer supplier){
		SupplierInfoEntity supplierInfo = supplierInfoService.getById(supplier);

        return R.ok().put("supplierInfo", supplierInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
  //  @RequiresPermissions("mm:supplierinfo:save")
    public R save(@RequestBody SupplierInfoEntity supplierInfo){
		supplierInfoService.save(supplierInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
  //  @RequiresPermissions("mm:supplierinfo:update")
    public R update(@RequestBody SupplierInfoEntity supplierInfo){
		supplierInfoService.updateById(supplierInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
  //  @RequiresPermissions("mm:supplierinfo:delete")
    public R delete(@RequestBody Integer[] suppliers){
		supplierInfoService.removeByIds(Arrays.asList(suppliers));

        return R.ok();
    }

}
