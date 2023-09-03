package com.wuxinfeng.doublev.mm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import com.wuxinfeng.common.enums.DataDicEnum;
import com.wuxinfeng.common.enums.ScopeFieldEnum;
import com.wuxinfeng.common.validator.group.AddGroup;
import com.wuxinfeng.doublev.mm.validator.DataDictionary;
import com.wuxinfeng.doublev.mm.validator.InDataScope;
import com.wuxinfeng.doublev.mm.vo.PurchaseDeliveryItemsVo;
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
 * @date 2023-04-07 12:13:17
 */
@Data
@TableName("purchase_orders")
public class PurchaseOrdersEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Null(groups = {AddGroup.class})
	@TableId(type = IdType.ASSIGN_ID)
	private Long purchaseOrder;
	/**
	 *
	 */
	@DataDictionary(groups = {AddGroup.class},field = DataDicEnum.CONSUMING_POSTING)
	private String consumingPosting;
	/**
	 * 
	 */
	private String packageList;
	/**
	 *
	 */
	@NotBlank(groups = {AddGroup.class})
	@InDataScope(groups = {AddGroup.class},field = ScopeFieldEnum.PLANT)
	private String plantCode;
	/**
	 * 
	 */
	@NotBlank(groups = {AddGroup.class})
	private String supplier;
	/**
	 * 
	 */
	@Null(groups = {AddGroup.class})
	private Date dateCreated;
	/**
	 *
	 */
	@NotNull(groups = {AddGroup.class})
	private Date documentDate;
	/**
	 * 
	 */
	@Null(groups = {AddGroup.class})
	private String creator;
	/**
	 *
	 */
	@Valid
	@TableField(exist = false)
	private List<GoodsItemsEntity> goodsItemsEntityList;
	/**
	 *
	 */
	@Valid
	@TableField(exist = false)
	private List<PurchaseDeliveryItemsVo> purchaseDeliveryItemsVoList;
}
