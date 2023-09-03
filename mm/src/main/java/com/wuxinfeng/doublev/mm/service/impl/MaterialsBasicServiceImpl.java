package com.wuxinfeng.doublev.mm.service.impl;


import com.wuxinfeng.doublev.mm.service.MaterialTypesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuxinfeng.common.utils.PageUtils;
import com.wuxinfeng.common.utils.Query;

import com.wuxinfeng.doublev.mm.dao.MaterialsBasicDao;
import com.wuxinfeng.doublev.mm.entity.MaterialsBasicEntity;
import com.wuxinfeng.doublev.mm.service.MaterialsBasicService;


@Service("materialsBasicService")
@RequiredArgsConstructor
public class MaterialsBasicServiceImpl extends ServiceImpl<MaterialsBasicDao, MaterialsBasicEntity> implements MaterialsBasicService {

    private final MaterialTypesService materialTypesService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MaterialsBasicEntity> page = this.page(
                new Query<MaterialsBasicEntity>().getPage(params),
                new QueryWrapper<MaterialsBasicEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public String getDescriptionByCode(String materialCode) {
        String descriptionByCode = baseMapper.getDescriptionByCode(materialCode);
        if(descriptionByCode==null) return "(null)";
        return descriptionByCode;
    }

    @Override
    public boolean getQtyUpdatingByMaterialCode(String materialCode) {
        return materialTypesService.getById(getById(materialCode).getMaterialType()).isQtyUpdating();
    }

    @Override
    public boolean getValueUpdatingByMaterialCode(String materialCode) {
        return materialTypesService.getById(getById(materialCode).getMaterialType()).isValueUpdating();
    }

    @Override
    public MaterialsBasicEntity getByMaterialCode(String materialCode) {
        return getById(materialCode);
    }

    @Override
    public boolean materialCodeExist(String materialCode) {
        return baseMapper.selectCount(new QueryWrapper<MaterialsBasicEntity>().eq("material_code",materialCode))>0;
    }

}