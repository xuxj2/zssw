package com.zrsf.forclient.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import com.zrsf.push.xmpp.model.NotificationVO;

public class ShuixinDao {
	private  SqlSessionTemplate sqlSessionTemplate;
	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	public String selectSwryByDeviceId(String deviceId) {		
		return sqlSessionTemplate.selectOne("com.zrsf.map.txl.selectSwryByDeviceId", deviceId);
	}
	public List<NotificationVO> selectNoSendMessageByDeviceId(String deviceId) {
		List<NotificationVO> list=sqlSessionTemplate.selectList("com.zrsf.map.sx.selectNoSendMessageByDeviceId", deviceId);
		return list;
	}
//	public List<NotificationVO> selectNoSendMessageBySwryDm(String swryDm) {
//		List<NotificationVO> list=sqlSessionTemplate.selectList("com.zrsf.map.sx.selectNoSendMessageBySwryDm", swryDm);
//		return list;
//	}
	public void updateMessageState(List<String> readedMessageList) {
		sqlSessionTemplate.update("com.zrsf.map.sx.updateMessageState", readedMessageList);		
	}
	public List<NotificationVO> selectNoSendPtoPMessage(String deviceId) {		
		return sqlSessionTemplate.selectList("com.zrsf.map.sx.selectNoSendPtoPMessage", deviceId);
	}
	public List<NotificationVO> selectNoSendLtoPMessage(String deviceId) {
		return sqlSessionTemplate.selectList("com.zrsf.map.sx.selectNoSendLtoPMessage", deviceId);
	}
	
	
	
	
	
}
