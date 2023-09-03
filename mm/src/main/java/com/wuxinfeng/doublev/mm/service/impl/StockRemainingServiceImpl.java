package com.wuxinfeng.doublev.mm.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuxinfeng.common.utils.PageUtils;
import com.wuxinfeng.common.utils.Query;

import com.wuxinfeng.doublev.mm.dao.StockRemainingDao;
import com.wuxinfeng.doublev.mm.entity.StockRemainingEntity;
import com.wuxinfeng.doublev.mm.service.StockRemainingService;


@Service("stockRemainingService")
public class StockRemainingServiceImpl extends ServiceImpl<StockRemainingDao, StockRemainingEntity> implements StockRemainingService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<StockRemainingEntity> page = this.page(
                new Query<StockRemainingEntity>().getPage(params),
                new QueryWrapper<StockRemainingEntity>()
        );

        return new PageUtils(page);
    }

}