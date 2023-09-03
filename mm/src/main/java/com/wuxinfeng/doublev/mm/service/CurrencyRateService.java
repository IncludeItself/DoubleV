package com.wuxinfeng.doublev.mm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wuxinfeng.common.utils.PageUtils;
import com.wuxinfeng.doublev.mm.entity.CurrencyRateEntity;

import java.util.Map;

/**
 * 
 *
 * @author Wu Xinfeng
 * @email 390155409@qq.com
 * @date 2023-08-24 14:11:37
 */
public interface CurrencyRateService extends IService<CurrencyRateEntity> {

    PageUtils queryPage(Map<String, Object> params);

    Float getCurrencyRate(String currencyLocal, String currencyBusiness);

    boolean currencyExist(String currency);
}

