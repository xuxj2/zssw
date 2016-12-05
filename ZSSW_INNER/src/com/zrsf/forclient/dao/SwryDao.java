package com.zrsf.forclient.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import com.zrsf.forclient.vo.Swry;
import com.zrsf.manage.vo.txlJsonBean;

public class SwryDao {
	private SqlSessionTemplate sessionTemplate;	
	public SqlSessionTemplate getSessionTemplate() {
		return sessionTemplate;
	}
	public void setSessionTemplate(SqlSessionTemplate sessionTemplate) {
		this.sessionTemplate = sessionTemplate;
	}

	public Swry selectLoginSwry(String password, String deviceid) {
		Map<String, String> map=new HashMap<String, String>();
		map.put("password", password);
		map.put("deviceid", deviceid);	
		Swry swry=null;
		try {
			 swry=sessionTemplate.selectOne("com.zrsf.map.login.selectLoginSwry", map);	
		} catch (Exception e) {
			e.printStackTrace();
		}			
		return swry;
	}
	public Swry selectSwry(String sql, Map<String, Object> map) {
		Swry swry=sessionTemplate.selectOne(sql, map);	
		return swry;
	}

	public List<Swry>  selectSwrys(String sql, Map<String, Object> map) {
		List<Swry>  swrys=sessionTemplate.selectList(sql, map);	
		return swrys;
	}
	
	public void updateLimit(String sql, Map<String, Object> map) {
		sessionTemplate.update(sql, map);		
	}
	public List<txlJsonBean> selectSwjg(String sql) {		
		return sessionTemplate.selectList(sql);
	}
	public List<txlJsonBean> selectSelectable(String sql, int permission) {		
		return sessionTemplate.selectList(sql,permission);
	}
	
}
