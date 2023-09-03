package com.wuxinfeng.doublev.fi.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuxinfeng.common.utils.PageUtils;
import com.wuxinfeng.common.utils.Query;

import com.wuxinfeng.doublev.fi.dao.ReasonsDao;
import com.wuxinfeng.doublev.fi.entity.ReasonsEntity;
import com.wuxinfeng.doublev.fi.service.ReasonsService;


@Service("reasonsService")
public class ReasonsServiceImpl extends ServiceImpl<ReasonsDao, ReasonsEntity> implements ReasonsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ReasonsEntity> page = this.page(
                new Query<ReasonsEntity>().getPage(params),
                new QueryWrapper<ReasonsEntity>()
        );

        return new PageUtils(page);
    }

}