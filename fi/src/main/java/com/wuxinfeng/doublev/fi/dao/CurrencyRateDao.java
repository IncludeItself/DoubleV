package com.wuxinfeng.doublev.fi.dao;

import com.wuxinfeng.doublev.fi.entity.CurrencyRateEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * 
 * 
 * @author Wu Xinfeng
 * @email 390155409@qq.com
 * @date 2023-03-28 19:50:16
 */
@Mapper
public interface CurrencyRateDao extends BaseMapper<CurrencyRateEntity> {

    public HashSet<String> selectCurrencies();

    float getRateByCurrency(String currency);
}
