package com.wuxinfeng.doublev.fi.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import lombok.Data;

/**
 * 
 * 
 * @author Wu Xinfeng
 * @email 390155409@qq.com
 * @date 2023-04-14 17:39:15
 */
@Data
@TableName("events")
public class EventsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String eventDescription;
	/**
	 * 
	 */
	private String voucherType;
	/**
	 * 
	 */
	private Long accountDebit;
	/**
	 *
	 */
	private Long accountCredit;
	/**
	 *
	 */
	private String postKeyDebit;
	/**
	 *
	 */
	private String postKeyCredit;

}
