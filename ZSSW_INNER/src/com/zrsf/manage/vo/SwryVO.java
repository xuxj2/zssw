package com.zrsf.manage.vo;

public class SwryVO {
	private String swryDm;
	private String xm;
	private String sex;
	private String zwDm;
	private String rzjgDm;
	private String gh;
	private String jhbj;
	private String deviceid;	
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getGh() {
		return gh;
	}
	public void setGh(String gh) {
		this.gh = gh;
	}
	
	public String getJhbj() {
		return jhbj;
	}
	public void setJhbj(String jhbj) {
		this.jhbj = jhbj;
	}
	public String getDeviceid() {
		return deviceid;
	}
	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}
	public String getZwDm() {
		return zwDm;
	}
	public void setZwDm(String zwDm) {
		this.zwDm = zwDm;
	}
	public String getRzjgDm() {
		return rzjgDm;
	}
	public void setRzjgDm(String rzjgDm) {
		this.rzjgDm = rzjgDm;
	}
	public String getSwryDm() {
		return swryDm;
	}
	public void setSwryDm(String swryDm) {
		this.swryDm = swryDm;
	}
	@Override
	public String toString() {
		return "SwryVO [swryDm=" + swryDm + ", xm=" + xm + ", sex=" + sex
				+ ", zwDm=" + zwDm + ", rzjgDm=" + rzjgDm + ", gh=" + gh
				+ ", jhbj=" + jhbj + ", deviceid=" + deviceid + "]";
	}
	
	
	
}
