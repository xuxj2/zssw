package com.zrsf.backup.vo;

import java.io.Serializable;
import java.util.List;

public class LmdyVo implements Serializable{

	private static final long serialVersionUID = 1L;
	private String swryDm;
	private String deviceId;
	private List<String> dylms;	
	public LmdyVo() {
		super();		
	}
	public String getSwryDm() {
		return swryDm;
	}
	public void setSwryDm(String swryDm) {
		this.swryDm = swryDm;
	}
	public List<String> getDylms() {
		return dylms;
	}
	public void setDylms(List<String> dylms) {
		this.dylms = dylms;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
}
