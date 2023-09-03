package com.wuxinfeng.doublev.gateway.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wuxinfeng.doublev.gateway.entity.UserInfoEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * 
 * @author Wu Xinfeng
 * @email sunlightcs@gmail.com
 * @date 2023-02-18 13:23:43
 */
@Mapper
public interface UserInfoDao extends BaseMapper<UserInfoEntity> {
	
}
