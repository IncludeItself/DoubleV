package com.wuxinfeng.doublev.fi.dao;

import com.wuxinfeng.doublev.fi.entity.EntriesEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 
 * 
 * @author Wu Xinfeng
 * @email 390155409@qq.com
 * @date 2023-03-20 17:47:26
 */
@Mapper
public interface EntriesDao extends BaseMapper<EntriesEntity> {

    public List<EntriesEntity> selectByVoucherNumber(long voucherNumber);

    void addWittenVoucher(Long voucherNumber, long book);

    boolean deleteByCode(long code);
}
