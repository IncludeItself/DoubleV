package com.wuxinfeng.doublev.mm.dao;

import com.wuxinfeng.doublev.mm.entity.CurrencyRateEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * 
 * @author Wu Xinfeng
 * @email 390155409@qq.com
 * @date 2023-08-24 14:11:37
 */
@Mapper
public interface CurrencyRateDao extends BaseMapper<CurrencyRateEntity> {

    float getRateByCurrency(String numerator);
}
