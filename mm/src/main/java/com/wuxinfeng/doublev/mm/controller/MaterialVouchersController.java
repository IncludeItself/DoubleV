package com.wuxinfeng.doublev.mm.controller;

import java.util.Arrays;
import java.util.Map;

import com.wuxinfeng.common.validator.group.AddGroup;
import com.wuxinfeng.doublev.mm.validator.PeriodAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.wuxinfeng.doublev.mm.entity.MaterialVouchersEntity;
import com.wuxinfeng.doublev.mm.service.MaterialVouchersService;
import com.wuxinfeng.common.utils.PageUtils;
import com.wuxinfeng.common.utils.R;



/**
 * 
 *
 * @author Wu Xinfeng
 * @email 390155409@qq.com
 * @date 2023-04-20 20:14:19
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("mm/materialvouchers")
@Validated
public class MaterialVouchersController {
    private final MaterialVouchersService materialVouchersService;

    /**
     *
     */
    @PostMapping("/move")
    public R move(@Validated({AddGroup.class}) @RequestBody @PeriodAllowed MaterialVouchersEntity materialVouchers){
        return R.ok().put("data",materialVouchersService.move(materialVouchers));
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
 //   @RequiresPermissions("mm:materialvouchers:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = materialVouchersService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{materialVoucherCode}")
  //  @RequiresPermissions("mm:materialvouchers:info")
    public R info(@PathVariable("materialVoucherCode") Long materialVoucherCode){
		MaterialVouchersEntity materialVouchers = materialVouchersService.getById(materialVoucherCode);

        return R.ok().put("materialVouchers", materialVouchers);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
  //  @RequiresPermissions("mm:materialvouchers:save")
    public R save(@RequestBody MaterialVouchersEntity materialVouchers){
		materialVouchersService.save(materialVouchers);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
  //  @RequiresPermissions("mm:materialvouchers:update")
    public R update(@RequestBody MaterialVouchersEntity materialVouchers){
		materialVouchersService.updateById(materialVouchers);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
  //  @RequiresPermissions("mm:materialvouchers:delete")
    public R delete(@RequestBody Long[] materialVoucherCodes){
		materialVouchersService.removeByIds(Arrays.asList(materialVoucherCodes));

        return R.ok();
    }

}
