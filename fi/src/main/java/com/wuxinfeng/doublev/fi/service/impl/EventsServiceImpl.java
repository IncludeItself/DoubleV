package com.wuxinfeng.doublev.fi.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuxinfeng.common.utils.PageUtils;
import com.wuxinfeng.common.utils.Query;

import com.wuxinfeng.doublev.fi.dao.EventsDao;
import com.wuxinfeng.doublev.fi.entity.EventsEntity;
import com.wuxinfeng.doublev.fi.service.EventsService;


@Service("eventsService")
public class EventsServiceImpl extends ServiceImpl<EventsDao, EventsEntity> implements EventsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<EventsEntity> page = this.page(
                new Query<EventsEntity>().getPage(params),
                new QueryWrapper<EventsEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public EventsEntity getByEventDescription(String event) {
        return baseMapper.getByEventDescription(event);
    }

}