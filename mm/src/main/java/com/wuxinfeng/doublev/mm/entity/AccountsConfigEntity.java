package com.wuxinfeng.doublev.mm.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 
 * 
 * @author Wu Xinfeng
 * @email 390155409@qq.com
 * @date 2023-08-19 11:35:34
 */
@Data
@AllArgsConstructor
@TableName("accounts_config")
public class AccountsConfigEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 *
	 */
	private String chartAccounts;
	/**
	 * 
	 */
	private String transactionKey;
	/**
	 * 
	 */
	private String accountModification;
	/**
	 * 
	 */
	private String valuationClass;
	/**
	 * 
	 */
	private String supplierType;
	/**
	 * 
	 */
	private Long debitAccount;
	/**
	 * 
	 */
	private Long creditAccount;

}
