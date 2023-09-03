package com.wuxinfeng.doublev.fi.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuxinfeng.common.utils.PageUtils;
import com.wuxinfeng.common.utils.Query;

import com.wuxinfeng.doublev.fi.dao.PlantInfoDao;
import com.wuxinfeng.doublev.fi.entity.PlantInfoEntity;
import com.wuxinfeng.doublev.fi.service.PlantInfoService;


@Service("plantInfoService")
public class PlantInfoServiceImpl extends ServiceImpl<PlantInfoDao, PlantInfoEntity> implements PlantInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PlantInfoEntity> page = this.page(
                new Query<PlantInfoEntity>().getPage(params),
                new QueryWrapper<PlantInfoEntity>()
        );

        return new PageUtils(page);
    }

}