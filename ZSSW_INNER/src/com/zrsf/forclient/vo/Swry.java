package com.zrsf.forclient.vo;

import java.io.Serializable;

public class Swry implements Serializable{	
	private static final long serialVersionUID = 1L;
	private String swryDm;		//税务人员代码
	private String swryGh;		//税务人员工号
	private String deviceid;	//设备id
	private String xm;			//姓名
	private String yddh;		//移动电话
	
	private String xb;			//性别
	private String zw;			//职务
	private String bgdh;		//办公电话
	private String rzjgDm;		//任职机关代码
	private String rzjgMc;		//任职机关名称
	private String jhbj;    	//是否激活
	private String txUri;		//头像地址
	private String zndm;		//职能代码
	private String znfw;		//职能范围
	private int limit;//权限值
	
	private String yzsj;		//验证用手机号
	
	public Swry(){		
	}

	public String getSwryDm() {
		return swryDm;
	}

	public void setSwryDm(String swryDm) {
		this.swryDm = swryDm;
	}

	public String getSwryGh() {
		return swryGh;
	}

	public void setSwryGh(String swryGh) {
		this.swryGh = swryGh;
	}

	public String getRzjgMc() {
		return rzjgMc;
	}

	public void setRzjgMc(String rzjgMc) {
		this.rzjgMc = rzjgMc;
	}

	public String getDeviceid() {
		return deviceid;
	}

	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {	
		this.xm = xm;
	}

	public String getYddh() {
		return yddh;
	}

	public void setYddh(String yddh) {
		this.yddh = yddh;
	}

	public String getXb() {
		return xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
	}

	public String getZw() {
		return zw;
	}

	public void setZw(String zw) {
		this.zw = zw;
	}

	public String getBgdh() {
		return bgdh;
	}

	public void setBgdh(String bgdh) {
		this.bgdh = bgdh;
	}

	public String getRzjgDm() {
		return rzjgDm;
	}

	public void setRzjgDm(String rzjgDm) {
		this.rzjgDm = rzjgDm;
	}

	public String getJhbj() {
		return jhbj;
	}

	public void setJhbj(String jhbj) {
		this.jhbj = jhbj;
	}

	public String getTxUri() {
		return txUri;
	}

	public void setTxUri(String txUri) {
		this.txUri = txUri;
	}
	
	

	
	public String getZndm() {
		return zndm;
	}

	public void setZndm(String zndm) {
		this.zndm = zndm;
	}

	public String getZnfw() {
		return znfw;
	}

	public String getYzsj() {
		return yzsj;
	}

	public void setYzsj(String yzsj) {
		this.yzsj = yzsj;
	}

	public void setZnfw(String znfw) {
		this.znfw = znfw;
	}

	public boolean equals(Object obj) {
		if(obj==null){
			return false;
		}
		if(this==obj){
			return true;
		}
		if(obj instanceof Swry ){
			Swry other=(Swry) obj;
			return this.swryGh.equals(other.getSwryGh());
		}
		return super.equals(obj);
	}


	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int hashCode() {		
		return this.swryGh.hashCode();
	}
	
	public String toString() {		
		return this.swryGh+":"+this.xm;
	}
	
}
