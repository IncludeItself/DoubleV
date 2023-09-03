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
 * @date 2023-04-06 13:32:46
 */
@Data
@TableName("materials_basic")
public class MaterialsBasicEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String materialCode;
	/**
	 * 
	 */
	private String materialDescription;
	/**
	 * 
	 */
	private String basicUnit;
	/**
	 * 
	 */
	private String materialGroup;
	/**
	 *
	 */
	private String materialType;

}
