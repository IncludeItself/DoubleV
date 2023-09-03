package com.wuxinfeng.doublev.mm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wuxinfeng.common.utils.PageUtils;
import com.wuxinfeng.doublev.mm.entity.GoodsItemsEntity;
import com.wuxinfeng.doublev.mm.entity.MaterialVouchersEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 
 *
 * @author Wu Xinfeng
 * @email 390155409@qq.com
 * @date 2023-04-20 20:14:19
 */
public interface MaterialVouchersService extends IService<MaterialVouchersEntity> {

    PageUtils queryPage(Map<String, Object> params);

    Long move(MaterialVouchersEntity materialVouchers);


    Long invoice(GoodsItemsEntity goodsItemsEntity);
}

