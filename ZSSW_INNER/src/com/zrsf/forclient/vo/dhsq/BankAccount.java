package com.zrsf.forclient.vo.dhsq;

import java.io.Serializable;

public class BankAccount implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private String xh;   //账号序号
	private String khyh;   //开户银行
	private String yhzh;   //银行帐号
	private String nszh;   //是否是纳税专户
	
	public BankAccount(){
		
	}

//	public String getXh() {
//		return xh;
//	}
//
//	public void setXh(String xh) {
//		this.xh = xh;
//	}

	public String getKhyh() {
		return khyh;
	}

	public void setKhyh(String khyh) {
		this.khyh = khyh;
	}

	public String getYhzh() {
		return yhzh;
	}

	public void setYhzh(String yhzh) {
		this.yhzh = yhzh;
	}

	public String getNszh() {
		return nszh;
	}

	public void setNszh(String nszh) {
		this.nszh = nszh;
	}
	public boolean equals(Object obj) {
		if(obj==null)return false;
		if(this==obj)return true;
		if(obj instanceof BankAccount){
			BankAccount other =(BankAccount) obj;
			return this.khyh.equals(other.getKhyh())&&this.yhzh.equals(other.getYhzh());
		}
		return super.equals(obj);
	}	
	public int hashCode() {		
		return this.yhzh.hashCode();
	}
	
	public String toString() {		
		return this.khyh+this.yhzh;
	}
	
	
}
