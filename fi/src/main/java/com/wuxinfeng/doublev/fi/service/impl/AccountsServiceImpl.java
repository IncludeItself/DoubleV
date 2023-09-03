package com.wuxinfeng.doublev.fi.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuxinfeng.common.utils.PageUtils;
import com.wuxinfeng.common.utils.Query;

import com.wuxinfeng.doublev.fi.dao.AccountsDao;
import com.wuxinfeng.doublev.fi.entity.AccountsEntity;
import com.wuxinfeng.doublev.fi.service.AccountsService;


@Service("accountsService")
public class AccountsServiceImpl extends ServiceImpl<AccountsDao, AccountsEntity> implements AccountsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AccountsEntity> page = this.page(
                new Query<AccountsEntity>().getPage(params),
                new QueryWrapper<AccountsEntity>()
        );

        return new PageUtils(page);
    }

}