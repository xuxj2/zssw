package com.zrsf.forclient.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.zrsf.forclient.dao.ShuixinDao;
import com.zrsf.push.xmpp.console.Notification;
import com.zrsf.push.xmpp.model.NotificationVO;
import com.zrsf.push.xmpp.model.UserVO;

/**
 * 为推送服务提供查询支持： 当客户端获取与推送服务的连接时，判断其身份的合法性； 对于合法身份，获取连接时，查询其未读消息，并进行推送
 * 
 * @author Terry
 * 
 */
public class ShuixinService {

	private  ShuixinDao dao;
	private Notification notificationer;	
	public ShuixinDao getDao() {
		return dao;
	}
	public void setDao(ShuixinDao dao) {
		this.dao = dao;
	}

	public Notification getNotificationer() {
		return notificationer;
	}
	public void setNotificationer(Notification notificationer) {
		this.notificationer = notificationer;
	}
	/**
	 * 通过设备id查询是否存在此税务人员
	 * @param deviceId
	 * @return 当存在时，返回true，不存在返回false
	 */
	public boolean selectSwryByDeviceId(String deviceId) {
		String swryDm = selectSwrydMByDeviceId(deviceId);
		return !StringUtils.isEmpty(swryDm);
	}

	/**
	 * 通过设备id查询持有该设备的税务人员的税务人员代码
	 * @param deviceId
	 * @return 持有该设备的税务人员的税务人员代码
	 */
	public String selectSwrydMByDeviceId(String deviceId) {
		String swryDm = dao.selectSwryByDeviceId(deviceId);
		return swryDm;
	}
/**
 * 
 * 通过设备id查询持有该设备的税务人员的 未推送消息
 * @param deviceId
 * @return 
 */
	public List<NotificationVO> selectNoSendMessageByDeviceId(String deviceId) {
		List<NotificationVO> nos = dao.selectNoSendMessageByDeviceId(deviceId);
		return nos;
	}

	
//	/**
//	 * 
//	 * 通过税务人员代码查询持有该设备的税务人员的 未推送消息
//	 * @param deviceId
//	 * @return
//	 */
//	public List<NotificationVO> selectNoSendMessageBySwryDm(String swryDm) {
//		List<NotificationVO> nos = dao.selectNoSendMessageBySwryDm(swryDm);
//		return nos;
//	}

	/**
	 * 修改一组消息状态为已发送
	 * 
	 * @param readedMessageList  已成功推送的消息的id集合
	 */
	public void updateSuccessSend(List<String> readedMessageList) {
		if (readedMessageList != null && readedMessageList.size() > 0) {
			dao.updateMessageState(readedMessageList);
		}
	}
	
	public void sendNoPushMessages(String deviceId) {		 
		UserVO user = new UserVO();
		user.setDeviceId(deviceId);	
		//推送其他税务人员发来的消息
		List<NotificationVO> nos=dao.selectNoSendPtoPMessage(deviceId);
		if(nos!=null&&nos.size()>0){
			user.setNotification(nos);		
			boolean successPushed=notificationer.sendNotificationToUser(user);			
			if(successPushed){
				List<String> idList=new ArrayList<String>();			
				for (int j = 0; j < nos.size(); j++) {				
						idList.add(nos.get(j).getSxxxid());					
				}
				this.updateSuccessSend(idList);
			}	
		}		
		//推送栏目推送消息
		nos=dao.selectNoSendLtoPMessage(deviceId);
		if(nos!=null&&nos.size()>0){
			user.setNotification(nos);		
			boolean successPushed=notificationer.sendNotificationToUser(user);					
			if(successPushed){
				List<String> idList=new ArrayList<String>();
				for (int j = 0; j < nos.size(); j++) {				
						idList.add(nos.get(j).getSxxxid());					
				}				
				this.updateSuccessSend(idList);
			}	
		}	
		
		//推送报表信息
		//TODO
	}

}
