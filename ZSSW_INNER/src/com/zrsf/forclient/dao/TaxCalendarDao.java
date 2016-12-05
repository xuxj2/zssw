package com.zrsf.forclient.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import com.zrsf.forclient.vo.TaxCalendarVo;
import com.zrsf.forclient.vo.TaxcalendarDetailVo;

public class TaxCalendarDao {
	private SqlSessionTemplate sessionTemplate;

	public SqlSessionTemplate getSessionTemplate() {
		return sessionTemplate;
	}
	public void setSessionTemplate(SqlSessionTemplate sessionTemplate) {
		this.sessionTemplate = sessionTemplate;
	}
	public List<TaxCalendarVo> selectCalendarList(String year) {
		System.out.println(year.length());
		return	sessionTemplate.selectList("com.zrsf.map.newsColumn.selectTaxCalendarVo", year);
		
	}
	public List<TaxcalendarDetailVo> selectCalendarDetail(String id) {		
		return sessionTemplate.selectList("com.zrsf.map.newsColumn.selectTaxCalendarDetailVo", id);
	}
	
}
