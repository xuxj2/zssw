package com.zrsf.forclient.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import com.zrsf.forclient.vo.TodoTask;

public class DbrwDao {
	private SqlSessionTemplate sessionTemplate;
	public SqlSessionTemplate getSessionTemplate() {
		return sessionTemplate;
	}
	public void setSessionTemplate(SqlSessionTemplate sessionTemplate) {
		this.sessionTemplate = sessionTemplate;
	}
	public List<TodoTask> selectDbrw(Map<String, Object> map) {
		List<TodoTask> list = null;
		list = sessionTemplate.selectList("com.zrsf.map.dbrw.selectDbrw",
				map);
		return list;
	}
}
