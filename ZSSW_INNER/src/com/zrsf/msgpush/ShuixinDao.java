package com.zrsf.msgpush;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import com.zrsf.forclient.vo.Swry;
import com.zrsf.push.xmpp.model.NotificationVO;

public class ShuixinDao {
	private  SqlSessionTemplate sqlSessionTemplate;
	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	public Swry selectSwryByDeviceId(String sql,String deviceId) {		
		return sqlSessionTemplate.selectOne(sql, deviceId);
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
		return sqlSessionTemplate.selectList("com.zrsf.map.sx.selectPtoPMessageByDeviceId", deviceId);
	}
	public List<NotificationVO> selectNoSendLtoPMessage(String deviceId) {
		return sqlSessionTemplate.selectList("com.zrsf.map.sx.selectLtoPMessageByDeviceId", deviceId);
	}
	
	public void insertMessage(String sql,ToMessage message) {
		sqlSessionTemplate.insert(sql, message);	
	}
	public void deleteMessage(String sql, ToMessage message) {
		sqlSessionTemplate.delete(sql, message);
	}
	public List<ToMessage> selectNoSends(String sql, String deviceId) {		
		return sqlSessionTemplate.selectList(sql, deviceId);
	}
	public List<ToMessage> selectNoSends(String sql) {		
		return sqlSessionTemplate.selectList(sql);
	}
	
	
	
	
	
}
