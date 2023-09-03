package com.wuxinfeng.doublev.fi.config;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.wuxinfeng.doublev.fi.constant.RedisConstant;
import com.wuxinfeng.doublev.fi.entity.EntriesEntity;
import com.wuxinfeng.doublev.fi.entity.VouchersEntity;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@RequiredArgsConstructor
@ConfigurationProperties(prefix = "fi")
@Component
@Data
public class VoucherIDGenerator implements IdentifierGenerator {

    private HashMap<String, Long> voucher;

    private final RedisTemplate redisTemplate;


    @Override
    public Number nextId(Object voucherEntity) {
        ValueOperations<String, Long> operator = redisTemplate.opsForValue();
        VouchersEntity e = (VouchersEntity) voucherEntity;
        String voucherType = e.getVoucherType();
        long lastId = operator.get(RedisConstant.VOUCHER_TYPE_PREFIX + voucherType)==null?0L:operator.get(RedisConstant.VOUCHER_TYPE_PREFIX + voucherType);
        if (lastId <= 0) {
            System.out.println("lastId <= 0,lastID："+lastId);
            System.out.println("voucherType："+voucher.get(voucherType));
            System.out.println("43*1000000000: "+43*1000000000);
            lastId = voucher.get(voucherType) * 1000000000 + 1;
            System.out.println("lastId with type："+lastId);
        }

        long newId = lastId + 1;
        System.out.println("newId："+newId);
        operator.set(RedisConstant.VOUCHER_TYPE_PREFIX + voucherType, newId);

        return newId;
    }

    public void test() {
        System.out.println(voucher);
    }


}
