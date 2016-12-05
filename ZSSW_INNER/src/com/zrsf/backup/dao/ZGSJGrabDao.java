package com.zrsf.backup.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

public class ZGSJGrabDao {	
	private  SqlSessionTemplate sqlSessionTemplate;
	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}


	public void insertZqsbl(List<Map<String, String>> list) {
		for (Map<String, String> map : list) {
			sqlSessionTemplate.insert("com.zrsf.map.zgsjrk.insertZqsbl", map);
		}
	}

	public void insertYqsbcfl(List<Map<String, String>> list) {
		for (Map<String, String> map : list) {
			sqlSessionTemplate.insert("com.zrsf.map.zgsjrk.insertYqsbcfl", map);
		}
	}

	public void insertQszjl(List<Map<String, String>> list) {
		for (Map<String, String> map : list){
			sqlSessionTemplate.insert("com.zrsf.map.zgsjrk.insertQszjl", map);
		}
	}

	public void insertRwzqwcl(List<Map<String, String>> list) {
		for (Map<String, String> map : list) {
			sqlSessionTemplate.insert("com.zrsf.map.zgsjrk.insertRwzqwcl", map);
		}
	}

	public void insertZqrkl(List<Map<String, String>> list) {
		for (Map<String, String> map : list) {
			sqlSessionTemplate.insert("com.zrsf.map.zgsjrk.insertZqrkl", map);
		}
	}
	
	public void addReport(String sql, List<Map<String, String>> list) {
		for (Map<String, String> map : list) {
			sqlSessionTemplate.insert(sql, map);
		}		
	}

	
	/**
	 * 征管报表查询
	 * @param sql
	 * @param map
	 * @return
	 */
	public List<?> selectTaxCollectionReport(String sql,
			Map<String, Object> map) {		
		return sqlSessionTemplate.selectList(sql,map);
	}
	/**
	 * 删除报表
	 * @param sql
	 * @param map
	 */
	public int removeReport(String sql, Map<String, Object> map) {
		return sqlSessionTemplate.delete(sql,map);		
	}
}
