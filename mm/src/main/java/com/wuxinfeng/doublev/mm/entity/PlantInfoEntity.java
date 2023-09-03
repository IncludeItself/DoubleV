package com.wuxinfeng.doublev.mm.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 
 * 
 * @author Wu Xinfeng
 * @email 390155409@qq.com
 * @date 2023-06-06 07:24:35
 */
@Data
@TableName("plant_info")
public class PlantInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String plantCode;
	/**
	 * 
	 */
	private String plantDescription;
	/**
	 * 
	 */
	private String companyCode;
	/**
	 * 
	 */
	private String companyDescription;
	/**
	 *
	 */
	private String currency;
	/**
	 * @description:
	 * @author: Wu Xinfeng
	 * @date: 2023/8/22 15:24
	 * @email: 390155409@qq.com
	 **/
	private String chartAccounts;
	/**
	 * 
	 */
	private Date currentPeriod;
	/**
	 * 
	 */
	private boolean lastPeriodAllowed;

}
