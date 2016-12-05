package com.zrsf.forclient.dao;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import com.zrsf.forclient.vo.Swry;

public class IndividuationDao {
	private SqlSessionTemplate sqlSessionTemplate;

	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	/**
	 * 修改头像
	 * 
	 * @param swryDm
	 * @param fileName
	 */
	public void updateAvatar(String swryDm, String fileName) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("swryDm", swryDm);
		map.put("fileName", fileName);
		sqlSessionTemplate.update("com.zrsf.map.login.updateAvantar", map);
	}

	public void updateIndInfo(Swry swryUp) {
		sqlSessionTemplate.update("com.zrsf.map.login.updateIndInfo", swryUp);
		
	}

}
