package com.wuxinfeng.doublev.mm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wuxinfeng.common.utils.PageUtils;
import com.wuxinfeng.doublev.mm.entity.MaterialsAccountingEntity;

import java.util.Map;

/**
 * 
 *
 * @author Wu Xinfeng
 * @email 390155409@qq.com
 * @date 2023-08-22 13:45:00
 */
public interface MaterialsAccountingService extends IService<MaterialsAccountingEntity> {

    PageUtils queryPage(Map<String, Object> params);

    MaterialsAccountingEntity getByMaterialCode(String plant, String materialCode);

    void updateStorage(String plant, String materialCode, Float quantity, float amount);
}

