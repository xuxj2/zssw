package com.zrsf.manage.vo;

import java.io.Serializable;
import java.util.List;

public class SystemPushVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<String> receiveds; //接收人
	private String zw;//正文
	private String fjurl;//附件Url
	private String title;//标题
	
	
	
	
	public List<String> getReceiveds() {
		return receiveds;
	}
	public void setReceiveds(List<String> receiveds) {
		this.receiveds = receiveds;
	}
	public String getZw() {
		return zw;
	}
	public void setZw(String zw) {
		this.zw = zw;
	}
	
	public String getFjurl() {
		return fjurl;
	}
	public void setFjurl(String fjurl) {
		this.fjurl = fjurl;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	

}
