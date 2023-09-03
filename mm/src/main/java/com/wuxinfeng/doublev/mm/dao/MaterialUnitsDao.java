package com.wuxinfeng.doublev.mm.dao;

import com.wuxinfeng.doublev.mm.entity.MaterialUnitsEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * 
 * @author Wu Xinfeng
 * @email 390155409@qq.com
 * @date 2023-04-20 21:40:59
 */
@Mapper
public interface MaterialUnitsDao extends BaseMapper<MaterialUnitsEntity> {

    float getUnitRate(@Param("materialCode") String materialCode, @Param("basicUnit") String basicUnit);

    Float getRateBasicUnitTo(@Param("materialCode") String materialCode, @Param("orderUnit") String orderUnit);
}
