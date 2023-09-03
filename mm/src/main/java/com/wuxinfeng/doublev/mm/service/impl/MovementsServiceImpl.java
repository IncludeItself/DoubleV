package com.wuxinfeng.doublev.mm.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuxinfeng.common.utils.PageUtils;
import com.wuxinfeng.common.utils.Query;

import com.wuxinfeng.doublev.mm.dao.MovementsDao;
import com.wuxinfeng.doublev.mm.entity.MovementsEntity;
import com.wuxinfeng.doublev.mm.service.MovementsService;


@Service("movementsService")
public class MovementsServiceImpl extends ServiceImpl<MovementsDao, MovementsEntity> implements MovementsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MovementsEntity> page = this.page(
                new Query<MovementsEntity>().getPage(params),
                new QueryWrapper<MovementsEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<MovementsEntity> getByVoucherCode(Long materialVoucherCode) {
        return baseMapper.selectByVoucherCode(materialVoucherCode);
    }

}