package com.zrsf.forclient.vo.dhsq;

import java.io.Serializable;
import java.util.List;


public class TsxxPage implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<TsxxVo> tsxx;
	private double sum;
	
	public TsxxPage(){
		
	}
	
	public List<TsxxVo> getTsxx() {
		return tsxx;
	}
	public void setTsxx(List<TsxxVo> tsxx) {
		this.tsxx = tsxx;
	}
	public double getSum() {
		return sum;
	}
	public void setSum(double sum) {
		this.sum = sum;
	}
	
	
	
	
	
}
