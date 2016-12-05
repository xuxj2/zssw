package com.zrsf.forclient.vo.dhsq;

import java.io.Serializable;

public class SbxxVo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String sbrq;
	private String zsxm;
	private String zspm;
	private String sfssq;
	private String jkrq;
	private double yzsfje;
	private String jkqx;
	private String rkrq;
	 
	
	public SbxxVo(){		
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

	public String getJkrq() {
		return jkrq;
	}

	public void setJkrq(String jkrq) {
		this.jkrq = jkrq;
	}

	public String getSfssq() {
		return sfssq;
	}

	public void setSfssq(String sfssq) {
		this.sfssq = sfssq;
	}

	public String getRkrq() {
		return rkrq;
	}

	public void setRkrq(String rkrq) {
		this.rkrq = rkrq;
	}

	
	public String getYzsfje() {
		return String.format("%.2f", yzsfje);
	}

	public void setYzsfje(double yzsfje) {
		this.yzsfje = yzsfje;
	}

	public String getJkqx() {
		return jkqx;
	}

	public void setJkqx(String jkqx) {
		this.jkqx = jkqx;
	}
	public boolean equals(Object obj) {
		if(obj==null)return false;
		if(this==obj)return true;
		if(obj instanceof SbxxVo){
			SbxxVo other =(SbxxVo) obj;
			return this.zspm.equals(other.zspm)&&this.sfssq.equals(other.getSfssq());
		}
		return super.equals(obj);
	}	
	public int hashCode() {		
		return super.hashCode();
	}
	
	public String toString() {		
		return this.zsxm+"-"+this.zspm+"("+this.getSfssq()+" to "+ this.getSfssq()+")"+this.getYzsfje();
	}
	

}
