package com.zrsf.forclient.vo.bbcx;

import java.io.Serializable;

public class SstjRowVo implements Serializable {

	private static final long serialVersionUID = 1L;
	private String c0;
	private String c1;
	private Double c2;
	private Double c3;
	private Double c4;
	private Double c5;
	private Double c6;
	private Double c7;
	private Double c8;
	
	public SstjRowVo(){
		
	}
	public SstjRowVo( String c1, Double c2, Double c3, Double c4,
			Double c5) {
		super();	
		this.c1 = c1;
		this.c2 = c2;
		this.c3 = c3;
		this.c4 = c4;
		this.c5 = c5;
	}


	public String catC0() {
		return c0;
	}

	public void setC0(String c0) {
		this.c0 = c0;
	}

	public String getC1() {
		return c1;
	}

	public void setC1(String c1) {
		this.c1 = c1;
	}

	public String getC2() {
		if(c2!=null){
			return String.valueOf(Math.round(c2));
		}
		return null;
	}

	public void setC2(Double c2) {
		this.c2 = c2;
	}

	public String getC3() {
		if(c3!=null){
			return String.format("%.2f", c3);
		}
		return null;
	}

	public void setC3(double c3) {
		this.c3 = c3;
	}

	public String getC4() {
		if(c4!=null){
			return String.valueOf(Math.round(c4));
		}
		return null;
	}

	public void setC4(double c4) {
		this.c4 = c4;
	}

	public String getC5() {
		if(c5!=null){
			return String.format("%.2f", c5);
		}
		return null;
	}

	public void setC5(double c5) {
		this.c5 = c5;
	}
	
	
	
	/**
	 * 两张预计表增加三列 
	 * @return
	 */
	public String getC6() {
		if(c6!=null){
			return String.valueOf(Math.round(c6));
		}
		return null;
	}
	public void setC6(double c6) {
		this.c6 = c6;
	}
	public String getC7() {
		if(c7!=null){
			return String.valueOf(Math.round(c7));
		}
		return null;
	}
	public void setC7(double c7) {
		this.c7 = c7;
	}
	public String getC8() {
		if(c8!=null){
			return String.valueOf(Math.round(c8));
		}
		return null;
	}
	public void setC8(double c8) {
		this.c8 = c8;
	}
	public double catDC6() {
		return c6;
	}
	public double catDC7() {
		return c7;
	}
	public double catDC8() {
		return c8;
	}
	
	
	public double catC2() {
		return c2;
	}
	public double catDC3() {
		return c3;
	}
	public double catDC4() {
		return c4;
	}
	public double catDC5() {
		return c5;
	}
		
}
