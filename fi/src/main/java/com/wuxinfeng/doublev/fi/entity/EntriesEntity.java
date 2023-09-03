package com.wuxinfeng.doublev.fi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import com.wuxinfeng.common.validator.group.AddGroup;
import com.wuxinfeng.common.validator.group.UpdateGroup;
import com.wuxinfeng.doublev.fi.validator.CurrencyOptional;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

/**
 * 
 * 
 * @author Wu Xinfeng
 * @email 390155409@qq.com
 * @date 2023-03-20 17:47:26
 */
@Data
@AllArgsConstructor
@TableName("entries")
public class EntriesEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@Null(groups = {AddGroup.class, UpdateGroup.class})
	@TableId(type = IdType.AUTO)
	private Integer id;
	/**
	 * 
	 */
	private Long voucherNumber;
	/**
	 * 
	 */
	private Integer itemNumber;
	/**
	 * 
	 */
	@NotBlank(groups = {AddGroup.class})
	@CurrencyOptional(groups = {AddGroup.class})
	private String currencyBusiness;
	/**
	 * 
	 */
	private Date exchangeDate;
	/**
	 * 
	 */
	private String postKey;
	/**
	 * 
	 */
	private Long account;
	/**
	 * 
	 */
	private Float amountBusiness;
	/**
	 *
	 */

	private String currencyLocal;
	/**
	 *
	 */

	private Float amountLocal;
	/**
	 * 
	 */
	private String costCenter;
	/**
	 * 
	 */
	private String businessArea;
	/**
	 * 
	 */
	private String assignment;
	/**
	 * 
	 */
	private String text;
	/**
	 * 
	 */
	private String reasonCode;
	/**
	 * 
	 */
	private String payingRequest;
	/**
	 * 
	 */
	private String specialIndicator;
	/**
	 * 
	 */
	private String taxCode;
	/**
	 * 
	 */
	private String calculateTax;
	/**
	 * 
	 */
	private String tradingMate;
	/**
	 * 
	 */
	private Date dueOn;
	/**
	 * 
	 */
	private String profitCenter;
	/**
	 * 
	 */
	private String itemCompanyCode;
	/**
	 * 
	 */
	private String materialCode;
	/**
	 * 
	 */
	private Float materialQuantity;
	/**
	 * 
	 */
	private String materialUnit;
	/**
	 *
	 */
	private Long clearVoucher;

}
