package com.wuxinfeng.doublev.fi.controller;

import com.wuxinfeng.doublev.fi.config.VoucherIDGenerator;
import com.wuxinfeng.doublev.fi.vo.TestVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequiredArgsConstructor
@RequestMapping("fi/test")
public class textCon {

    @Autowired
    private VoucherIDGenerator voucherIDGenerator;

    @PostMapping("/t")
    public boolean test(@RequestBody TestVo testVo){
//        voucherIDGenerator.test();
        System.out.println(testVo);
        return true;
    }
}
