package com.wuxinfeng.doublev.mm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wuxinfeng.common.utils.PageUtils;
import com.wuxinfeng.doublev.mm.entity.WarehouseSpotsEntity;

import java.util.Map;

/**
 * 
 *
 * @author Wu Xinfeng
 * @email 390155409@qq.com
 * @date 2023-04-13 13:47:15
 */
public interface WarehouseSpotsService extends IService<WarehouseSpotsEntity> {

    PageUtils queryPage(Map<String, Object> params);

    String getBusinessAreaByStockSpot(long stockSpot);
}

