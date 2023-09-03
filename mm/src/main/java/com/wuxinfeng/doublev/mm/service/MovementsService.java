package com.wuxinfeng.doublev.mm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wuxinfeng.common.utils.PageUtils;
import com.wuxinfeng.doublev.mm.entity.MovementsEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author Wu Xinfeng
 * @email 390155409@qq.com
 * @date 2023-04-20 20:14:19
 */
public interface MovementsService extends IService<MovementsEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<MovementsEntity> getByVoucherCode(Long materialVoucherCode);
}

