package com.zrsf.manage.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import com.zrsf.manage.vo.SwryVO;
import com.zrsf.manage.vo.rzjg;
import com.zrsf.manage.vo.zw;

public class RywhDao {
	private SqlSessionTemplate sessionTemplate;
	public List<zw> selectZw()
	{
		return sessionTemplate.selectList("com.zrsf.manage.manager.selectZw");
	}
	public List<rzjg> selectRzjg(String jgDm)
	{
		return  sessionTemplate.selectList("com.zrsf.manage.manager.selectRzjg", jgDm);
	}
	public SwryVO selectSwry(String swryDm)
	{
		return sessionTemplate.selectOne("com.zrsf.manage.manager.selectSwry", swryDm);
	}
	public void updateSwry(Map<String,String> map)
	{
		sessionTemplate.update("com.zrsf.manage.manager.updateSwry", map);
	}
	public void updateSwrys(Map<String,String> map)
	{
		sessionTemplate.update("com.zrsf.manage.manager.updateSwrys", map);
	}
	public SqlSessionTemplate getSessionTemplate() {
		return sessionTemplate;
	}

	public void setSessionTemplate(SqlSessionTemplate sessionTemplate) {
		this.sessionTemplate = sessionTemplate;
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
	
	
	
	
	

}
