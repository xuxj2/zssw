package com.zrsf.msgpush;

import java.io.Serializable;
import java.util.Date;

public class ToMessage implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final int DELTYPE=2;
	private long id; 
	private String deviceId;
	private String message;
	private Date   recieveTime;
	private int type; //用于表示消息类别，有些栏目有新消息时，需删除旧的未发消息 ,此时值为2
	
	
	public ToMessage(){
		super();
	}
	public ToMessage(String deviceId, String message) {
		super();
		this.deviceId = deviceId;
		this.message = message;
	}
	
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getRecieveTime() {
		return recieveTime;
	}
	public void setRecieveTime(Date recieveTime) {
		this.recieveTime = recieveTime;
	}

	public boolean equals(Object obj) {
		if(obj==null){
			return false;
		}
		if(this==obj){
			return true;
		}
		if(obj instanceof ToMessage ){
			ToMessage other=(ToMessage) obj;
			return this.id==other.getId();
		}
		return super.equals(obj);
	}

	
	public int hashCode() {		
		return (int) this.id;
	}
	
	public String toString() {		
		return message;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
}
