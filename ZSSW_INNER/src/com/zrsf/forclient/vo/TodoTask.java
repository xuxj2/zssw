package com.zrsf.forclient.vo;

import java.io.Serializable;

public class TodoTask implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;				//id
	private String swryDm;			//任务所属处理人的税务人员代码
	private String rwzt;			//任务主题
	private String cjsj;			//创建时间
	private String xbsj;			//限办时间
	private String rwly;			//任务来源
	public TodoTask(){
		
	}
	public String getRwzt() {
		return rwzt;
	}
	public void setRwzt(String rwzt) {
		this.rwzt = rwzt;
	}
	public String getCjsj() {
		return cjsj;
	}
	public void setCjsj(String cjsj) {
		this.cjsj = cjsj;
	}
	public String getXbsj() {
		return xbsj;
	}
	public void setXbsj(String xbsj) {
		this.xbsj = xbsj;
	}
	public String getRwly() {
		return rwly;
	}
	public void setRwly(String rwly) {
		this.rwly = rwly;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setSwryDm(String swryDm) {
		this.swryDm = swryDm;
	}
	public String getId() {
		return id;
	}
	public String getSwryDm() {
		return swryDm;
	}
	
	
	
	
	
}
