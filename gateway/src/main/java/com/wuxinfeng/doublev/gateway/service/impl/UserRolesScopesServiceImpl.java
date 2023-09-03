package com.wuxinfeng.doublev.gateway.service.impl;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuxinfeng.common.utils.PageUtils;
import com.wuxinfeng.common.utils.Query;

import com.wuxinfeng.doublev.gateway.dao.UserRolesScopesDao;
import com.wuxinfeng.doublev.gateway.entity.UserRolesScopesEntity;
import com.wuxinfeng.doublev.gateway.service.UserRolesScopesService;


@Service("userRolesScopesService")
public class UserRolesScopesServiceImpl extends ServiceImpl<UserRolesScopesDao, UserRolesScopesEntity> implements UserRolesScopesService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UserRolesScopesEntity> page = this.page(
                new Query<UserRolesScopesEntity>().getPage(params),
                new QueryWrapper<UserRolesScopesEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<String> getPlantScopesByUsername(String username) {
        String[] scopesString = getById(username).getPlantScopes().split(";");
        List<String> scopes = new ArrayList<String>();
        Arrays.stream(scopesString).map(scopes::add).collect(Collectors.toList());
        return scopes;
    }

}