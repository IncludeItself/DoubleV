package com.wuxinfeng.doublev.gateway.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.sql.Date;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import static com.baomidou.mybatisplus.annotation.IdType.AUTO;

/**
 * 
 * 
 * @author Wu Xinfeng
 * @email sunlightcs@gmail.com
 * @date 2023-02-18 13:23:43
 */
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@TableName("user_info")
public class UserInfoEntity extends BaseEntity implements UserDetails {
	private static final long serialVersionUID = 1L;

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.stream(role.split(";")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	}

	@TableField(exist = false)
	private Collection<? extends GrantedAuthority> authorities;
	/**
	 * 
	 */
	@TableId(type = AUTO)
	private Integer id;
	/**
	 * 
	 */
	private String username;
	/**
	 *
	 */
	private String mobilePhone;
	/**
	 * 
	 */
	private Date birthday;
	/**
	 *
	 */
	private String password;
	/**
	 * 
	 */
	private String role;
	/**
	 * 
	 */
	private String title;
	/**
	 * 
	 */
	private String email;
	/**
	 * 
	 */
	private Date timeLast;
	/**
	 * 
	 */
	private Date timeCreated;
	/**
	 * 
	 */
	private boolean accountNonExpired;
	/**
	 * 
	 */
	private boolean accountNonLocked;
	/**
	 * 
	 */
	private boolean credentialsNonExpired;
	/**
	 * 
	 */
	private boolean enabled;
}
