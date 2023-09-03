package com.wuxinfeng.doublev.fi.service.impl;

import com.wuxinfeng.doublev.fi.constant.LockConstant;
import com.wuxinfeng.doublev.fi.constant.RedisConstant;
import lombok.RequiredArgsConstructor;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuxinfeng.common.utils.PageUtils;
import com.wuxinfeng.common.utils.Query;

import com.wuxinfeng.doublev.fi.dao.CurrencyRateDao;
import com.wuxinfeng.doublev.fi.entity.CurrencyRateEntity;
import com.wuxinfeng.doublev.fi.service.CurrencyRateService;


@Service("currencyRateService")
@RequiredArgsConstructor
public class CurrencyRateServiceImpl extends ServiceImpl<CurrencyRateDao, CurrencyRateEntity> implements CurrencyRateService {

    private final RedissonClient redissonClient;

    private final RedisTemplate redisTemplate;


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CurrencyRateEntity> page = this.page(
                new Query<CurrencyRateEntity>().getPage(params),
                new QueryWrapper<CurrencyRateEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public HashSet<String> getOptions() {
        RReadWriteLock readWriteLock = redissonClient.getReadWriteLock(LockConstant.CURRENCY_OPTIONS);
        RLock rLock = readWriteLock.readLock();
        ValueOperations<String, HashSet<String>> ops = redisTemplate.opsForValue();
        rLock.lock();
        Object strings = ops.get(RedisConstant.CURRENCY_OPTIONS);
        if(strings==null) {
            HashSet<String> hashSet = baseMapper.selectCurrencies();
            ops.set(RedisConstant.CURRENCY_OPTIONS, hashSet);
            rLock.unlock();
            return hashSet;
        }
        rLock.unlock();
        return (HashSet<String>) strings;
    }

    @Override
    public float getCurrencyRate(String numerator, String denominator) {
        float numeratorCurrency = baseMapper.getRateByCurrency(numerator);
        float denominatorCurrency = baseMapper.getRateByCurrency(denominator);
        return numeratorCurrency/denominatorCurrency;
    }

}