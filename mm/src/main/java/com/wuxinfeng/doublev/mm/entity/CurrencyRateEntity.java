package com.wuxinfeng.doublev.mm.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.Year;

import lombok.Data;

/**
 * 
 * 
 * @author Wu Xinfeng
 * @email 390155409@qq.com
 * @date 2023-08-24 14:11:37
 */
@Data
@TableName("currency_rate")
public class CurrencyRateEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 
	 */
	private String currency;
	/**
	 * 
	 */
	private Year year;
	/**
	 * 
	 */
	private boolean month;
	/**
	 * 
	 */
	private Float rateToHardCurrency;

}
