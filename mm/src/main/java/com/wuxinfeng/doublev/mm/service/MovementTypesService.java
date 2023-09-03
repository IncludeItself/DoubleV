package com.wuxinfeng.doublev.mm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wuxinfeng.common.utils.PageUtils;
import com.wuxinfeng.doublev.mm.entity.MovementTypesEntity;

import java.util.Map;

/**
 * 
 *
 * @author Wu Xinfeng
 * @email 390155409@qq.com
 * @date 2023-04-06 13:32:46
 */
public interface MovementTypesService extends IService<MovementTypesEntity> {

    PageUtils queryPage(Map<String, Object> params);

    MovementTypesEntity getByReverseType(String movementType);

    MovementTypesEntity getByType(String movementType);

    Boolean isReverseType(String movementType);
}

