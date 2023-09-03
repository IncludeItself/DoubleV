package com.wuxinfeng.doublev.mm.service.impl;

import com.wuxinfeng.doublev.mm.constant.RedisConstant;
import com.wuxinfeng.doublev.mm.constant.RedissonConstant;
import com.wuxinfeng.doublev.mm.exception.LastPeriodAllowedException;
import com.wuxinfeng.doublev.mm.exception.PeriodOpenException;
import com.wuxinfeng.doublev.mm.exception.PlantNotExistException;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuxinfeng.common.utils.PageUtils;
import com.wuxinfeng.common.utils.Query;

import com.wuxinfeng.doublev.mm.dao.PlantInfoDao;
import com.wuxinfeng.doublev.mm.entity.PlantInfoEntity;
import com.wuxinfeng.doublev.mm.service.PlantInfoService;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service("plantInfoService")
public class PlantInfoServiceImpl extends ServiceImpl<PlantInfoDao, PlantInfoEntity> implements PlantInfoService {

    private final RedissonClient redissonClient;
    private final RedisTemplate redisTemplate;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PlantInfoEntity> page = this.page(
                new Query<PlantInfoEntity>().getPage(params),
                new QueryWrapper<PlantInfoEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional
    public Date activeNewPeriod(PlantInfoEntity plantInfo) {
        String companyCode = plantInfo.getCompanyCode();
        RReadWriteLock readWriteLock = redissonClient.getReadWriteLock(RedissonConstant.PLANT_INFO_LOCK+companyCode);
        RLock rLock = readWriteLock.writeLock();
        rLock.lock();

        PlantInfoEntity oldPlantEntity = getPlantInfoEntity(companyCode);
        boolean lastPeriodAllowed = oldPlantEntity.isLastPeriodAllowed();
        if (lastPeriodAllowed) {
            rLock.unlock();
            throw new LastPeriodAllowedException();
        }
        Date newPeriodDate = plantInfo.getCurrentPeriod();
        Period between = Period.between(oldPlantEntity.getCurrentPeriod().toLocalDate(), newPeriodDate.toLocalDate());
        if (between.getYears()*12+between.getMonths()!=1) {
            rLock.unlock();
            throw new PeriodOpenException();
        }
        baseMapper.updatePeriodDate(companyCode,newPeriodDate);
        oldPlantEntity.setCurrentPeriod(newPeriodDate);
        oldPlantEntity.setLastPeriodAllowed(true);
        ValueOperations<String,PlantInfoEntity> ops = redisTemplate.opsForValue();
        ops.set(RedisConstant.COMPANY_INFO + companyCode,oldPlantEntity);
        rLock.unlock();
        return newPeriodDate;
    }

    @Override
    public boolean allowLast(PlantInfoEntity plantInfo) {
        String companyCode = plantInfo.getCompanyCode();
        boolean b = plantInfo.isLastPeriodAllowed();
        PlantInfoEntity oldPlantEntity = getPlantInfoEntity(companyCode);
        RReadWriteLock readWriteLock = redissonClient.getReadWriteLock(RedissonConstant.PLANT_INFO_LOCK+companyCode);
        RLock rLock = readWriteLock.writeLock();
        rLock.lock();
        if (oldPlantEntity.isLastPeriodAllowed()==b) {
            rLock.unlock();
            return true;
        }

        baseMapper.updateLastPeriodAllowed(companyCode,b);
        oldPlantEntity.setLastPeriodAllowed(b);
        ValueOperations<String,PlantInfoEntity> ops = redisTemplate.opsForValue();
        ops.set(RedisConstant.COMPANY_INFO + companyCode,oldPlantEntity);
        rLock.unlock();
        return true;
    }

    @Override
    public boolean isPeriodAllowed(String plantCode, Date postingDate) {
        String companyCode = getCompanyCodeByPlantCode(plantCode);
        PlantInfoEntity currentPlantEntity = getPlantInfoEntity(companyCode);
        LocalDate postingLocalDate = postingDate.toLocalDate();
        LocalDate currentLocalDate = currentPlantEntity.getCurrentPeriod().toLocalDate();
        Period between = Period.between(postingLocalDate, currentLocalDate);
        int months = between.getYears() * 12 + between.getMonths();
        if ((months ==1 && currentPlantEntity.isLastPeriodAllowed()==true) || months ==0) return true;
        return false;
    }

    @Override
    public PlantInfoEntity getByPlantCode(String materialCode) {
        return getById(materialCode);
    }

    private PlantInfoEntity getPlantInfoEntity(String companyCode) {
        ValueOperations<String,PlantInfoEntity> ops = redisTemplate.opsForValue();
        PlantInfoEntity oldPlantEntity;
        if (ops.get(RedisConstant.COMPANY_INFO+ companyCode)!=null) {
            oldPlantEntity = ops.get(RedisConstant.COMPANY_INFO + companyCode);
        }else{
            oldPlantEntity = baseMapper.getByCompanyCode(companyCode).get(0);
        }
        return oldPlantEntity;
    }

    private String getCompanyCodeByPlantCode(String plant){
        ValueOperations<String,String> opsForCompanyCode = redisTemplate.opsForValue();
        String companyCode;
        if (opsForCompanyCode.get(RedisConstant.PLANT_COMPANY+plant)==null) {
            PlantInfoEntity plantEntity = getById(plant);
            if (plantEntity==null) {
                throw new PlantNotExistException();
            }
            companyCode= plantEntity.getCompanyCode();
            opsForCompanyCode.set(RedisConstant.PLANT_COMPANY+plant,companyCode);
        }
        return opsForCompanyCode.get(RedisConstant.PLANT_COMPANY+plant);
    }


}