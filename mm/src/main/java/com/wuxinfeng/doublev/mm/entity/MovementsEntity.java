package com.wuxinfeng.doublev.mm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wuxinfeng.common.enums.ScopeFieldEnum;
import com.wuxinfeng.doublev.mm.validator.InDataScope;
import java.io.Serializable;

import com.wuxinfeng.common.validator.group.AddGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * 
 * 
 * @author Wu Xinfeng
 * @email 390155409@qq.com
 * @date 2023-04-20 20:14:19
 */
@Data
@TableName("movements")
public class MovementsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(type = IdType.AUTO)
	@Null(groups = {AddGroup.class})
	private Integer id;
	/**
	 *
	 */
	@NotBlank(groups = {AddGroup.class})
	@InDataScope(groups = {AddGroup.class},field = ScopeFieldEnum.PLANT)
	private String plant;
	/**
	 * 
	 */
	private Integer itemNo;
	/**
	 * 
	 */
	@Null(groups = {AddGroup.class})
	private Long materialVoucherCode;
	/**
	 *
	 */
	private String specialStock;
	/**
	 * 
	 */
	@NotNull(groups = {AddGroup.class})
	private Long stockPosition;
	/**
	 * 
	 */
	@NotBlank(groups = {AddGroup.class})
	private String materialCode;
	/**
	 * 
	 */
	@Null(groups = {AddGroup.class})
	private String materialDescription;
	/**
	 * 
	 */
	@Null(groups = {AddGroup.class})
	private Float quantity;
	/**
	 * 
	 */
	@Null(groups = {AddGroup.class})
	private String basicUnit;
	/**
	 * 
	 */
	@Null(groups = {AddGroup.class})
	private Float amount;
	/**
	 * 
	 */
	@NotNull(groups = {AddGroup.class})
	private String operateUnit;
	/**
	 * 
	 */
	@NotNull(groups = {AddGroup.class})
	private Float quantityOperated;
	/**
	 * 
	 */
	private String text;

}
