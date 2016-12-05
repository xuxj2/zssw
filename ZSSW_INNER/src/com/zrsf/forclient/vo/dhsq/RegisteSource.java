package com.zrsf.forclient.vo.dhsq;

import java.io.Serializable;

public class RegisteSource implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String djlydm;   //登记来源代码 
	private String djly;   //登记来源 
	private String djzt;   //登记状态 
	public RegisteSource(){
		
	}
	public String getDjlydm() {
		return djlydm;
	}
	public void setDjlydm(String djlydm) {
		this.djlydm = djlydm;
	}
	public String getDjly() {
		return djly;
	}
	public void setDjly(String djly) {
		this.djly = djly;
	}
	public String getDjzt() {
		return djzt;
	}
	public void setDjzt(String djzt) {
		this.djzt = djzt;
	}
	
	public boolean equals(Object obj) {
		if(obj==null)return false;
		if(this==obj)return true;
		if(obj instanceof RegisteSource){
			RegisteSource other =(RegisteSource) obj;
			return this.djlydm.equals(other.getDjlydm());
		}
		return super.equals(obj);
	}	
	public int hashCode() {		
		return super.hashCode();
	}
	
	public String toString() {		
		return this.djly;
	}
	
}
