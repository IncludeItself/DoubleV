package com.wuxinfeng.doublev.fi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wuxinfeng.common.utils.PageUtils;
import com.wuxinfeng.doublev.fi.entity.EntriesEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author Wu Xinfeng
 * @email 390155409@qq.com
 * @date 2023-03-20 17:47:26
 */
public interface EntriesService extends IService<EntriesEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<EntriesEntity> selectByVoucherNumber(long voucherNumber);

    void addWittenVoucher(Long voucherNumber, long book);

    boolean deleteByCode(long code);
}

