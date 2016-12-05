package com.zrsf.manage.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import com.zrsf.forclient.vo.SwjgTxl;
import com.zrsf.forclient.vo.Swry;


public class SystemPushDao {
	
	private SqlSessionTemplate sessionTemplate;

	public SqlSessionTemplate getSessionTemplate() {
		return sessionTemplate;
	}

	public void setSessionTemplate(SqlSessionTemplate sessionTemplate) {
		this.sessionTemplate = sessionTemplate;
	}
	
	public String insertMessage(Map<String, Object> message) {
		sessionTemplate.insert("com.zrsf.map.sx.insertMessage", message);
		return String.valueOf(message.get("messageId"));
	}

	public void insertSxxx(Map<String, Object> fromTo) {
		sessionTemplate.insert("com.zrsf.map.sx.insertSxxx", fromTo);
	}
	public List<Swry> selectDeviceId(List<String> list) {
		return sessionTemplate.selectList("com.zrsf.map.txl.selectDeviceId",
				list);
	}

	

}
