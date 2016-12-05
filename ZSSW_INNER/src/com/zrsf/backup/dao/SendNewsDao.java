//package com.zrsf.backup.dao;
//
//import java.util.List;
//import java.util.Map;
//
//import org.mybatis.spring.SqlSessionTemplate;
//
//import com.zrsf.backup.vo.NewsSendVo;
//
//public class SendNewsDao {
//	private SqlSessionTemplate sessionTemplate;	
//	public SqlSessionTemplate getSessionTemplate() {
//		return sessionTemplate;
//	}
//	public void setSessionTemplate(SqlSessionTemplate sessionTemplate) {
//		this.sessionTemplate = sessionTemplate;
//	}
//
//	public List<NewsSendVo> selectSendNews(Map<String, Object> paras) {
//		List<NewsSendVo> list=sessionTemplate.selectList("com.zrsf.map.lmxx.selectSendNews", paras);
//		return list;
//	}
//	
//}
