package com.zrsf.forclient.vo.dhsq;

import java.io.Serializable;

public class StatusRecord implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fzchrdsj;   //非正常户认定日期
	private String rdry;   //认定人员
	private String fzchjcsj;   //非正常户解除日期
	private String jcry;   //解除人员
	
	public StatusRecord(){
		
	}

	public String getFzchrdsj() {
		return fzchrdsj;
	}

	public void setFzchrdsj(String fzchrdsj) {
		this.fzchrdsj = fzchrdsj;
	}

	public String getRdry() {
		return rdry;
	}

	public void setRdry(String rdry) {
		this.rdry = rdry;
	}

	public String getFzchjcsj() {
		return fzchjcsj;
	}

	public void setFzchjcsj(String fzchjcsj) {
		this.fzchjcsj = fzchjcsj;
	}

	public String getJcry() {
		return jcry;
	}

	public void setJcry(String jcry) {
		this.jcry = jcry;
	}
	
	public boolean equals(Object obj) {
		if(obj==null)return false;
		if(this==obj)return true;
		if(obj instanceof StatusRecord){
			StatusRecord other =(StatusRecord) obj;
			return this.fzchrdsj.equals(other.getFzchrdsj());
		}
		return super.equals(obj);
	}	
	public int hashCode() {		
		return super.hashCode();
	}
	
	public String toString() {		
		return this.fzchrdsj.toString();
	}
	
	
	
	
}
