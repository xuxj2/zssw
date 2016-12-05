package com.zrsf.forclient.vo.bbcx;

import java.io.Serializable;

public class RwzqwclVo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String swjgdm;
	private String swjgmc;
	private int ywcrws;
	private int zqwcrws;
	private double rwzqwcl;
	
	public RwzqwclVo(){
		
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

	public int getYwcrws() {
		return ywcrws;
	}

	public void setYwcrws(int ywcrws) {
		this.ywcrws = ywcrws;
	}

	public int getZqwcrws() {
		return zqwcrws;
	}

	public void setZqwcrws(int zqwcrws) {
		this.zqwcrws = zqwcrws;
	}
	public void setRwzqwcl(double rwzqwcl) {
		this.rwzqwcl = rwzqwcl;
	}
	public String getRwzqwcl() {
		return String.format("%.2f",rwzqwcl);
	}
}
