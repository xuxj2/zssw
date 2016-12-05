package com.zrsf.manage.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import com.zrsf.manage.vo.News;

public class NewsDao {
	private SqlSessionTemplate sessionTemplate;	
	public SqlSessionTemplate getSessionTemplate() {
		return sessionTemplate;
	}
	public void setSessionTemplate(SqlSessionTemplate sessionTemplate) {
		this.sessionTemplate = sessionTemplate;
	}
	public List<News> selectNewsList(String sql,Map<String, Object> map) {
		return  sessionTemplate.selectList(sql, map);		
	}
	public int selectNewsAmount(String sql, String columnId) {		
		 return sessionTemplate.selectOne(sql, columnId);
	}
	public void deleteOne(String sql, String id) {
		sessionTemplate.delete(sql,id);		
	}
	
	public void upstaOne(String sql, News news) {
		sessionTemplate.update(sql, news);		
	}
	public void modify(String sql,News news) {
		sessionTemplate.update(sql, news);			
	}
	
	public News selectNewsDetail(String sql, News news) {
		return  sessionTemplate.selectOne(sql,news);
	}

}
