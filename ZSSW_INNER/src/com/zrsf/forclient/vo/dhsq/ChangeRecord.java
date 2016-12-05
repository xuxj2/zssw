package com.zrsf.forclient.vo.dhsq;

import java.io.Serializable;

public class ChangeRecord implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String bgxm;   //变更项目
	private String bgqnr;   //变更前内容,
	private String bghnr;   //变更后内容
	private String bgsj;   //变更时间
	
	public ChangeRecord(){
		
	}

	public String getBgxm() {
		return bgxm;
	}

	public void setBgxm(String bgxm) {
		this.bgxm = bgxm;
	}

	public String getBgqnr() {
		return bgqnr;
	}

	public void setBgqnr(String bgqnr) {
		this.bgqnr = bgqnr;
	}

	public String getBghnr() {
		return bghnr;
	}

	public void setBghnr(String bghnr) {
		this.bghnr = bghnr;
	}

	public String getBgsj() {
		return bgsj;
	}

	public void setBgsj(String bgsj) {
		this.bgsj = bgsj;
	}
	public boolean equals(Object obj) {
		if(obj==null)return false;
		if(this==obj)return true;
		if(obj instanceof ChangeRecord){
			ChangeRecord other =(ChangeRecord) obj;
			return this.bgxm.equals(other.getBgxm())&&this.bgsj.equals(other.getBgsj());
		}
		return super.equals(obj);
	}	
	public int hashCode() {		
		return super.hashCode();
	}
	
	public String toString() {		
		return this.bgxm+this.bgsj;
	}
	
}
