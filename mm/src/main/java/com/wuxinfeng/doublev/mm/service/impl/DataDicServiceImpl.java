package com.wuxinfeng.doublev.mm.service.impl;

import com.wuxinfeng.common.enums.DataDicEnum;
import com.wuxinfeng.doublev.mm.constant.RedisConstant;
import com.wuxinfeng.doublev.mm.constant.RedissonConstant;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.enums.EnumUtils;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuxinfeng.common.utils.PageUtils;
import com.wuxinfeng.common.utils.Query;

import com.wuxinfeng.doublev.mm.dao.DataDicDao;
import com.wuxinfeng.doublev.mm.entity.DataDicEntity;
import com.wuxinfeng.doublev.mm.service.DataDicService;


@Service("dataDicService")
@RequiredArgsConstructor
public class DataDicServiceImpl extends ServiceImpl<DataDicDao, DataDicEntity> implements DataDicService {
    private final RedisTemplate redisTemplate;
    private final RedissonClient redissonClient;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<DataDicEntity> page = this.page(
                new Query<DataDicEntity>().getPage(params),
                new QueryWrapper<DataDicEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveNewBatch(List<DataDicEntity> dataDicList) {
        RReadWriteLock readWriteLock = redissonClient.getReadWriteLock(RedissonConstant.DATA_DIC_OPTIONS);
        RLock rLock = readWriteLock.writeLock();
        rLock.lock();
        ValueOperations<String,List<String>> ops = redisTemplate.opsForValue();
        for (DataDicEntity dataDicEntity : dataDicList) {
            List<String> list = ops.get(RedisConstant.DATA_DIC_OPTIONS + dataDicEntity.getFieldName());
            if (list!=null) {
                redisTemplate.delete(RedisConstant.DATA_DIC_OPTIONS + dataDicEntity.getFieldName());
            }
        }
        rLock.unlock();
        saveBatch(dataDicList);
    }

    @Override
    public List<String> getOptionsByFiledName(DataDicEnum field) {
        ValueOperations<String,List<String>> ops = redisTemplate.opsForValue();
        List<String> list = ops.get(RedisConstant.DATA_DIC_OPTIONS + field);
        if (list==null) {
            list= getOptionByField(field);
            ops.set(RedisConstant.DATA_DIC_OPTIONS + field,list);
        }
        return list;
    }

    private List<String> getOptionByField(DataDicEnum field) {
        if (field== DataDicEnum.FIELD) {
            System.out.println(EnumUtils.getEnumList(DataDicEnum.class));
            return EnumUtils.getEnumList(DataDicEnum.class);
        }
        return baseMapper.getOptionByField(field);
    }

}