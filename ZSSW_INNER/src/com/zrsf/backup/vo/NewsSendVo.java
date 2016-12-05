package com.zrsf.backup.vo;

import java.io.Serializable;

public class NewsSendVo implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long id;
	private String lmId; 			//栏目id
	private String newsId;			//消息id
	private String title;			//标题
	private String lmMc;			//栏目名称
	private String xxlx;
	
	
	public String getXxlx() {
		return xxlx;
	}
	public void setXxlx(String xxlx) {
		this.xxlx = xxlx;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNewsId() {
		return newsId;
	}
	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}
	public void setTitle(String title) {
		this.title = title;
	}	
	public String getTitle() {
		return title;
	}
	public String getLmId() {
		return lmId;
	}
	public void setLmId(String lmId) {
		this.lmId = lmId;
	}
	public String getLmMc() {
		return lmMc;
	}
	public void setLmMc(String lmMc) {
		this.lmMc = lmMc;
	}
	
}
