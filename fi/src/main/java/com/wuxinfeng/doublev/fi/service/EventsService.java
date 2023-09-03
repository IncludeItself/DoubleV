package com.wuxinfeng.doublev.fi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wuxinfeng.common.utils.PageUtils;
import com.wuxinfeng.doublev.fi.entity.EventsEntity;

import java.util.Map;

/**
 * 
 *
 * @author Wu Xinfeng
 * @email 390155409@qq.com
 * @date 2023-04-14 17:39:15
 */
public interface EventsService extends IService<EventsEntity> {

    PageUtils queryPage(Map<String, Object> params);

    EventsEntity getByEventDescription(String event);
}

