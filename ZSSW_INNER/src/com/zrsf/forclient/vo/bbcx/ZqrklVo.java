package com.zrsf.forclient.vo.bbcx;

import java.io.Serializable;

public class ZqrklVo implements Serializable {

	private static final long serialVersionUID = 1L;
	private String swjgdm;
	private String swjgmc;
	private double yrkje;
	private double yirkje;
	private double rkl;
	private int yrkhs;
	private int zqrkhs;
	private double zqrkl;
	public ZqrklVo(){
		
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
	public double catYrkje() {
		return yrkje;
	}
	public void setYrkje(double yrkje) {
		this.yrkje = yrkje;
	}
	public double catYirkje() {
		return yirkje;
	}
	public void setYirkje(double yirkje) {
		this.yirkje = yirkje;
	}
	
	public void setRkl(double rkl) {
		this.rkl = rkl;
	}
	public int getYrkhs() {
		return yrkhs;
	}
	public void setYrkhs(int yrkhs) {
		this.yrkhs = yrkhs;
	}
	public int getZqrkhs() {
		return zqrkhs;
	}
	public void setZqrkhs(int zqrkhs) {
		this.zqrkhs = zqrkhs;
	}
	
	public void setZqrkl(double zqrkl) {
		this.zqrkl = zqrkl;
	}
	public String getYrkje() {
		return String.format("%.2f",yrkje);
	}
	public String getYirkje() {
		return String.format("%.2f",yirkje);
	}	
	public String getRkl() {
		return String.format("%.2f",rkl);
	}
	public String getZqrkl() {
		return String.format("%.2f",zqrkl);
	}
}
