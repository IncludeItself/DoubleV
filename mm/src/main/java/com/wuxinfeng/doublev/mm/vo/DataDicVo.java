package com.wuxinfeng.doublev.mm.vo;

import com.wuxinfeng.doublev.mm.entity.DataDicEntity;
import lombok.Data;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

/**
 * @Description: TODO
 * @Author: Wu Xinfeng
 * @CreateTime: 2023-09-03  14:02
 */
@Data
public class DataDicVo{

    @Valid
    List<DataDicEntity> dataDicList;
}
