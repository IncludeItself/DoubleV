package com.wuxinfeng.doublev.mm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import com.wuxinfeng.common.enums.ScopeFieldEnum;
import com.wuxinfeng.common.validator.group.AddGroup;
import com.wuxinfeng.doublev.mm.validator.InDataScope;
import com.wuxinfeng.doublev.mm.validator.NotZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * 
 * 
 * @author Wu Xinfeng
 * @email 390155409@qq.com
 * @date 2023-04-07 12:13:17
 */
@Data
@AllArgsConstructor
@TableName("goods_items")
public class GoodsItemsEntity implements Serializable {
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
	@NotBlank(groups = {AddGroup.class})
	@InDataScope(groups = {AddGroup.class},field = ScopeFieldEnum.MATERIAL_CODES)
	private String materialCode;
	/**
	 * 
	 */
	@NotBlank(groups = {AddGroup.class})
	@InDataScope(groups = {AddGroup.class},field = ScopeFieldEnum.CURRENCIES)
	private String currency;
	/**
	 * 
	 */
	@NotBlank(groups = {AddGroup.class})
	private String orderUnit;
	/**
	 *
	 */
	@NotNull(groups = {AddGroup.class})
	@NotZero(groups={AddGroup.class})
	@TableField(exist = false)
	private Float orderQuan;
	/**
	 *
	 */
	@Null(groups = {AddGroup.class})
	private Float quantityBasicUnit;
	/**
	 * 
	 */
	@NotNull(groups = {AddGroup.class})
	@NotZero(groups={AddGroup.class})
	private Float estimatedAmount;
	/**
	 * 
	 */
	@Null(groups = {AddGroup.class})
	private Float receivedQuantity;
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
