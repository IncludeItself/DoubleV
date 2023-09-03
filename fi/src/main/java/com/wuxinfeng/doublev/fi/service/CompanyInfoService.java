package com.wuxinfeng.doublev.fi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wuxinfeng.common.utils.PageUtils;
import com.wuxinfeng.doublev.fi.entity.CompanyInfoEntity;

import java.util.Map;

/**
 * 
 *
 * @author Wu Xinfeng
 * @email 390155409@qq.com
 * @date 2023-04-14 18:28:19
 */
public interface CompanyInfoService extends IService<CompanyInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

