package com.wuxinfeng.doublev.mm.dao;

import com.wuxinfeng.doublev.mm.entity.PlantInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;
import java.util.List;

/**
 * 
 * 
 * @author Wu Xinfeng
 * @email 390155409@qq.com
 * @date 2023-06-06 07:24:35
 */
@Mapper
public interface PlantInfoDao extends BaseMapper<PlantInfoEntity> {

    List<PlantInfoEntity> getByCompanyCode(String companyCode);

    void updatePeriodDate(@Param("companyCode") String companyCode, @Param("newPeriod") Date newPeriod);

    void updateLastPeriodAllowed(@Param("companyCode") String companyCode, @Param("i") boolean i);
}
