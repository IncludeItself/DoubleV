package com.wuxinfeng.doublev.mm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import com.wuxinfeng.common.validator.group.AddGroup;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;

/**
 * 
 * 
 * @author Wu Xinfeng
 * @email 390155409@qq.com
 * @date 2023-04-07 12:13:17
 */
@Data
@AllArgsConstructor
@TableName("delivery_items")
public class DeliveryItemsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Null(groups = {AddGroup.class})
	@TableId(type = IdType.AUTO)
	private Integer id;
	/**
	 * 
	 */
	@Null(groups = {AddGroup.class})
	private Long purchaseOrder;
	/**
	 *
	 */
	@Null(groups = {AddGroup.class})
	private Integer itemNo;
	/**
	 * 
	 */
	@Null(groups = {AddGroup.class})
	private String estimatedItem;
	/**
	 *
	 */
	@Null(groups = {AddGroup.class})
	private String deliverySupplier;
	/**
	 * 
	 */
	@Null(groups = {AddGroup.class})
	private String currency;
	/**
	 * 
	 */
	@NotNull(groups = {AddGroup.class})
	private Float estimatedAmount;
	/**
	 * 
	 */
	@Null(groups = {AddGroup.class})
	private Float receivedAmount;
	/**
	 *
	 */
	@Null(groups = {AddGroup.class})
	private Float confirmedQuantity;
	/**
	 *
	 */
	@Null(groups = {AddGroup.class})
	private Float confirmedAmount;

}
