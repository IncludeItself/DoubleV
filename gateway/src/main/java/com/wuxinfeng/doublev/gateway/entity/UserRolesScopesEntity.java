package com.wuxinfeng.doublev.gateway.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.Data;

/**
 * 
 * 
 * @author Wu Xinfeng
 * @email 390155409@qq.com
 * @date 2023-05-28 08:39:34
 */
@Data
@TableName("user_roles_scopes")
public class UserRolesScopesEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String username;
	/**
	 * 
	 */
	private String roles;
	/**
	 * 
	 */
	private String plantScopes;

}
