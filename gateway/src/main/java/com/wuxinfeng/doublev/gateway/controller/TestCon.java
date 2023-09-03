package com.wuxinfeng.doublev.gateway.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @BelongsProject: DoubleV
 * @BelongsPackage: com.wuxinfeng.doublev.gateway.controller
 * @Author: yanhongwei
 * @CreateTime: 2023-03-22  05:32
 * @Description: TODO
 * @Version: 1.0
 */
@RestController
@RequestMapping("test")
public class TestCon {

    @RequestMapping("/t")
    public String test(){
        return "tttt";
    }
}
