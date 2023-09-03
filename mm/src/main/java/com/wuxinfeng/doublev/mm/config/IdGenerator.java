package com.wuxinfeng.doublev.mm.config;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.wuxinfeng.doublev.mm.constant.RedisConstant;
import com.wuxinfeng.doublev.mm.entity.MaterialVouchersEntity;
import com.wuxinfeng.doublev.mm.entity.PurchaseOrdersEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * @Description: TODO
 * @Author: Wu Xinfeng
 * @CreateTime: 2023-04-07  12:34
 */
@RequiredArgsConstructor
@Component
public class IdGenerator implements IdentifierGenerator {

    private final RedisTemplate redisTemplate;
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Number nextId(Object entity) {
        System.out.println("进入到了Generator:"+entity.getClass().getName());
        if(entity instanceof PurchaseOrdersEntity)  return getNewPurchaseOrder();
        if (entity instanceof MaterialVouchersEntity) return getNewMaterialVoucherCode();
        else return null;
    }

    private long getNewMaterialVoucherCode() {
        ValueOperations<String,Long> ops = redisTemplate.opsForValue();
        Long latestCode = ops.get(RedisConstant.LATEST_MATERIAL_VOUCHER_CODE);
        if (latestCode==null) {
            String sql="select max(material_voucher_code) from material_vouchers";
            latestCode = jdbcTemplate.queryForObject(sql, long.class);
            if (latestCode==null) {
                latestCode=4000000000l;
            }
        }
        long newCode = latestCode + 1;
        ops.set(RedisConstant.LATEST_MATERIAL_VOUCHER_CODE,newCode);
        return newCode;
    }

    private long getNewPurchaseOrder() {
        ValueOperations<String,Long> ops = redisTemplate.opsForValue();
        Long latestOrder = ops.get(RedisConstant.LATEST_PURCHASE_ORDER);
        if (latestOrder==null) {
            String sql="select max(purchase_order) from purchase_orders";
            latestOrder = jdbcTemplate.queryForObject(sql, long.class);
            if (latestOrder==null) {
                latestOrder=4000000000l;
            }
        }
        long newPurchaseOrder = latestOrder + 1;
        ops.set(RedisConstant.LATEST_PURCHASE_ORDER,newPurchaseOrder);
        return newPurchaseOrder;
    }


}
