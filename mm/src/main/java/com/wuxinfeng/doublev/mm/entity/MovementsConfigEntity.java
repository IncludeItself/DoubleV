package com.wuxinfeng.doublev.mm.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 
 * 
 * @author Wu Xinfeng
 * @email 390155409@qq.com
 * @date 2023-08-06 16:09:29
 */
@Data
@AllArgsConstructor
@TableName("movements_config")
public class MovementsConfigEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer movementConfigId;
	/**
	 * 
	 */
	private String movementType;
	/**
	 * 
	 */
	private String specialStock;
	/**
	 * 
	 */
	private boolean valueUpdating;
	/**
	 * 
	 */
	private boolean qtyUpdating;
	/**
	 * 
	 */
	private String movementIndicator;
	/**
	 * 
	 */
	private String consumptionPosting;
	/**
	 *
	 */
	private String transactionKey;
	/**
	 *
	 */
	private String accountModification;

}
