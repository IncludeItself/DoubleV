package com.wuxinfeng.doublev.mm.dao;

import com.wuxinfeng.doublev.mm.entity.AccountsConfigEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * 
 * @author Wu Xinfeng
 * @email 390155409@qq.com
 * @date 2023-08-19 11:35:34
 */
@Mapper
public interface AccountsConfigDao extends BaseMapper<AccountsConfigEntity> {

    AccountsConfigEntity getByEntity(AccountsConfigEntity accountsConfigEntity);
}
