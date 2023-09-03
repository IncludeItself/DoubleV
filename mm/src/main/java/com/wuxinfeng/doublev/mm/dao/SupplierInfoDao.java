package com.wuxinfeng.doublev.mm.dao;

import com.wuxinfeng.doublev.mm.entity.SupplierInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wuxinfeng.doublev.mm.vo.SupplierNameVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 
 * 
 * @author Wu Xinfeng
 * @email 390155409@qq.com
 * @date 2023-08-25 14:45:14
 */
@Mapper
public interface SupplierInfoDao extends BaseMapper<SupplierInfoEntity> {

    SupplierNameVo getNameById(String supplier);

    List<String> selectSupplierByPlant(String plant);
}
