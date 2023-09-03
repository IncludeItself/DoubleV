package com.wuxinfeng.doublev.mm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wuxinfeng.common.utils.PageUtils;
import com.wuxinfeng.doublev.mm.entity.SupplierInfoEntity;
import com.wuxinfeng.doublev.mm.vo.SupplierNameVo;

import java.util.Map;

/**
 * 
 *
 * @author Wu Xinfeng
 * @email 390155409@qq.com
 * @date 2023-08-25 14:45:14
 */
public interface SupplierInfoService extends IService<SupplierInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    SupplierInfoEntity getBySupplierId(String supplierId);

    SupplierNameVo getNameById(String supplier);

    boolean supplierExist(String plant,String supplier);
}

