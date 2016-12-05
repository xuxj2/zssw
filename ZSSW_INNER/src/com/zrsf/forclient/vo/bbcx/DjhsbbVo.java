package com.zrsf.forclient.vo.bbcx;

import java.io.Serializable;

public class DjhsbbVo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String swjgdm;
	private String swjgmc;
	
	private int qyhs;
	private int gths;	
	private int zchs;
	private int fzchs;
	private int tyhs;
	private int hjhs;
	
	public DjhsbbVo() {
		super();		
	}	
	
	public int getHjhs() {
		return hjhs;
	}
//	public int catHjhs() {
//		return hjhs;
//	}
	public int getQyhs() {
		return qyhs;
	}
	public void setQyhs(int qyhs) {
		this.hjhs=this.hjhs-this.qyhs+qyhs;
		this.qyhs = qyhs;
	}

	public int getGths() {
		return gths;
	}

	public void setGths(int gths) {
		this.hjhs=this.hjhs-this.gths+gths;
		this.gths = gths;
	}

//	public void setHjhs(int hjhs) {
//		this.hjhs = hjhs;
//	}

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

	public int getZchs() {
		return zchs;
	}

	public void setZchs(int zchs) {
		this.zchs = zchs;
	}

	public int getFzchs() {
		return fzchs;
	}

	public void setFzchs(int fzchs) {
		this.fzchs = fzchs;
	}

	public int getTyhs() {
		return tyhs;
	}

	public void setTyhs(int tyhs) {
		this.tyhs = tyhs;
	}
	
	
}
