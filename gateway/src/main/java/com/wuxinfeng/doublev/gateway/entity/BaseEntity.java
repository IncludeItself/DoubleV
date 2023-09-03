package com.wuxinfeng.doublev.gateway.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author Wu Xinfeng
 * @Date 2023/2/16 10:31
 **/
@Data
public abstract class BaseEntity implements Serializable {

    /**
     *
     */
    private Date timeLast;
    /**
     *
     */
    @JsonFormat(pattern = "yyyy-MM-dd  HH:mm:ss")
    private Date timeCreated;

    @TableField(exist = false)
    private Map<String,Object> params;

    public Map<String,Object> getParams(){
        if(params==null) params = new HashMap<>();
        return params;
    }


}
