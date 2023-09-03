package com.wuxinfeng.doublev.mm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import com.wuxinfeng.common.enums.DataDicEnum;
import com.wuxinfeng.common.validator.group.AddGroup;
import com.wuxinfeng.doublev.mm.validator.DataDictionary;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 
 * 
 * @author Wu Xinfeng
 * @email 390155409@qq.com
 * @date 2023-09-03 12:23:09
 */
@Data
@TableName("data_dic")
public class DataDicEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@NotNull(groups = {AddGroup.class})
	private String optionDescription;
	/**
	 * 
	 */
	@NotBlank(groups = {AddGroup.class})
	@DataDictionary(groups = {AddGroup.class},field = DataDicEnum.FIELD)
	private String fieldName;

}
