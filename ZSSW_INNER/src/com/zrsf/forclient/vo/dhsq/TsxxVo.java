package com.zrsf.forclient.vo.dhsq;

import java.io.Serializable;

public class TsxxVo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String tssqrq;
	private String zsxm;
	private String zspm;
	private String sfssq;
	private double tsje;
	private String tsrq;
	public TsxxVo(){
		
	}
	public String getTssqrq() {
		return tssqrq;
	}
	public void setTssqrq(String tssqrq) {
		this.tssqrq = tssqrq;
	}
	public String getZsxm() {
		return zsxm;
	}
	public void setZsxm(String zsxm) {
		this.zsxm = zsxm;
	}
	public String getZspm() {
		return zspm;
	}
	public void setZspm(String zspm) {
		this.zspm = zspm;
	}
	public String getSfssq() {
		return sfssq;
	}
	public void setSfssq(String sfssq) {
		this.sfssq = sfssq;
	}
	public String getTsje() {
		return String.format("%.2f", tsje);
	}
	public void setTsje(double tsje) {
		this.tsje = tsje;
	}
	public String getTsrq() {
		return tsrq;
	}
	public void setTsrq(String tsrq) {
		this.tsrq = tsrq;
	}
	
	
	
	
}
