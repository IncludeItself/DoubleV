package com.wuxinfeng.doublev.mm.dao;

import com.wuxinfeng.common.enums.DataDicEnum;
import com.wuxinfeng.doublev.mm.entity.DataDicEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 
 * 
 * @author Wu Xinfeng
 * @email 390155409@qq.com
 * @date 2023-09-03 12:23:09
 */
@Mapper
public interface DataDicDao extends BaseMapper<DataDicEntity> {

    List<String> getOptionByField(DataDicEnum field);

    List<String> getFieldNames();
}
