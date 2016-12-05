package com.zrsf.forclient.vo.dhsq;

import java.io.Serializable;

public class OfficeStaff implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	private String xh;   //序号 
	private String xm;   //姓名 
	private String rzryid;   //身份证号 
	private String zw;   //职务 
	private String tel;   //联系电话 
	private String yddh;   //联系电话 
	public OfficeStaff(){
		
	}
//	public String getXh() {
//		return xh;
//	}
//	public void setXh(String xh) {
//		this.xh = xh;
//	}
	
	public String getXm() {
		return xm;
	}
	public String getYddh() {
		return yddh;
	}
	public void setYddh(String yddh) {
		this.yddh = yddh;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getRzryid() {
		return rzryid;
	}
	public void setRzryid(String rzryid) {
		this.rzryid = rzryid;
	}
	public String getZw() {
		return zw;
	}
	public void setZw(String zw) {
		this.zw = zw;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public boolean equals(Object obj) {
		if(obj==null)return false;
		if(this==obj)return true;
		if(obj instanceof OfficeStaff){
			OfficeStaff other =(OfficeStaff) obj;
			return this.rzryid.equals(other.getRzryid());
		}
		return super.equals(obj);
	}	
	public int hashCode() {		
		return this.getRzryid().hashCode();
	}
	
	public String toString() {		
		return this.xm+"("+this.rzryid+")";
	}
	
	
}
