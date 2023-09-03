package com.wuxinfeng.doublev.mm.service.impl;

import com.wuxinfeng.doublev.mm.service.MaterialsBasicService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuxinfeng.common.utils.PageUtils;
import com.wuxinfeng.common.utils.Query;

import com.wuxinfeng.doublev.mm.dao.MaterialUnitsDao;
import com.wuxinfeng.doublev.mm.entity.MaterialUnitsEntity;
import com.wuxinfeng.doublev.mm.service.MaterialUnitsService;


@Service("materialUnitsService")
@RequiredArgsConstructor
public class MaterialUnitsServiceImpl extends ServiceImpl<MaterialUnitsDao, MaterialUnitsEntity> implements MaterialUnitsService {
    private final MaterialsBasicService materialsBasicService;
    private final RedisTemplate redisTemplate;


    @Value("${mm.redis.timeout.material-unit}")
    private int timeoutMaterialUnit;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MaterialUnitsEntity> page = this.page(
                new Query<MaterialUnitsEntity>().getPage(params),
                new QueryWrapper<MaterialUnitsEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public float getUnitRate(String materialCode, String numeratorUnit,String denominatorUnit) {
        System.out.println("numeratorUnit："+numeratorUnit);
        System.out.println("denominatorUnit："+denominatorUnit);
        if (numeratorUnit.equals(denominatorUnit)) {
            return 1f;
        }
        if (materialsBasicService.getById(materialCode).getBasicUnit()==denominatorUnit) {
            return baseMapper.getUnitRate(materialCode,numeratorUnit);
        }
        return baseMapper.getUnitRate(materialCode,numeratorUnit)/baseMapper.getUnitRate(materialCode,denominatorUnit);
    }

    @Override
    public Float getRateBasicUnitTo(String materialCode, String unit) {
        ValueOperations<String,Float> ops = redisTemplate.opsForValue();
        Float rateBasicUnitTo;
        Float rate = ops.get(materialCode + "_" + unit);
        if (rate ==null) {
            rateBasicUnitTo = baseMapper.getRateBasicUnitTo(materialCode, unit);
            ops.set(materialCode+"_"+unit,rateBasicUnitTo,timeoutMaterialUnit, TimeUnit.SECONDS);
            return rateBasicUnitTo;
        }
        return rate;
    }

}