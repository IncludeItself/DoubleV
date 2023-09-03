package com.wuxinfeng.doublev.mm.dao;

import com.wuxinfeng.doublev.mm.entity.MovementsConfigEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wuxinfeng.doublev.mm.entity.ValueString;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * 
 * @author Wu Xinfeng
 * @email 390155409@qq.com
 * @date 2023-08-06 16:09:29
 */
@Mapper
public interface MovementsConfigDao extends BaseMapper<MovementsConfigEntity> {

    List<ValueString> getValueStrings(MovementsConfigEntity movementsConfigEntity);
}
