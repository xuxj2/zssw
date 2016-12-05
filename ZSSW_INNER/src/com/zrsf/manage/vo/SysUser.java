package com.zrsf.manage.vo;

import java.io.Serializable;

public class SysUser implements Serializable {
	private static final long serialVersionUID = 1L;
	private String jobNumber;		//工号
	private String code;			//税务人员代码
	private String name;			//姓名
	private String password;		//密码
	private Integer limit;			//后台管理权限
	
	
	
	
	public SysUser() {
		super();		
	}
	public String getJobNumber() {
		return jobNumber;
	}
	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	

}
