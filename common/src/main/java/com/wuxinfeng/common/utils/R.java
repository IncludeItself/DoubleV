package com.wuxinfeng.common.utils;

import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据
 *
 */
public class R<T> extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;
//	private T data;
	public void setCode(Integer code){
		put("code",code);
	}
	public Integer getCode(){
		Object code = get("code");
		if(code instanceof Integer) return (Integer) code;
		return null;
	}
	public void setMsg(String msg){
		put("msg",msg);
	}
	public String getMsg(){
		Object msg = get("msg");
		if(msg instanceof String) return (String) msg;
		return null;
	}

	public T getData(){
//		return data;
		return (T)get("data");
	}

	public void setData(T data){
//		this.data=data;
		put("data",data);
	}

	public R() {
		setCode(0);
		setMsg("success");
	}
	
	public static R error() {
		return error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "未知异常，请联系管理员");
	}
	
	public static R error(String msg) {
		return error(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg);
	}
	
	public static R error(int code, String msg) {
		R r = new R();
		r.setCode(code);
		r.setMsg(msg);
		return r;
	}

	public static R ok(String msg) {
		R r = new R();
		r.setMsg(msg);
		return r;
	}
	
	public static R ok(Map<String, Object> map) {
		R r = new R();
		r.setCode(0);
		r.setMsg("success");
		r.putAll(map);
		return r;
	}
	
	public static R ok() {
		R r = new R();
		r.setCode(0);
		r.setMsg("success");
		return r;
	}

	public R put(String key, Object value) {
		super.put(key, value);
		return this;
	}
}
