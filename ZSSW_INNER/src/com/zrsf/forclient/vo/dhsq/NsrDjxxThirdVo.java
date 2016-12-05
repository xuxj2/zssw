package com.zrsf.forclient.vo.dhsq;

import java.io.Serializable;
import java.util.List;


public class NsrDjxxThirdVo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 房产登记信息
	 */
	private List<HouseEstate>fcdjxx;
	/**
	 *土地登记信息
	 */
	private List<LandedEstate>tddjxx;
	public NsrDjxxThirdVo(){
		
	}
	public List<HouseEstate> getFcdjxx() {
		return fcdjxx;
	}
	public void setFcdjxx(List<HouseEstate> fcdjxx) {
		this.fcdjxx = fcdjxx;
	}
	public List<LandedEstate> getTddjxx() {
		return tddjxx;
	}
	public void setTddjxx(List<LandedEstate> tddjxx) {
		this.tddjxx = tddjxx;
	}
	
	
	
}
