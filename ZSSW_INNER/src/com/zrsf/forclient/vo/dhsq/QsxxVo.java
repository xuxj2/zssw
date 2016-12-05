package com.zrsf.forclient.vo.dhsq;

import java.io.Serializable;

public class QsxxVo implements Serializable {

	private static final long serialVersionUID = 1L;
	private String sbrq;
	private String zsxm;
	private String zspm;
	private String sfssq;	
	private double qsje;
	private String jkqx;
	private String kjrq;
	public QsxxVo(){
		
	}
	public String getSbrq() {
		return sbrq;
	}
	public void setSbrq(String sbrq) {
		this.sbrq = sbrq;
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
	
	public String getQsje() {
		return String.format("%.2f", qsje);
	}
	public void setQsje(double qsje) {
		this.qsje = qsje;
	}
	public String getJkqx() {
		return jkqx;
	}
	public void setJkqx(String jkqx) {
		this.jkqx = jkqx;
	}
	public String getKjrq() {
		return kjrq;
	}
	public void setKjrq(String kjrq) {
		this.kjrq = kjrq;
	}
	
	
	

}
