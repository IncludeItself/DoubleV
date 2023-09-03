package com.wuxinfeng.doublev.fi.dao;

import com.wuxinfeng.doublev.fi.entity.EventsEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * 
 * @author Wu Xinfeng
 * @email 390155409@qq.com
 * @date 2023-04-14 17:39:15
 */
@Mapper
public interface EventsDao extends BaseMapper<EventsEntity> {

    EventsEntity getByEventDescription(String event);
}
