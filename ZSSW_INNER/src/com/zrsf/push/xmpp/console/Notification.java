package com.zrsf.push.xmpp.console;

import net.sf.json.JSONArray;

import com.zrsf.msgpush.ToMessage;
import com.zrsf.push.xmpp.model.UserVO;
import com.zrsf.push.xmpp.push.NotificationManager;
import com.zrsf.push.xmpp.session.ClientSession;
import com.zrsf.push.xmpp.session.SessionManager;

public class Notification {

	NotificationManager notificationManager;

	/**
	 * 判断接收用户是否在线
	 * 
	 * @param UserName
	 * @return boolean
	 */
	public boolean getUserSessionByName(String UserName) {
		ClientSession session = SessionManager.getInstance().getSession(
				UserName);
		if (session == null) {
			return false;
		}
		return true;
	}

	/**
	 * 传入UserVO对象，发送该对象所有的通知
	 * 
	 * @param user
	 * @return
	 */
	public Boolean sendNotificationToUser(UserVO user) {
		JSONArray jArray = JSONArray.fromObject(user.getNotification());
		return notificationManager.sendNotifcationToUser("1234567890", user
				.getDeviceId().toLowerCase().trim(), jArray.toString());

	}

	public NotificationManager getNotificationManager() {
		return notificationManager;
	}

	public void setNotificationManager(NotificationManager notificationManager) {
		this.notificationManager = notificationManager;
	}

	public Boolean sendNotificationToUser(ToMessage message) {
		return notificationManager.sendNotifcationToUser("1234567890", message
				.getDeviceId().toLowerCase().trim(), message.getMessage());

	}

}
