package com.zrsf.forclient.vo.dhsq;

import java.io.Serializable;

public class FpxxVo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String riqi;				//领购日期、缴销核销日期、库存日期
	private String fpzl;				//发票种类（代码 +名称）
	private String qshm;				//起始号码
	private String zzhm;				//终止号码
	private String slfs;				//数量（份数）
	private String fppc;				//发票批次
	
	public FpxxVo(){
		
	}

	public String getRiqi() {
		return riqi;
	}
	public void setRiqi(String riqi) {
		this.riqi = riqi;
	}
	public String getFpzl() {
		return fpzl;
	}
	public void setFpzl(String fpzl) {
		this.fpzl = fpzl;
	}
	public String getQshm() {
		return qshm;
	}
	public void setQshm(String qshm) {
		this.qshm = qshm;
	}
	public String getZzhm() {
		return zzhm;
	}
	public void setZzhm(String zzhm) {
		this.zzhm = zzhm;
	}
	public String getSlfs() {
		return slfs;
	}
	public void setSlfs(String slfs) {
		this.slfs = slfs;
	}
	public String getFppc() {
		return fppc;
	}
	public void setFppc(String fppc) {
		this.fppc = fppc;
	}
	
	
	
	
	

}
