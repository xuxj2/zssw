package com.zrsf.manage.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import com.zrsf.manage.vo.TaxStatisticRow;

public class TaxStatisticDao {
	private SqlSessionTemplate sessionTemplate;	
	public SqlSessionTemplate getSessionTemplate() {
		return sessionTemplate;
	}
	public void setSessionTemplate(SqlSessionTemplate sessionTemplate) {
		this.sessionTemplate = sessionTemplate;
	}
	
	public void insertTaxStatisticRow(String sql,TaxStatisticRow row){
		sessionTemplate.insert(sql,row);
	}
	public void deleteTaxStatistic(String sql, String ssq) {
		sessionTemplate.delete(sql,ssq);		
	}
	
	public List<TaxStatisticRow> selectReport(String sql, String ssq) {		
		return sessionTemplate.selectList(sql, ssq);
	}
	public List<String> selectReportList(String sql) {		
		return sessionTemplate.selectList(sql);
	}
	public int updateIncome( Map<String, Object> map) {
		String sql="com.zrsf.tax.updateIncome";
		return sessionTemplate.update(sql,map);		
	}
	public int updateIncomeTotal(String ssq) {
		String sql="com.zrsf.tax.updateIncomeTotal";
		return sessionTemplate.update(sql,ssq);	
	}
}
