package com.zrsf.manage.vo;

import java.io.Serializable;

import org.apache.log4j.chainsaw.Main;

public class TaxStatisticRow implements Serializable {

	private static final long serialVersionUID = 1L;
	private String ssq;		//所属期
	private int xh;			//顺序号
	private String swjgMc;	//名称
	private Double byyj;	//当月预计
	private Double sntq;	//上年同期
	private Double bytb;	//当月同比增减
	private Double sysz;	//上月数目
	private Double bnlj;	//当年累计预计
	private Double snlj;	//上年当月累计
	private Double ljtb;	//累计同比增减
	

	public TaxStatisticRow(){		
	}


	public String getSsq() {
		return ssq;
	}
	public void setSsq(String ssq) {
		this.ssq = ssq;
	}
	public int getXh() {
		return xh;
	}	
	public void setXh(int xh) {
		this.xh = xh;
	}
	
	public String getSwjgMc() {
		return String.format("%-8s",swjgMc);
	}
	public void setSwjgMc(String swjgMc) {
		this.swjgMc = swjgMc;
	}
	public String getByyj() {
		if(byyj==null){
			return null;
		}
		return String.format("%8.0f",byyj);
	}
	public void setByyj(Double byyj) {
		this.byyj = byyj;
	}
	public String getSntq() {
		if(sntq==null){
			return null;
		}
		return String.format("%8.0f",sntq);
	}
	public void setSntq(Double sntq) {
		this.sntq = sntq;
	}
	public String getBytb() {
		if(bytb==null){
			return null;
		}
		return String.format("%8.2f",bytb);
	}
	public void setBytb(Double bytb) {
		this.bytb = bytb;
	}
	public String getSysz() {
		if(sysz==null){
			return null;
		}
		return String.format("%8.0f",sysz);
	}
	public void setSysz(Double sysz) {
		this.sysz = sysz;
	}
	public String getBnlj() {
		if(bnlj==null){
			return null;
		}
		return String.format("%8.0f",bnlj);
	}
	public void setBnlj(Double bnlj) {
		this.bnlj =bnlj ;
	}
	public String getSnlj() {
		if(snlj==null){
			return null;
		}
		return String.format("%8.0f",snlj);
	}
	public void setSnlj(Double snlj) {
		this.snlj = snlj;
	}
	public String getLjtb() {
		if(ljtb==null){
			return null;
		}
		return String.format("%8.2f",ljtb);
	}
	public void setLjtb(Double ljtb) {
		this.ljtb = ljtb;
	}


	
	public String toString() {
		return this.getSsq() +"	"+  xh+"	"
		+ String.format("%-8s", this.getSwjgMc())+"	" 
		+ String.format("%8.0f", this.getByyj()) +"	"
		+ String.format("%8.0f", this.getSntq())+"	"
		+ String.format("%8.2f", this.getBytb())+"	"
		+ String.format("%8.0f", this.getSysz())+"	" 
		+ String.format("%8.0f", this.getBnlj())+"	" 
		+ String.format("%8.0f", this.getSnlj())+"	" 
		+ String.format("%8.2f", this.getLjtb()) ;
	}

}
