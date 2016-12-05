package com.zrsf.forclient.vo;

import java.io.Serializable;

public class TaxcalendarDetailVo implements Serializable{
	private static final long serialVersionUID = 1L;
	private String taxCalendarDetailId;		//办税日历详情id
	private String taxCalendarId;			//办税日历主表id
	private String month;					//月份
	private String startDay;				//开始日期
	private String endDay;					//截止日期
	private String content;					//内容
	public String getTaxCalendarDetailId() {
		return taxCalendarDetailId;
	}
	public void setTaxCalendarDetailId(String taxCalendarDetailId) {
		this.taxCalendarDetailId = taxCalendarDetailId;
	}
	public String getTaxCalendarId() {
		return taxCalendarId;
	}
	public void setTaxCalendarId(String taxCalendarId) {
		this.taxCalendarId = taxCalendarId;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getStartDay() {
		return startDay;
	}
	public void setStartDay(String startDay) {
		this.startDay = startDay;
	}
	public String getEndDay() {
		return endDay;
	}
	public void setEndDay(String endDay) {
		this.endDay = endDay;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
