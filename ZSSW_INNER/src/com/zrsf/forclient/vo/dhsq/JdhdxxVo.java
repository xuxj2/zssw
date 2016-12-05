package com.zrsf.forclient.vo.dhsq;

import java.io.Serializable;
/**
 * 鉴定核定信息
 * @author Terry
 *
 */
public class JdhdxxVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	private String swglm;
	private String jdly;		//鉴定来源
	private String zsxm;		
	private String zspm;
	private String jdyxq;
//	private String jdqsrq;
//	private String jdzzrq;
	private String sbqx;
	private double jsje;
	
	private String jdlx;
	
	public JdhdxxVo(){		
	}


//	public String getSwglm() {
//		return swglm;
//	}
//
//
//	public void setSwglm(String swglm) {
//		this.swglm = swglm;
//	}


	public String getJdly() {
		return jdly;
	}


	public void setJdly(String jdly) {
		this.jdly = jdly;
	}


	public String getZsxm() {
		return zsxm;
	}


	public void setZsxm(String zsxm) {
		this.zsxm = zsxm;
	}


	public String getZspm() {
		return zspm;
	}


	public void setZspm(String zspm) {
		this.zspm = zspm;
	}


//	public String getJdqsrq() {
//		return jdqsrq;
//	}
//
//
//	public void setJdqsrq(String jdqsrq) {
//		this.jdqsrq = jdqsrq;
//	}
//
//
//	public String getJdzzrq() {
//		return jdzzrq;
//	}
//
//
//	public void setJdzzrq(String jdzzrq) {
//		this.jdzzrq = jdzzrq;
//	}


	public String getJdyxq() {
		return jdyxq;
	}


	public String getSbqx() {
		return sbqx;
	}


	public void setSbqx(String sbqx) {
		this.sbqx = sbqx;
	}


	public void setJdyxq(String jdyxq) {
		this.jdyxq = jdyxq;
	}


	public String getJsje() {
		return String.format("%.2f", jsje);
	}


	public void setJsje(double jsje) {
		this.jsje = jsje;
	}
	
	public boolean equals(Object obj) {
		if(obj==null)return false;
		if(this==obj)return true;
		if(obj instanceof JdhdxxVo){
			JdhdxxVo other =(JdhdxxVo) obj;
			return this.zspm.equals(other.zspm);
		}
		return super.equals(obj);
	}	
	public int hashCode() {		
		return super.hashCode();
	}
	
	public String toString() {		
		return this.zsxm+"-"+this.zspm+this.jsje;
	}


	public String getJdlx() {
		return jdlx;
	}

	public void setJdlx(String jdlx) {
		this.jdlx = jdlx;
	}
	
	
	
}
