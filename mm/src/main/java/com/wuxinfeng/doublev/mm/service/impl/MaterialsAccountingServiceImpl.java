package com.wuxinfeng.doublev.mm.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuxinfeng.common.utils.PageUtils;
import com.wuxinfeng.common.utils.Query;

import com.wuxinfeng.doublev.mm.dao.MaterialsAccountingDao;
import com.wuxinfeng.doublev.mm.entity.MaterialsAccountingEntity;
import com.wuxinfeng.doublev.mm.service.MaterialsAccountingService;


@Service("materialsAccountingService")
public class MaterialsAccountingServiceImpl extends ServiceImpl<MaterialsAccountingDao, MaterialsAccountingEntity> implements MaterialsAccountingService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MaterialsAccountingEntity> page = this.page(
                new Query<MaterialsAccountingEntity>().getPage(params),
                new QueryWrapper<MaterialsAccountingEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public MaterialsAccountingEntity getByMaterialCode(String plant, String materialCode) {
        return baseMapper.getByMaterialCode(plant,materialCode);
    }

    @Override
    public void updateStorage(String plant, String materialCode, Float quantity, float amount) {
        MaterialsAccountingEntity materialsAccountingEntity = baseMapper.getByMaterialCode(plant, materialCode);
        materialsAccountingEntity.setQtyAll(materialsAccountingEntity.getQtyAll()+quantity);
        materialsAccountingEntity.setValue(materialsAccountingEntity.getValue()+amount);
        updateById(materialsAccountingEntity);
    }

}