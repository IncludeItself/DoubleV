package com.wuxinfeng.doublev.mm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wuxinfeng.common.utils.PageUtils;
import com.wuxinfeng.doublev.mm.entity.MaterialsBasicEntity;

import java.util.Map;

/**
 * 
 *
 * @author Wu Xinfeng
 * @email 390155409@qq.com
 * @date 2023-04-06 13:32:46
 */
public interface MaterialsBasicService extends IService<MaterialsBasicEntity> {

    PageUtils queryPage(Map<String, Object> params);

    String getDescriptionByCode(String materialCode);

    boolean getQtyUpdatingByMaterialCode(String materialCode);

    boolean getValueUpdatingByMaterialCode(String materialCode);

    MaterialsBasicEntity getByMaterialCode(String materialCode);

    boolean materialCodeExist(String materialCode);
}

