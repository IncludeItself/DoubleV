package com.wuxinfeng.doublev.mm.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuxinfeng.common.utils.PageUtils;
import com.wuxinfeng.common.utils.Query;

import com.wuxinfeng.doublev.mm.dao.WarehouseSpotsDao;
import com.wuxinfeng.doublev.mm.entity.WarehouseSpotsEntity;
import com.wuxinfeng.doublev.mm.service.WarehouseSpotsService;


@Service("warehouseSpotsService")
public class WarehouseSpotsServiceImpl extends ServiceImpl<WarehouseSpotsDao, WarehouseSpotsEntity> implements WarehouseSpotsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<WarehouseSpotsEntity> page = this.page(
                new Query<WarehouseSpotsEntity>().getPage(params),
                new QueryWrapper<WarehouseSpotsEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public String getBusinessAreaByStockSpot(long stockSpot) {
        return baseMapper.getBusinessAreaByStockSpot(stockSpot);
    }

}