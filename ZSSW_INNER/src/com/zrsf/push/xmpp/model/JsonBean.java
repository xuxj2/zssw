package com.zrsf.push.xmpp.model;

public class JsonBean {
	private String lm_id;
	private String id;
	private String title;
	private String lm_mc;
	
	
	public void setId(String id) {
		this.id = id;
	}
	public void setTitle(String title) {
		this.title = title;
	}	

	public String getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public String getLm_id() {
		return lm_id;
	}
	public void setLm_id(String lm_id) {
		this.lm_id = lm_id;
	}
	public String getLm_mc() {
		return lm_mc;
	}
	public void setLm_mc(String lm_mc) {
		this.lm_mc = lm_mc;
	}

	
	
	
	

}
