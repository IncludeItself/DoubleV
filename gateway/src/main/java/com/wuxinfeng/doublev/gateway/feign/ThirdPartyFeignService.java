package com.wuxinfeng.doublev.gateway.feign;

import com.wuxinfeng.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description
 * @Author Wu Xinfeng
 * @Date 2023/3/3 17:27
 **/
@FeignClient("third-party")
public interface ThirdPartyFeignService {

    @GetMapping("/third-party/sms/sendcode")
    public R sendCode(@RequestParam("phoneNumber") String phoneNumber, @RequestParam("code") String code);
}
