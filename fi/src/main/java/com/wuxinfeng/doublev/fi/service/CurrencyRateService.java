package com.wuxinfeng.doublev.fi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wuxinfeng.common.utils.PageUtils;
import com.wuxinfeng.doublev.fi.entity.CurrencyRateEntity;
import java.util.HashSet;
import java.util.Map;

/**
 * 
 *
 * @author Wu Xinfeng
 * @email 390155409@qq.com
 * @date 2023-03-28 19:50:16
 */
public interface CurrencyRateService extends IService<CurrencyRateEntity> {

    PageUtils queryPage(Map<String, Object> params);

    HashSet<String> getOptions();

    float getCurrencyRate(String localCurrency, String currencyBusiness);
}

