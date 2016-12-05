package com.zrsf.forclient.vo.dhsq;

import java.io.Serializable;
import java.util.List;


public class NsrDjxxSecondVo implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 投资人信息
	 */
	private List<Investor> tzrxx;
	/**
	 * 任职人员信息
	 */
	private List<OfficeStaff> rzryxx;
	/**
	 * 银行账户信息
	 */
	private List<BankAccount> yhzhxx;

	public NsrDjxxSecondVo() {

	}

	public List<Investor> getTzrxx() {
		return tzrxx;
	}

	public void setTzrxx(List<Investor> tzrxx) {
		this.tzrxx = tzrxx;
	}

	public List<OfficeStaff> getRzryxx() {
		return rzryxx;
	}

	public void setRzryxx(List<OfficeStaff> rzryxx) {
		this.rzryxx = rzryxx;
	}

	public List<BankAccount> getYhzhxx() {
		return yhzhxx;
	}

	public void setYhzhxx(List<BankAccount> yhzhxx) {
		this.yhzhxx = yhzhxx;
	}

	
	

}
