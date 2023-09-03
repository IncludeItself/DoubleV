package com.wuxinfeng.doublev.mm.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.Data;

/**
 * 
 * 
 * @author Wu Xinfeng
 * @email 390155409@qq.com
 * @date 2023-08-25 14:45:14
 */
@Data
@TableName("supplier_info")
public class SupplierInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer supplier;
	/**
	 * 
	 */
	private String supplierDescription;
	/**
	 * 
	 */
	private String type;

}
