package com.wuxinfeng.doublev.fi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.wuxinfeng.common.validator.group.AddGroup;
import com.wuxinfeng.common.validator.group.UpdateGroup;
import lombok.AllArgsConstructor;
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
 * @date 2023-03-20 17:47:25
 */
@Data
@TableName("vouchers")
public class VouchersEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@NotBlank(groups = {UpdateGroup.class})
	@Null(groups = {AddGroup.class})
	@TableId(type = IdType.ASSIGN_ID)
	private Long voucherNumber;
	/**
	 * 
	 */
	@NotBlank(groups = {AddGroup.class})
	private String companyCode;
	/**
	 * 
	 */
	@NotBlank(groups = {AddGroup.class})
	private String voucherType;
	/**
	 * 
	 */
	@NotNull(groups = {AddGroup.class})
	private Date documentDate;
	/**
	 * 
	 */
	@NotNull(groups = {AddGroup.class})
	private Date postingDate;
	/**
	 * 
	 */
	@NotBlank(groups = {AddGroup.class})
	private String reference;
	/**
	 * 
	 */
	@NotBlank(groups = {AddGroup.class})
	private String docHeaderText;
	/**
	 * 
	 */
	@Null(groups = {AddGroup.class, UpdateGroup.class})
	private String creator;
	/**
	 * 
	 */
	@Null(groups = {AddGroup.class, UpdateGroup.class})
	private Date timeCreated;
	/**
	 * 
	 */
	@Null(groups = {AddGroup.class, UpdateGroup.class})
	private Date lastChange;
	/**
	 *
	 */
	private Long writtenOffVoucher;

	@TableField(exist = false)
	@Valid
	private List<EntriesEntity> entries;

}
