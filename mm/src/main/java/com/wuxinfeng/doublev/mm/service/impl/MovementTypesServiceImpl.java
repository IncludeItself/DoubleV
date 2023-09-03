package com.wuxinfeng.doublev.mm.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuxinfeng.common.utils.PageUtils;
import com.wuxinfeng.common.utils.Query;

import com.wuxinfeng.doublev.mm.dao.MovementTypesDao;
import com.wuxinfeng.doublev.mm.entity.MovementTypesEntity;
import com.wuxinfeng.doublev.mm.service.MovementTypesService;


@Service("movementTypesService")
public class MovementTypesServiceImpl extends ServiceImpl<MovementTypesDao, MovementTypesEntity> implements MovementTypesService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MovementTypesEntity> page = this.page(
                new Query<MovementTypesEntity>().getPage(params),
                new QueryWrapper<MovementTypesEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public MovementTypesEntity getByReverseType(String movementType) {
        return baseMapper.selectByReverseType(movementType);
    }

    @Override
    public MovementTypesEntity getByType(String movementType) {
        return baseMapper.selectByType(movementType);
    }

    @Override
    public Boolean isReverseType(String movementType) {
        if (baseMapper.selectByReverseType(movementType)==null) {
            if (baseMapper.selectByType(movementType)==null) {
                return null;
            }
            return false;
        }
        return true;
    }

}