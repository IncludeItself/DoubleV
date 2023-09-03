package com.wuxinfeng.doublev.mm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wuxinfeng.common.utils.PageUtils;
import com.wuxinfeng.doublev.mm.entity.MaterialUnitsEntity;

import java.util.Map;

/**
 * 
 *
 * @author Wu Xinfeng
 * @email 390155409@qq.com
 * @date 2023-04-20 21:40:59
 */
public interface MaterialUnitsService extends IService<MaterialUnitsEntity> {

    PageUtils queryPage(Map<String, Object> params);

    float getUnitRate(String materialCode, String numeratorUnit,String denominatorUnit);

    Float getRateBasicUnitTo(String materialCode, String orderUnit);
}

