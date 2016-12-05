package com.zrsf.appcenter;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

public class ApplicationDao {
	private SqlSessionTemplate sessionTemplate;

	public SqlSessionTemplate getSessionTemplate() {
		return sessionTemplate;
	}

	public void setSessionTemplate(SqlSessionTemplate sessionTemplate) {
		this.sessionTemplate = sessionTemplate;
	}

	public ApplicationDao() {
		super();
	}

	public ApplicationVo selectAppDetail(String packageName) {
		return sessionTemplate.selectOne("com.zrsf.appcenter.selectAppDetail",
				packageName);
	}

	public List<ApplicationVo> selectAllApps() {		
		return sessionTemplate.selectList("com.zrsf.appcenter.selectAllApps");
	}

	
}
