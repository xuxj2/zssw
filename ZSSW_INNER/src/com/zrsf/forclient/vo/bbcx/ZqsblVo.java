package com.zrsf.forclient.vo.bbcx;

import java.io.Serializable;

public class ZqsblVo implements Serializable {

	private static final long serialVersionUID = 1L;
	private String swjgdm;
	private String swjgmc;
	private int ysbhs;
	private int yisbhs;
	private int zqsbhs;
	private double sbl;
	private double zqsbl;
	public ZqsblVo(){
		
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

	public int getYsbhs() {
		return ysbhs;
	}
	public void setYsbhs(int ysbhs) {
		this.ysbhs = ysbhs;
	}
	public int getYisbhs() {
		return yisbhs;
	}
	public void setYisbhs(int yisbhs) {
		this.yisbhs = yisbhs;
	}
	public int getZqsbhs() {
		return zqsbhs;
	}
	public void setZqsbhs(int zqsbhs) {
		this.zqsbhs = zqsbhs;
	}
	
	public void setSbl(double sbl) {
		this.sbl = sbl;
	}	
	public void setZqsbl(double zqsbl) {
		this.zqsbl = zqsbl;
	}
	public String getZqsbl() {
		return String.format("%.2f",zqsbl);
	}
	public String getSbl() {
		return String.format("%.2f",sbl);
	}
	
	
	
}
