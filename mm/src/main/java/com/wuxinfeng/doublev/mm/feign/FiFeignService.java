package com.wuxinfeng.doublev.mm.feign;

import com.wuxinfeng.common.to.FiVoucherReturnTo;
import com.wuxinfeng.common.to.FiVoucherTo;
import com.wuxinfeng.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;


/**
 * @Description: TODO
 * @Author: Wu Xinfeng
 * @CreateTime: 2023-04-14  19:01
 */
@FeignClient("fi")
public interface FiFeignService {

    @PostMapping("/fi/vouchers/passbook")
    FiVoucherReturnTo saveVoucher(FiVoucherTo fiVoucherTo);
}
