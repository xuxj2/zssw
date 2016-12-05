package com.zrsf.push.xmpp.model;

import java.util.List;

public class UserVO {
	private String deviceId;//设备ID
	private boolean  status=false;//是否运行摧毁程序，0为否，1为是
	private List<NotificationVO> notification;//消息集合	
	
	public UserVO(){
		super();
	}
//	public UserVO(String swryGh){
//		this.SWRY_GH=swryGh;
//	}	
//	public String getSWRY_GH() {
//		return SWRY_GH;
//	}
//	public void setSWRY_GH(String sWRY_GH) {
//		SWRY_GH = sWRY_GH;
//	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public List<NotificationVO> getNotification() {
		return notification;
	}
	public void setNotification(List<NotificationVO> notification) {
		this.notification = notification;
	}
	public boolean equals(Object obj) {
		if(obj==null){
			return false;
		}
		if(this==obj)return true;
		if(obj instanceof UserVO){
			UserVO user=(UserVO) obj;
			return this.deviceId.equals(user.getDeviceId())&&this.notification.hashCode()==user.getNotification().hashCode();
		}
		return false;
	}

	public int hashCode() {		
		return super.hashCode();
	}

	public String toString() {
		
		return super.toString();
	}
	
	
	

}
