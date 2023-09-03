package com.wuxinfeng.doublev.mm.dao;

import com.wuxinfeng.doublev.mm.entity.MaterialsAccountingEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * 
 * @author Wu Xinfeng
 * @email 390155409@qq.com
 * @date 2023-08-22 13:45:00
 */
@Mapper
public interface MaterialsAccountingDao extends BaseMapper<MaterialsAccountingEntity> {

    MaterialsAccountingEntity getByMaterialCode(@Param("plant") String plant, @Param("materialCode") String materialCode);
}
