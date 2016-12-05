package com.zrsf.manage.dao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import com.zrsf.manage.vo.SysUser;

public class UserDao {
	private SqlSessionTemplate sessionTemplate;	
	public SqlSessionTemplate getSessionTemplate() {
		return sessionTemplate;
	}
	public void setSessionTemplate(SqlSessionTemplate sessionTemplate) {
		this.sessionTemplate = sessionTemplate;
	}
	public SysUser selectSysUser(Map<String, String> map) {
		return  sessionTemplate.selectOne("", map);
		
	}

}
