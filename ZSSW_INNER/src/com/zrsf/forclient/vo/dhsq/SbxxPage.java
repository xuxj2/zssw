package com.zrsf.forclient.vo.dhsq;

import java.io.Serializable;
import java.util.List;

public class SbxxPage implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<SbxxVo> sbxx;
	private double sum;
	public List<SbxxVo> getSbxx() {
		return sbxx;
	}
	public void setSbxx(List<SbxxVo> sbxx) {
		this.sbxx = sbxx;
	}
	public String getSum() {
		return String.format("%.2f", sum);
	}
	public void setSum(double sum) {
		this.sum = sum;
	}
	
}
