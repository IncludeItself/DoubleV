package com.wuxinfeng.doublev.mm.dao;

import com.wuxinfeng.doublev.mm.entity.MovementsEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 
 * 
 * @author Wu Xinfeng
 * @email 390155409@qq.com
 * @date 2023-04-20 20:14:19
 */
@Mapper
public interface MovementsDao extends BaseMapper<MovementsEntity> {

    List<MovementsEntity> selectByVoucherCode(Long materialVoucherCode);
}
