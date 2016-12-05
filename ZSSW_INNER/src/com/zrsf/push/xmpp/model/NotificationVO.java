package com.zrsf.push.xmpp.model;

import java.io.Serializable;
import java.util.Date;

public class NotificationVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String sxxxid;// 税信消息主表id
	private String xxlyDm; // 消息来源代码
	private String xxlyTb; // 消息来源图标，栏目图标或人员头像
	private String xxlyMc; // 消息来源名字:税务人员姓名或栏目名称
	private String xxlxDm; // 消息类型 0聊天，1新闻动态，2待办任务，3 系统消息,征管报表8，规财报表9
	private Date cjsj; // 创建时间
	// private String TITLE;
	private String xxnr; // 消息内容
	private String xxid; // 链接,封装具体内容id
	private String xyhz = "0"; // 是否需要回执，0否，1是
	// private boolean sfyd; //是否发送成功

	public NotificationVO() {
		super();
	}

	public String getSxxxid() {
		return sxxxid;
	}

	public void setSxxxid(String sxxxid) {
		this.sxxxid = sxxxid;
	}

	public String getXxlyDm() {
		return xxlyDm;
	}

	public void setXxlyDm(String xxlyDm) {
		this.xxlyDm = xxlyDm;
	}

	public String getXxlyTb() {
		return xxlyTb;
	}

	public void setXxlyTb(String xxlyTb) {
		this.xxlyTb = xxlyTb;
	}

	public String getXxlyMc() {
		return xxlyMc;
	}

	public void setXxlyMc(String xxlyMc) {
		this.xxlyMc = xxlyMc;
	}

	public String getXxlxDm() {
		return xxlxDm;
	}

	public void setXxlxDm(String xxlxDm) {
		this.xxlxDm = xxlxDm;
	}

	public long getCjsj() {
		return cjsj.getTime();
	}

	public void setCjsj(Date cjsj) {
		this.cjsj = cjsj;
	}

	public String getXxnr() {
		return xxnr;
	}

	public void setXxnr(String xxnr) {
		this.xxnr = xxnr;
	}

	public String getXxid() {
		return xxid;
	}

	public void setXxid(String xxid) {
		this.xxid = xxid;
	}

	public String getXyhz() {
		return xyhz;
	}

	public void setXyhz(String xyhz) {
		this.xyhz = xyhz;
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (this == obj)
			return true;
		if (obj instanceof NotificationVO) {
			NotificationVO user = (NotificationVO) obj;
			return this.xxlyDm.equals(user.getXxlyDm())
					&& this.xxid.equals(user.getXxid());
		}
		return false;
	}

	public int hashCode() {
		return super.hashCode();
	}

	public String toString() {
		return this.xxlyMc + "(" + this.xxlyDm + ")" + "at" + this.cjsj
				+ " send a " + this.xxlxDm + " message " + this.xxnr;
	}
}
