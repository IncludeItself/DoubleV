package com.wuxinfeng.doublev.fi.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wuxinfeng.doublev.fi.entity.EventsEntity;
import com.wuxinfeng.doublev.fi.service.EventsService;
import com.wuxinfeng.common.utils.PageUtils;
import com.wuxinfeng.common.utils.R;



/**
 * 
 *
 * @author Wu Xinfeng
 * @email 390155409@qq.com
 * @date 2023-04-14 17:39:15
 */
@RestController
@RequestMapping("fi/events")
public class EventsController {
    @Autowired
    private EventsService eventsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
 //   @RequiresPermissions("fi:events:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = eventsService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{eventDescription}")
  //  @RequiresPermissions("fi:events:info")
    public R info(@PathVariable("eventDescription") String eventDescription){
		EventsEntity events = eventsService.getById(eventDescription);

        return R.ok().put("events", events);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
  //  @RequiresPermissions("fi:events:save")
    public R save(@RequestBody EventsEntity events){
		eventsService.save(events);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
  //  @RequiresPermissions("fi:events:update")
    public R update(@RequestBody EventsEntity events){
		eventsService.updateById(events);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
  //  @RequiresPermissions("fi:events:delete")
    public R delete(@RequestBody String[] eventDescriptions){
		eventsService.removeByIds(Arrays.asList(eventDescriptions));

        return R.ok();
    }

}
