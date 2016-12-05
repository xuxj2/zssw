package com.zrsf.forclient.vo.dhsq;

import java.io.Serializable;
import java.util.List;


public class NsrDjxxForthVo implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 登记变更记录
	 */
	private List<ChangeRecord> djbgxx;
	/**
	 * 纳税人登记来源信息
	 */
	private List<RegisteSource> djlyxx;
	/**
	 * 纳税人状态信息
	 */
	private List<StatusRecord> nsrztxx;

	public NsrDjxxForthVo() {

	}

	public List<ChangeRecord> getDjbgxx() {
		return djbgxx;
	}

	public void setDjbgxx(List<ChangeRecord> djbgxx) {
		this.djbgxx = djbgxx;
	}

	public List<RegisteSource> getDjlyxx() {
		return djlyxx;
	}

	public void setDjlyxx(List<RegisteSource> djlyxx) {
		this.djlyxx = djlyxx;
	}

	public List<StatusRecord> getNsrztxx() {
		return nsrztxx;
	}

	public void setNsrztxx(List<StatusRecord> nsrztxx) {
		this.nsrztxx = nsrztxx;
	}

}
