package com.zrsf.forclient.vo.dhsq;

import java.io.Serializable;
import java.util.List;

public class RkxxPage implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<RkxxVo> rkxx;
	private double sum;
	public List<RkxxVo> getRkxx() {
		return rkxx;
	}
	public void setRkxx(List<RkxxVo> rkxx) {
		this.rkxx = rkxx;
	}
	public String getSum() {
		return String.format("%.2f", sum);
	}
	public void setSum(double sum) {
		this.sum = sum;
	}
	
	
	
}
