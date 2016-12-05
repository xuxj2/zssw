package com.zrsf.forclient.vo.dhsq;

import java.io.Serializable;

public class Investor implements Serializable {
	
	private static final long serialVersionUID = 1L;
	//private String xh;   //序号,
	private String tzrxm;   //投资人名称
	private String tzrid;   //身份证号
	private String tzrtel;   //投资人联系电话
	private String tzmoney;   //投资金额
	//private String tzbz;   //币种
	private String tzbl;   //投资比例
	public Investor(){
		
	}
//	public String getXh() {
//		return xh;
//	}
//	public void setXh(String xh) {
//		this.xh = xh;
//	}
	public String getTzrxm() {
		return tzrxm;
	}
	public void setTzrxm(String tzrxm) {
		this.tzrxm = tzrxm;
	}
	public String getTzrid() {
		return tzrid;
	}
	public void setTzrid(String tzrid) {
		this.tzrid = tzrid;
	}
	public String getTzrtel() {
		return tzrtel;
	}
	public void setTzrtel(String tzrtel) {
		this.tzrtel = tzrtel;
	}
	public String getTzmoney() {
		return tzmoney;
	}
	public void setTzmoney(String tzmoney) {
		this.tzmoney = tzmoney;
	}
//	public String getTzbz() {
//		return tzbz;
//	}
//	public void setTzbz(String tzbz) {
//		this.tzbz = tzbz;
//	}
	public String getTzbl() {
		return tzbl;
	}
	public void setTzbl(String tzbl) {
		this.tzbl = tzbl;
	}
	
	public boolean equals(Object obj) {
		if(obj==null)return false;
		if(this==obj)return true;
		if(obj instanceof Investor){
			Investor other =(Investor) obj;
			return this.tzrid.equals(other.getTzrid());
		}
		return super.equals(obj);
	}	
	public int hashCode() {		
		return this.tzrid.hashCode();
	}
	
	public String toString() {		
		return this.tzrxm;
	}
}
