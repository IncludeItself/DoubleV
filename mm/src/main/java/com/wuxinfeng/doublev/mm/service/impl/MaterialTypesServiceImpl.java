package com.wuxinfeng.doublev.mm.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuxinfeng.common.utils.PageUtils;
import com.wuxinfeng.common.utils.Query;

import com.wuxinfeng.doublev.mm.dao.MaterialTypesDao;
import com.wuxinfeng.doublev.mm.entity.MaterialTypesEntity;
import com.wuxinfeng.doublev.mm.service.MaterialTypesService;


@Service("materialTypesService")
public class MaterialTypesServiceImpl extends ServiceImpl<MaterialTypesDao, MaterialTypesEntity> implements MaterialTypesService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MaterialTypesEntity> page = this.page(
                new Query<MaterialTypesEntity>().getPage(params),
                new QueryWrapper<MaterialTypesEntity>()
        );

        return new PageUtils(page);
    }

}