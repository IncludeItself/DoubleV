package com.wuxinfeng.doublev.mm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wuxinfeng.common.utils.PageUtils;
import com.wuxinfeng.doublev.mm.entity.PlantInfoEntity;

import java.sql.Date;
import java.util.Map;

/**
 * 
 *
 * @author Wu Xinfeng
 * @email 390155409@qq.com
 * @date 2023-06-06 07:24:35
 */
public interface PlantInfoService extends IService<PlantInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    Date activeNewPeriod(PlantInfoEntity plantInfo);

    boolean allowLast(PlantInfoEntity plantInfo);

    boolean isPeriodAllowed(String plantCode,Date postingDate);

    PlantInfoEntity getByPlantCode(String materialCode);
}

