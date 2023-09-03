package com.wuxinfeng.doublev.mm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wuxinfeng.common.enums.DataDicEnum;
import com.wuxinfeng.common.utils.PageUtils;
import com.wuxinfeng.doublev.mm.entity.DataDicEntity;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author Wu Xinfeng
 * @email 390155409@qq.com
 * @date 2023-09-03 12:23:09
 */
public interface DataDicService extends IService<DataDicEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveNewBatch(List<DataDicEntity> dataDicList);

    List<String> getOptionsByFiledName(DataDicEnum field);
}

