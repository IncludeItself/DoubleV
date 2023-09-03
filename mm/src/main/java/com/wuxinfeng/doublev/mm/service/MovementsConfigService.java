package com.wuxinfeng.doublev.mm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wuxinfeng.common.utils.PageUtils;
import com.wuxinfeng.doublev.mm.entity.MovementsConfigEntity;
import com.wuxinfeng.doublev.mm.entity.MovementsEntity;
import com.wuxinfeng.doublev.mm.entity.ValueString;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author Wu Xinfeng
 * @email 390155409@qq.com
 * @date 2023-08-06 16:09:29
 */
public interface MovementsConfigService extends IService<MovementsConfigEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<ValueString> getValueStrings(MovementsConfigEntity movementsConfigEntity);

    void test(MovementsEntity movementsEntity);
}

