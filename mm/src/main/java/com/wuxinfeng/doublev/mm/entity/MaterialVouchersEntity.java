package com.wuxinfeng.doublev.mm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;


import com.wuxinfeng.common.enums.DataDicEnum;
import com.wuxinfeng.common.validator.group.AddGroup;
import com.wuxinfeng.doublev.mm.validator.DataDictionary;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Past;

/**
 * 
 * 
 * @author Wu Xinfeng
 * @email 390155409@qq.com
 * @date 2023-04-20 20:14:19
 */
@Data
@TableName("material_vouchers")
public class MaterialVouchersEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(type = IdType.ASSIGN_ID)
	@Null(groups = {AddGroup.class})
	private Long materialVoucherCode;
	/**
	 * 
	 */
	@Null(groups = {AddGroup.class})
	private Long fiVoucherCode;
//	/**
//	 *
//	 */
//	@NotBlank(groups = {AddGroup.class})
//	@InDataScope(groups = {AddGroup.class},field = ScopeFieldEnum.PLANT)
//	private String plant;
	/**
	 * 
	 */
	@Null(groups = {AddGroup.class})
	private Date dateOperated;
	/**
	 * 
	 */
	@NotNull(groups = {AddGroup.class})
	@Past
	private Date documentDate;
	/**
	 * 
	 */
	@NotNull(groups = {AddGroup.class})
	private Date postDate;
	/**
	 * 
	 */
	@NotBlank(groups = {AddGroup.class})
	private String movementType;
	/**
	 * 
	 */
	@Null(groups = {AddGroup.class})
	private String movementTypeDescription;
	/**
	 * 
	 */
	@NotNull(groups = {AddGroup.class})
	private String reference;
	/**
	 *
	 */
	private String headerText;
	/**
	 * 
	 */
	private Long purchaseOrder;
	/**
	 * 
	 */
	private String saleOrder;
	/**
	 *
	 */
	@Null(groups = {AddGroup.class})
	private String creator;
	/**
	 *
	 */
	@TableField(exist = false)
	@Valid
	private List<MovementsEntity> moveItems;

}
