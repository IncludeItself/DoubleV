package com.wuxinfeng.doublev.fi.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuxinfeng.common.utils.PageUtils;
import com.wuxinfeng.common.utils.Query;

import com.wuxinfeng.doublev.fi.dao.CompanyInfoDao;
import com.wuxinfeng.doublev.fi.entity.CompanyInfoEntity;
import com.wuxinfeng.doublev.fi.service.CompanyInfoService;


@Service("companyInfoService")
public class CompanyInfoServiceImpl extends ServiceImpl<CompanyInfoDao, CompanyInfoEntity> implements CompanyInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CompanyInfoEntity> page = this.page(
                new Query<CompanyInfoEntity>().getPage(params),
                new QueryWrapper<CompanyInfoEntity>()
        );

        return new PageUtils(page);
    }

}