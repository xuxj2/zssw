package com.zrsf.forclient.vo;


public class ResponseObject {
	public static final String successCode="0000";
	public static final String failCode="9999";
	public static String loginCode="1111";
	
	private String code;
	private String message;
	private Object entity;
	
	public ResponseObject() {
		super();		
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getEntity() {
		return entity;
	}
	public void setEntity(Object entity) {
		this.entity = entity;
	}
	
	
	
	
	
	
	
	
	
	
	
}
