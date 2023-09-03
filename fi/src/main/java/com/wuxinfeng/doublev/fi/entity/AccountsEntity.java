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
@TableName("accounts")
public class AccountsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String accountCode;
	/**
	 * 
	 */
	private String accountDescription;
	/**
	 * 
	 */
	private boolean isReconciliationAccount;
	/**
	 * 
	 */
	private Integer auxiliaryAccountsId;

}
