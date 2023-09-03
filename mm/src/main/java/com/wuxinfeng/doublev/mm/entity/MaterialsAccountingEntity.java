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
 * @date 2023-08-22 13:45:00
 */
@Data
@TableName("materials_accounting")
public class MaterialsAccountingEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String materialCode;
	/**
	 * 
	 */
	private String plant;
	/**
	 * 
	 */
	private Float qtyAll;
	/**
	 * 
	 */
	private String valuationClass;
	/**
	 * 
	 */
	private Float qtySales;
	/**
	 * 
	 */
	private boolean ml;
	/**
	 * 
	 */
	private String priceDetermined;
	/**
	 * 
	 */
	private String priceControl;
	/**
	 * 
	 */
	private Float standardPrice;
	/**
	 * 
	 */
	private Integer priceDenominator;
	/**
	 * 
	 */
	private Float value;

}
