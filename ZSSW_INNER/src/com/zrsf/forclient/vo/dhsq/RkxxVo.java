package com.zrsf.forclient.vo.dhsq;

import java.io.Serializable;

public class RkxxVo implements Serializable {

	private static final long serialVersionUID = 1L;
	private String sbrq;		//申报日期
	private String zsxm;		//征收项目
	private String zspm;		//征收品目
	private String sfssq;		//税费所属期
	private double rkje;		//入库金额
	private String jkqx;		//缴款期限
	private String kjrq;		//扣缴日期
	private String rkrq;		//入库日期
	
	public RkxxVo(){		
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

	public String getRkje() {
		return String.format("%.2f", rkje);
	}

	public void setRkje(double rkje) {
		this.rkje = rkje;
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

	public String getRkrq() {
		return rkrq;
	}

	public void setRkrq(String rkrq) {
		this.rkrq = rkrq;
	}

	
	
	
}
