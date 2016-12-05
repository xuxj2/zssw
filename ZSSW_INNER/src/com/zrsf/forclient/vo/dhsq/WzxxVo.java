package com.zrsf.forclient.vo.dhsq;

import java.io.Serializable;

public class WzxxVo implements Serializable {

	private static final long serialVersionUID = 1L;
	private String wzcfrq;
	private String wzyy;
	private String wzcfyj;
	private double cfje;//处罚金额
	public WzxxVo(){		
	}
	public String getWzcfrq() {
		return wzcfrq;
	}
	public void setWzcfrq(String wzcfrq) {
		this.wzcfrq = wzcfrq;
	}
	public String getWzyy() {
		return wzyy;
	}
	public void setWzyy(String wzyy) {
		this.wzyy = wzyy;
	}
	public String getWzcfyj() {
		return wzcfyj;
	}
	public void setWzcfyj(String wzcfyj) {
		this.wzcfyj = wzcfyj;
	}
	public String getCfje() {
		return String.format("%.2f", cfje);
	}
	public void setCfje(double cfje) {
		this.cfje = cfje;
	}
	
	
	
	
//	public String getCwje() {
//		return String.format("%.2f", cwje);
//	}
//	public void setCwje(double cwje) {
//		this.cwje = cwje;
//	}
//	

}
