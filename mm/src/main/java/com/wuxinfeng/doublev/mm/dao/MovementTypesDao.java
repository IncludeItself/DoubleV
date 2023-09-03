package com.wuxinfeng.doublev.mm.dao;

import com.wuxinfeng.doublev.mm.entity.MovementTypesEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * 
 * @author Wu Xinfeng
 * @email 390155409@qq.com
 * @date 2023-04-06 13:32:46
 */
@Mapper
public interface MovementTypesDao extends BaseMapper<MovementTypesEntity> {


    MovementTypesEntity selectByReverseType(String movementType);

    MovementTypesEntity selectByType(String movementType);
}
