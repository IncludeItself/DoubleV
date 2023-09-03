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
 * @date 2023-04-13 13:47:15
 */
@Data
@TableName("warehouse_spots")
public class WarehouseSpotsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 
	 */
	private Integer areaCode;
	/**
	 *
	 */
	private String businessArea;
	/**
	 * 
	 */
	private Integer warehouseCode;
	/**
	 * 
	 */
	private String plantCode;

}
