package com.wuxinfeng.doublev.gateway.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wuxinfeng.common.utils.PageUtils;
import com.wuxinfeng.doublev.gateway.entity.UserRolesScopesEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author Wu Xinfeng
 * @email 390155409@qq.com
 * @date 2023-05-28 08:39:34
 */
public interface UserRolesScopesService extends IService<UserRolesScopesEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<String> getPlantScopesByUsername(String username);
}

