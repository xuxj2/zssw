package com.zrsf.forclient.vo;

import java.io.Serializable;

public class TaxCalendarVo implements Serializable{
	private static final long serialVersionUID = 1L;
	private String taxCalendarId;			//办税日历主表id
	private String area;					//地区
	private String year;	//年度
	public String getTaxCalendarId() {
		return taxCalendarId;
	}
	public void setTaxCalendarId(String taxCalendarId) {
		this.taxCalendarId = taxCalendarId;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	
	
}
