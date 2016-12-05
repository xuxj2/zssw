package com.zrsf.forclient.vo.dhsq;

import java.io.Serializable;
import java.util.List;

public class QsxxPage implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private List<QsxxVo> qsxx;
	private double sum;	//欠税总额
	public List<QsxxVo> getQsxx() {
		return qsxx;
	}
	public void setQsxx(List<QsxxVo> qsxx) {
		this.qsxx = qsxx;
	}
	public String getSum() {
		return String.format("%.2f", sum);
	}
	public void setSum(double sum) {
		this.sum = sum;
	}
}
