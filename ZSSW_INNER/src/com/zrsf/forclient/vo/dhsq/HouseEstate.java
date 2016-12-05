package com.zrsf.forclient.vo.dhsq;

import java.io.Serializable;

public class HouseEstate implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private String xh;   //序号,
	private String fczh;   //产权证书号",
	private String fczld;   //房屋坐落",
	private String jzmj;   //房屋建筑面积",
	private String fcyz;   //房产原值",
	private String iszyfc;   //是否自有房产",
	private String zq;   //租期,
	private String nzj;   //年租金,
	private String chuzfxxv;   //出租方信息,
	private String chengzfxxv;   //承租方信息	
	public HouseEstate(){
		
	}
//	public String getXh() {
//		return xh;
//	}
//	public void setXh(String xh) {
//		this.xh = xh;
//	}
	public String getFczh() {
		return fczh;
	}
	public void setFczh(String fczh) {
		this.fczh = fczh;
	}
	public String getFczld() {
		return fczld;
	}
	public void setFczld(String fczld) {
		this.fczld = fczld;
	}
	public String getJzmj() {
		return jzmj;
	}
	public void setJzmj(String jzmj) {
		this.jzmj = jzmj;
	}
	public String getFcyz() {
		return fcyz;
	}
	public void setFcyz(String fcyz) {
		this.fcyz = fcyz;
	}
	public String getIszyfc() {
		return iszyfc;
	}
	public void setIszyfc(String iszyfc) {
		this.iszyfc = iszyfc;
	}
	public String getZq() {
		return zq;
	}
	public void setZq(String zq) {
		this.zq = zq;
	}
	public String getNzj() {
		return nzj;
	}
	public void setNzj(String nzj) {
		this.nzj = nzj;
	}
	public String getChuzfxxv() {
		return chuzfxxv;
	}
	public void setChuzfxxv(String chuzfxxv) {
		this.chuzfxxv = chuzfxxv;
	}
	public String getChengzfxxv() {
		return chengzfxxv;
	}
	public void setChengzfxxv(String chengzfxxv) {
		this.chengzfxxv = chengzfxxv;
	}
	
	public boolean equals(Object obj) {
		if(obj==null)return false;
		if(this==obj)return true;
		if(obj instanceof HouseEstate){
			HouseEstate other =(HouseEstate) obj;
			return this.fczh.equals(other.getFczh());
		}
		return super.equals(obj);
	}	
	public int hashCode() {		
		return this.fczh.hashCode();
	}
	
	public String toString() {		
		return this.fczh+this.fczld;
	}
	
	
}
