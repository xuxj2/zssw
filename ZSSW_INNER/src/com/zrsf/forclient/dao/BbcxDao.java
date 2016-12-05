package com.zrsf.forclient.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import com.zrsf.backup.vo.NewsSendVo;
import com.zrsf.forclient.vo.Swry;

public class BbcxDao {
	private SqlSessionTemplate sessionTemplate;
	public SqlSessionTemplate getSessionTemplate() {
		return sessionTemplate;
	}
	public void setSessionTemplate(SqlSessionTemplate sessionTemplate) {
		this.sessionTemplate = sessionTemplate;
	}
	public List<Object> selectTaxCollectionReport(String sql,
			Map<String, Object> map) {		
		return sessionTemplate.selectList(sql,map);
	}
	
	public List<Object> selectTaxStatisticData(String sql,
			Map<String, Object> map) {		
		return sessionTemplate.selectList(sql,map);
	}
	
	/**
	 * 查询所有可以接收征管报表的税务人员：所有已激活的税务人员
	 */
	public List<Swry> selectRecipients(String sql) {
		List<Swry> list=sessionTemplate.selectList(sql);
		return list;
		
	}
	public void insertReportSendMwssage(NewsSendVo vo) {
		sessionTemplate.insert("com.zrsf.map.sx.insertLmts", vo);		
	}

}
