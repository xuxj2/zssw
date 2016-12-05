package com.zrsf.forclient.vo.bbcx;

import java.io.Serializable;

public class QszjlVo implements Serializable {

	private static final long serialVersionUID = 1L;
	private String swjgdm;
	private String swjgmc;
	private double qcqsje;
	private double xzqsje;
	private double qqskje;
	private double qmqsje;
	private double qszjl;
	
	
	public QszjlVo(){
		
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
	public double catQcqsje() {
		return qcqsje;
	}
	public void setQcqsje(double qcqsje) {
		this.qcqsje = qcqsje;
	}
	public double catXzqsje() {
		return xzqsje;
	}
	public void setXzqsje(double xzqsje) {
		this.xzqsje = xzqsje;
	}
	public double catQqskje() {
		return qqskje;
	}
	public void setQqskje(double qqskje) {
		this.qqskje = qqskje;
	}
	public double catQmqsje() {
		return qmqsje;
	}
	public void setQmqsje(double qmqsje) {
		this.qmqsje = qmqsje;
	}
	public String getQszjl() {
		return String.format("%.2f", qszjl);
	}
	public void setQszjl(double qszjl) {
		this.qszjl = qszjl;
	}
	public String getQcqsje() {
		return String.format("%.2f",qcqsje);
	}
	public String getXzqsje() {
		return String.format("%.2f",xzqsje);
	}
	public String getQqskje() {
		return String.format("%.2f",qqskje);
	}
	public String getQmqsje() {
		return String.format("%.2f",qmqsje);
	}
	
	
	
}
