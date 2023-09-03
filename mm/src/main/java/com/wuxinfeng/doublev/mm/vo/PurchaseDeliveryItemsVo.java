package com.wuxinfeng.doublev.mm.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.List;

import com.wuxinfeng.common.enums.DataDicEnum;
import com.wuxinfeng.common.enums.ScopeFieldEnum;
import com.wuxinfeng.common.validator.group.AddGroup;
import com.wuxinfeng.doublev.mm.entity.DeliveryItemsEntity;
import com.wuxinfeng.doublev.mm.validator.DataDictionary;
import com.wuxinfeng.doublev.mm.validator.InDataScope;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * 
 * 
 * @author Wu Xinfeng
 * @email 390155409@qq.com
 * @date 2023-04-09 10:11:34
 */
@Data
//@TableName("purchase_delivery_items")
public class PurchaseDeliveryItemsVo implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Null(groups = {AddGroup.class})
	@TableId
	private Integer id;
	/**
	 * 
	 */
	@Null(groups = {AddGroup.class})
	private Long purchaseOrder;
	/**
	 * 
	 */
	@NotBlank(groups = {AddGroup.class})
	@DataDictionary(groups = {AddGroup.class},field = DataDicEnum.ESTIMATED_ITEMS)
	private String estimatedItem;
	/**
	 * 
	 */
	@NotNull(groups = {AddGroup.class})
	private String supplier;
	/**
	 *
	 */
	@NotBlank(groups = {AddGroup.class})
	@InDataScope(groups = {AddGroup.class},field = ScopeFieldEnum.CURRENCIES)
	private String currency;
	/**
	 *
	 */
	@Valid
	@TableField(exist = false)
	private List<DeliveryItemsEntity> deliveryItemsEntityList;

}
