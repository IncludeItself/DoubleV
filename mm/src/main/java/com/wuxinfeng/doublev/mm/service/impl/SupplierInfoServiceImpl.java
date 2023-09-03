package com.wuxinfeng.doublev.mm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuxinfeng.common.utils.PageUtils;
import com.wuxinfeng.common.utils.Query;
import com.wuxinfeng.doublev.mm.constant.RedisConstant;
import com.wuxinfeng.doublev.mm.dao.SupplierInfoDao;
import com.wuxinfeng.doublev.mm.entity.SupplierInfoEntity;
import com.wuxinfeng.doublev.mm.service.SupplierInfoService;
import com.wuxinfeng.doublev.mm.vo.SupplierNameVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@Service("supplierInfoService")
@RequiredArgsConstructor
public class SupplierInfoServiceImpl extends ServiceImpl<SupplierInfoDao, SupplierInfoEntity> implements SupplierInfoService {

    private final RedisTemplate redisTemplate;

    @Value("${mm.redis.timeout.supplier-list}")
    private int timeoutSupplierList;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SupplierInfoEntity> page = this.page(
                new Query<SupplierInfoEntity>().getPage(params),
                new QueryWrapper<SupplierInfoEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public SupplierInfoEntity getBySupplierId(String supplierId) {
        return getById(supplierId);
    }

    @Override
    public SupplierNameVo getNameById(String supplier) {
        return baseMapper.getNameById(supplier);
    }

    @Override
    public boolean supplierExist(String plant, String supplier) {
        return getSupplierList(plant).contains(supplier);
    }

    private List<String> getSupplierList(String plant) {
        ValueOperations<String, List<String>> ops = redisTemplate.opsForValue();
        List<String> suppliers = ops.get(RedisConstant.SUPPLIERS + "_" + plant);
        if (suppliers == null || suppliers.size() == 0) {
            suppliers= baseMapper.selectSupplierByPlant(plant);
            ops.set(RedisConstant.SUPPLIERS + "_" + plant, suppliers, timeoutSupplierList, TimeUnit.SECONDS);
        }
        return suppliers;
    }

}