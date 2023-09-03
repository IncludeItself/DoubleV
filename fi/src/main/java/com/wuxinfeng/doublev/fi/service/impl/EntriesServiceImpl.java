package com.wuxinfeng.doublev.fi.service.impl;

import com.wuxinfeng.common.validator.group.AddGroup;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuxinfeng.common.utils.PageUtils;
import com.wuxinfeng.common.utils.Query;

import com.wuxinfeng.doublev.fi.dao.EntriesDao;
import com.wuxinfeng.doublev.fi.entity.EntriesEntity;
import com.wuxinfeng.doublev.fi.service.EntriesService;
import org.springframework.validation.annotation.Validated;


@Service("entriesService")
public class EntriesServiceImpl extends ServiceImpl<EntriesDao, EntriesEntity> implements EntriesService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<EntriesEntity> page = this.page(
                new Query<EntriesEntity>().getPage(params),
                new QueryWrapper<EntriesEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<EntriesEntity> selectByVoucherNumber(long voucherNumber) {
        List<EntriesEntity> entriesEntities = baseMapper.selectByVoucherNumber(voucherNumber);
        return entriesEntities;
    }

    @Override
    public void addWittenVoucher(Long voucherNumber, long book) {
        baseMapper.addWittenVoucher(voucherNumber,book);
    }

    @Override
    public boolean deleteByCode(long code) {
        return baseMapper.deleteByCode(code);
    }

}