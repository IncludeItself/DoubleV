package com.wuxinfeng.doublev.fi.controller;

import java.util.Arrays;
import java.util.Map;

import com.wuxinfeng.common.to.FiVoucherReturnTo;
import com.wuxinfeng.common.to.FiVoucherTo;
import com.wuxinfeng.common.validator.group.AddGroup;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RedissonClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.wuxinfeng.doublev.fi.entity.VouchersEntity;
import com.wuxinfeng.doublev.fi.service.VouchersService;
import com.wuxinfeng.common.utils.PageUtils;
import com.wuxinfeng.common.utils.R;


/**
 * 
 *
 * @author Wu Xinfeng
 * @email 390155409@qq.com
 * @date 2023-03-20 17:47:25
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("fi/vouchers")
public class VouchersController {
    private final VouchersService vouchersService;

    private final RedissonClient redissonClient;

    /**
     * @description:供其他微服务远程调用
     * @author: Wu Xinfeng
     * @date: 2023/4/14 17:18
     * @email: 390155409@qq.com
     **/
    @RequestMapping("/passbook")
    public FiVoucherReturnTo saveVoucher(@Validated({AddGroup.class}) @RequestBody FiVoucherTo voucher){
//        FiVoucherReturnTo book = vouchersService.book(voucher);
//        if (book==null) {
//            return R.error("失败了的凭证");
//        }
//        R<FiVoucherReturnTo> r=R.ok();
//        r.setData(book);
//        System.out.println("Controller这边的R："+r+"，R里面的Data："+r.getData());
//        return r;
        return vouchersService.book(voucher);
    }

    /**
     * @description:创建一个凭证
     * @author: Wu Xinfeng
     * @date: 2023/3/20 23:20
     * @email: 390155409@qq.com
     **/
    @PostMapping("/book")
    //   @RequiresPermissions("fi:vouchers:list")
    public R book(@Validated({AddGroup.class}) @RequestBody VouchersEntity voucher, @RequestHeader Map<String, String> headers){
        return R.ok().put("data", vouchersService.book(voucher,headers));
    }

    @PostMapping("/write-off/{vouchernumber}")
    public R writeOff(@PathVariable("vouchernumber") Integer voucherNumber, @RequestHeader Map<String, String> headers) throws Exception{
         return R.ok().put("data", vouchersService.writeOff(voucherNumber, headers));
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
 //   @RequiresPermissions("fi:vouchers:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = vouchersService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{voucherNumber}")
  //  @RequiresPermissions("fi:vouchers:info")
    public R info(@PathVariable("voucherNumber") Integer voucherNumber){
		VouchersEntity vouchers = vouchersService.getById(voucherNumber);

        return R.ok().put("vouchers", vouchers);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
  //  @RequiresPermissions("fi:vouchers:save")
    public R save(@RequestBody VouchersEntity vouchers){
		vouchersService.save(vouchers);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
  //  @RequiresPermissions("fi:vouchers:update")
    public R update(@RequestBody VouchersEntity vouchers){
		vouchersService.updateById(vouchers);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
  //  @RequiresPermissions("fi:vouchers:delete")
    public R delete(@RequestBody Integer[] voucherNumbers){
		vouchersService.removeByIds(Arrays.asList(voucherNumbers));

        return R.ok();
    }

}
