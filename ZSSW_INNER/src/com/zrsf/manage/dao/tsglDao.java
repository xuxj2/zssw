package com.zrsf.manage.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

public class tsglDao {
	private SqlSessionTemplate sessionTemplate;
	public List<String> selectRy(String dylm)
	{
		return sessionTemplate.selectList("com.zrsf.manage.manager.selectRy", dylm);
	}
	public void insertLmdy(Map<String,String> map)
	{
		sessionTemplate.insert("com.zrsf.manage.manager.insertLmdy", map);
	}
	public void deleteLmdy(Map<String,String> map)
	{
		sessionTemplate.delete("com.zrsf.manage.manager.deleteLmRy", map);
	}
	public List<String> idsToList(String ids) {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < ids.length() / 12; i++) {
			if (!ids.substring((i * 12), i * 12 + 11).substring(0, 4)
					.equals("23205"))
				list.add(ids.substring((i * 12), i * 12 + 11));
		}
		return list;
	}
	public SqlSessionTemplate getSessionTemplate() {
		return sessionTemplate;
	}
	public void setSessionTemplate(SqlSessionTemplate sessionTemplate) {
		this.sessionTemplate = sessionTemplate;
	}
}
