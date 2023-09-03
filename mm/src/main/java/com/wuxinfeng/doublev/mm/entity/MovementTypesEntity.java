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
 * @date 2023-04-06 13:32:46
 */
@Data
@TableName("movement_types")
public class MovementTypesEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String movementType;
	/**
	 * 
	 */
	private String movementTypeDescription;
	/**
	 * 
	 */
	private Boolean isWarehousing;
	/**
	 *
	 */
	private String reverseMovement;
	/**
	 *
	 */
	private String reverseMovementDescription;
	/**
	 *
	 */
	private int move_Direction;

}
