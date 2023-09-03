package com.wuxinfeng.doublev.mm.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuxinfeng.common.utils.PageUtils;
import com.wuxinfeng.common.utils.Query;

import com.wuxinfeng.doublev.mm.dao.CurrencyRateDao;
import com.wuxinfeng.doublev.mm.entity.CurrencyRateEntity;
import com.wuxinfeng.doublev.mm.service.CurrencyRateService;


@Service("currencyRateService")
public class CurrencyRateServiceImpl extends ServiceImpl<CurrencyRateDao, CurrencyRateEntity> implements CurrencyRateService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CurrencyRateEntity> page = this.page(
                new Query<CurrencyRateEntity>().getPage(params),
                new QueryWrapper<CurrencyRateEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public Float getCurrencyRate(String numerator, String denominator) {
        float numeratorCurrency = baseMapper.getRateByCurrency(numerator);
        float denominatorCurrency = baseMapper.getRateByCurrency(denominator);
        return numeratorCurrency/denominatorCurrency;
    }

    @Override
    public boolean currencyExist(String currency) {
        return baseMapper.selectCount(new QueryWrapper<CurrencyRateEntity>().eq("currency",currency))>0;
    }

}