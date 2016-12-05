package com.zrsf.forclient.vo.dhsq;

import java.io.Serializable;

public class LandedEstate implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String landid;   //土地证号
	private String landdz;   //土地地址
	private String mj;   	 //土地面积
	private String dj;   	//地段等级
	private String tdlx;    //土地类型：自用、出租、承租
	private String nsqx;    //纳税期限
	private String ysmj;	//应税面积
	private String mpfse;	//每平方米应纳税额
	private String nynse;	//年应纳税额
	
	public LandedEstate(){		
	}

	public String getLandid() {
		return landid;
	}
	public void setLandid(String landid) {
		this.landid = landid;
	}
	public String getLanddz() {
		return landdz;
	}
	public void setLanddz(String landdz) {
		this.landdz = landdz;
	}
	public String getMj() {
		return mj;
	}
	public void setMj(String mj) {
		this.mj = mj;
	}
	public String getDj() {
		return dj;
	}
	public void setDj(String dj) {
		this.dj = dj;
	}
	
	public String getYsmj() {
		return ysmj;
	}

	public void setYsmj(String ysmj) {
		this.ysmj = ysmj;
	}

	
	public String getMpfse() {
		return mpfse;
	}

	public void setMpfse(String mpfse) {
		this.mpfse = mpfse;
	}

	public String getNynse() {
		return nynse;
	}

	public void setNynse(String nynse) {
		this.nynse = nynse;
	}

	public String getTdlx() {
		return tdlx;
	}
	public void setTdlx(String tdlx) {
		this.tdlx = tdlx;
	}
	public String getNsqx() {
		return nsqx;
	}
	public void setNsqx(String nsqx) {
		this.nsqx = nsqx;
	}
	public boolean equals(Object obj) {
		if(obj==null)return false;
		if(this==obj)return true;
		if(obj instanceof LandedEstate){
			LandedEstate other =(LandedEstate) obj;
			return this.landid.equals(other.getLandid());
		}
		return super.equals(obj);
	}	
	public int hashCode() {		
		return this.landid.hashCode();
	}
	
	public String toString() {		
		return this.landid+this.landdz;
	}
	
	
	
}
