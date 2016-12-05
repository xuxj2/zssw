package com.zrsf.forclient.vo.bbcx;

import java.io.Serializable;

public class DjhsbdbbVo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String swjgdm;
	private String swjgmc;
	private int qchs;
	private int xzhs;	
	private int qmhs;
	
	public DjhsbdbbVo(){		
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
	public int getQchs() {
		
		return qchs;
	}
	public void setQchs(int qchs) {
		this.xzhs=this.xzhs+this.qchs-qchs;
		this.qchs = qchs;
	}
	public int getXzhs() {
		return xzhs;
	}
//	public void setXzhs(int xzhs) {
//		this.xzhs = xzhs;
//	}
//	public int catXzhs() {
//		return xzhs;
//	}
	public int getQmhs() {
		return qmhs;
	}
	public void setQmhs(int qmhs) {
		this.xzhs=this.xzhs-this.qmhs+qmhs;
		this.qmhs = qmhs;
	}
	

}
