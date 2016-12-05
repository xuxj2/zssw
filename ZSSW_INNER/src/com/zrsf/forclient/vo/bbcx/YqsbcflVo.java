package com.zrsf.forclient.vo.bbcx;

import java.io.Serializable;

public class YqsbcflVo implements Serializable {

	private static final long serialVersionUID = 1L;
	private String swjgdm;
	private String swjgmc;
	private int yqsbhs;
	private int cfhs;
	private double yqsbcfl;
	public YqsbcflVo(){
		
	}
	public String getSwjgdm() {
		return swjgdm;
	}
	public void setSwjgdm(String swjgdm) {
		this.swjgdm = swjgdm;
	}
	public String getSwjgmc() {
		return swjgmc;
	}
	public void setSwjgmc(String swjgmc) {
		this.swjgmc = swjgmc;
	}
	public int getYqsbhs() {
		return yqsbhs;
	}
	public void setYqsbhs(int yqsbhs) {
		this.yqsbhs = yqsbhs;
	}
	public int getCfhs() {
		return cfhs;
	}
	public void setCfhs(int cfhs) {
		this.cfhs = cfhs;
	}	
	public void setYqsbcfl(double yqsbcfl) {
		this.yqsbcfl = yqsbcfl;
	}
	public String getYqsbcfl() {
		return String.format("%.2f",yqsbcfl);
	}
	
}
