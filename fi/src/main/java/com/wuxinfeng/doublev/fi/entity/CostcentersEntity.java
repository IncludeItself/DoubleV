package com.wuxinfeng.doublev.fi.entity;

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
 * @date 2023-03-28 19:50:16
 */
@Data
@TableName("costcenters")
public class CostcentersEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String id;
	/**
	 * 
	 */
	private String description;
	/**
	 * 
	 */
	private String plant;
	/**
	 * 直接生产/间接生产/职能部门/销售部门
	 */
	private String type;
	/**
	 * userId
	 */
	private String leader;

}
