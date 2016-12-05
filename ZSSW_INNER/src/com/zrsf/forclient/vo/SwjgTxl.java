package com.zrsf.forclient.vo;

import java.io.Serializable;
import java.util.List;

public class SwjgTxl implements Serializable {

	private static final long serialVersionUID = 1L;
	private String swjgDm; // 税务机关代码
	private String swjgMc; // 税务机关名称
	private List<SwjgTxl> xjjgs; // 下级机关
	private List<Swry> swrys; // 税务人员
	private String swjgCc;         //机关的级次

	public SwjgTxl() {

	}

	public String getSwjgDm() {
		return swjgDm;
	}

	public void setSwjgDm(String swjgDm) {
		this.swjgDm = swjgDm;
	}

	public String getSwjgMc() {
		return swjgMc;
	}

	public void setSwjgMc(String swjgMc) {
		this.swjgMc = swjgMc;
	}

	public List<SwjgTxl> getXjjgs() {
		if(xjjgs==null||xjjgs.size()<1){
			return null;
		}
		return xjjgs;
	}

	public void setXjjgs(List<SwjgTxl> xjjgs) {
		this.xjjgs = xjjgs;
	}

	public List<Swry> getSwrys() {
		if(swrys==null||swrys.size()<1){
			return null;
		}
		return swrys;
	}

	public void setSwrys(List<Swry> swrys) {
		this.swrys = swrys;
	}

	public String getSwjgCc() {
		return swjgCc;
	}

	public void setSwjgCc(String jc) {
		this.swjgCc = jc;
	}

	
	public String toString() {
		StringBuffer bf=new StringBuffer(this.swjgMc+"("+this.swjgDm+"---"+this.swjgCc+"）");	
		if(this.swrys!=null){
			bf.append("人员数量："+this.swrys.size());
		}	
		int i=1;
		if(this.xjjgs!=null){
			bf.append("下级机关数量："+this.xjjgs.size());			
			for(SwjgTxl lowerJg:xjjgs){
				bf.append("\n"+(i++)+lowerJg.toString());
			}			
		}		
		return bf.toString();
	}
	

}
