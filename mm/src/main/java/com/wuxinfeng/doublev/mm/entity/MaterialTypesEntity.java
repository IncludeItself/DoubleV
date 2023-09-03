package com.wuxinfeng.doublev.mm.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author Wu Xinfeng
 * @email 390155409@qq.com
 * @date 2023-08-12 12:34:46
 */
@Data
@TableName("material_types")
public class MaterialTypesEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String materialType;
	/**
	 * 
	 */
	private String materialTypeDescription;
	/**
	 * 
	 */
	private boolean qtyUpdating;
	/**
	 * 
	 */
	private boolean valueUpdating;

}
