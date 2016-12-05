package com.zrsf.manage.vo;

import java.io.Serializable;

public class News implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;					//id
	private String columnId;			//栏目id
	//private String columnName;			//栏目名称
	private String sourceurl;			//原始地址
	private String title;				//标题
	private String time;				//时间
	private String textContent;			//内容
	private String fileurl;				//附件链接，给一段内容附加超链接
	private String imageurl;			//图片链接，直接显示链接内容
	private String channel;				//消息来源，发文单位
	private String issueNumber;			//文号
	private String effective;			//有效性
	private String visibility;			//客户端是否可查看
	
	
	public News(){
		
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getColumnId() {
		return columnId;
	}



	public void setColumnId(String columnId) {
		this.columnId = columnId;
	}



	public String getSourceurl() {
		return sourceurl;
	}



	public String getVisibility() {
		return visibility;
	}



	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}



	public void setSourceurl(String sourceurl) {
		this.sourceurl = sourceurl;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getTime() {
		return time;
	}



	public void setTime(String time) {
		this.time = time;
	}



	public String getTextContent() {
		return textContent;
	}



	public void setTextContent(String textContent) {
		this.textContent = textContent;
	}



	public String getFileurl() {
		return fileurl;
	}



	public void setFileurl(String fileurl) {
		this.fileurl = fileurl;
	}



	public String getImageurl() {
		return imageurl;
	}



	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}



	public String getChannel() {
		return channel;
	}



	public void setChannel(String channel) {
		this.channel = channel;
	}



	public String getIssueNumber() {
		return issueNumber;
	}



	public void setIssueNumber(String issueNumber) {
		this.issueNumber = issueNumber;
	}



	public String getEffective() {
		return effective;
	}



	public void setEffective(String effective) {
		this.effective = effective;
	}
	
}
