package com.wuxinfeng.doublev.fi.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuxinfeng.common.utils.PageUtils;
import com.wuxinfeng.common.utils.Query;

import com.wuxinfeng.doublev.fi.dao.CostcentersDao;
import com.wuxinfeng.doublev.fi.entity.CostcentersEntity;
import com.wuxinfeng.doublev.fi.service.CostcentersService;


@Service("costcentersService")
public class CostcentersServiceImpl extends ServiceImpl<CostcentersDao, CostcentersEntity> implements CostcentersService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CostcentersEntity> page = this.page(
                new Query<CostcentersEntity>().getPage(params),
                new QueryWrapper<CostcentersEntity>()
        );

        return new PageUtils(page);
    }

}