package com.wuxinfeng.doublev.mm.service.impl;

import com.wuxinfeng.doublev.mm.entity.MovementsEntity;
import com.wuxinfeng.doublev.mm.entity.ValueString;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuxinfeng.common.utils.PageUtils;
import com.wuxinfeng.common.utils.Query;

import com.wuxinfeng.doublev.mm.dao.MovementsConfigDao;
import com.wuxinfeng.doublev.mm.entity.MovementsConfigEntity;
import com.wuxinfeng.doublev.mm.service.MovementsConfigService;


@Service("movementsConfigService")
@RequiredArgsConstructor
public class MovementsConfigServiceImpl extends ServiceImpl<MovementsConfigDao, MovementsConfigEntity> implements MovementsConfigService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MovementsConfigEntity> page = this.page(
                new Query<MovementsConfigEntity>().getPage(params),
                new QueryWrapper<MovementsConfigEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<ValueString> getValueStrings(MovementsConfigEntity movementsConfigEntity) {
        return baseMapper.getValueStrings(movementsConfigEntity);
    }

    @Override
    public void test(MovementsEntity movementsEntity) {
        System.out.println("This is test from MovementsConfigService");
    }

}