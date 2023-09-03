package com.wuxinfeng.doublev.fi.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuxinfeng.common.utils.PageUtils;
import com.wuxinfeng.common.utils.Query;

import com.wuxinfeng.doublev.fi.dao.AuxiliaryAccountsDao;
import com.wuxinfeng.doublev.fi.entity.AuxiliaryAccountsEntity;
import com.wuxinfeng.doublev.fi.service.AuxiliaryAccountsService;


@Service("auxiliaryAccountsService")
public class AuxiliaryAccountsServiceImpl extends ServiceImpl<AuxiliaryAccountsDao, AuxiliaryAccountsEntity> implements AuxiliaryAccountsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AuxiliaryAccountsEntity> page = this.page(
                new Query<AuxiliaryAccountsEntity>().getPage(params),
                new QueryWrapper<AuxiliaryAccountsEntity>()
        );

        return new PageUtils(page);
    }

}