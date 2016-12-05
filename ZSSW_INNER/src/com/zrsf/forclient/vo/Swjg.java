package com.zrsf.forclient.vo;

public class Swjg {
	private String swjgDm;
	private String swjgMc;
	private String Zn;
	private String znfw;
	private Swjg sjjg;
	private String jgjc;
	private String swjgJc;
	private String cc;
	private String sxbj;
	
	public Swjg(){
		super();
	}
	public Swjg(String swjgDm){
		this.swjgDm=swjgDm;
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
	public String getZn() {
		return Zn;
	}
	public void setZn(String zn) {
		Zn = zn;
	}
	public String getZnfw() {
		return znfw;
	}
	public void setZnfw(String znfw) {
		this.znfw = znfw;
	}
	public Swjg getSjjg() {
		return sjjg;
	}
	public void setSjjg(Swjg sjjg) {
		this.sjjg = sjjg;
	}
	public String getJgjc() {
		return jgjc;
	}
	public void setJgjc(String jgjc) {
		this.jgjc = jgjc;
	}
	public String getSwjgJc() {
		return swjgJc;
	}
	public void setSwjgJc(String swjgJc) {
		this.swjgJc = swjgJc;
	}
	public String getCc() {
		return cc;
	}
	public void setCc(String cc) {
		this.cc = cc;
	}
	public String getSxbj() {
		return sxbj;
	}
	public void setSxbj(String sxbj) {
		this.sxbj = sxbj;
	}

	public boolean equals(Object obj) {
		if (obj==null)return false;
		if(this==obj)return true;
		if(obj instanceof Swjg){
			Swjg other =(Swjg) obj;
			return this.swjgDm.equals(other.getSwjgDm());
		}
		return super.equals(obj);
	}
	
	public int hashCode() {		
		return this.swjgDm.hashCode();
	}
	
	public String toString() {		
		return this.swjgMc+"("+this.swjgDm+")";
	}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
